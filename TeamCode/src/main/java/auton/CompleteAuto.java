package auton;

import static global.General.fault;

import androidx.annotation.NonNull;

import automodules.StageList;
import autoutil.executors.Executor;
import geometry.circles.AngleType;
import util.codeseg.CodeSeg;
import util.codeseg.ParameterCodeSeg;

public abstract class CompleteAuto extends Auto {
    protected Executor executor;

    boolean haveTelemetry = true;

    public void addTelemetry() { haveTelemetry = false; }

    public void duringLoop() {}

    public void onStart() {}

    public abstract void defineExecutorAndAddPoints();

    public abstract void onEnd();

    private void checkHeadingWrong(double h) {
        fault.check("Using degrees or wrong range of heading ( correct is (-PI, PI] ) in default point?",
                Math.abs(h) > Math.PI || h == -Math.PI, false);
    }

    public double[] wayPoint(double x, double y, double h) {
        checkHeadingWrong(h);
        return new double[] { x, y, h, 1 };
    }

    public double[] setPoint(double x, double y, double h) {
        checkHeadingWrong(h);
        return new double[] { x, y, h, 0 };
    }

    public Object[] unsyncedRF(StageList rf) {
        return new Object[] { rf, 0 };
    }

    public Object[] syncedRF(StageList rf) {
        return new Object[] { rf, 1 };
    }

    public CodeSeg custom(CodeSeg in) {
        return in;
    }

    public void addExecutorFuncs(@NonNull Object... funcs) {
        for (Object func : funcs) {
            if (func instanceof CodeSeg) {
                ((CodeSeg) func).run();
            } else if (func instanceof double[]) {
                double[] point = (double[]) func;
                fault.check("Incorrect Point Syntax", point.length != 4, false);
                if (point[3] == 0) {
                    executor.addSetpoint(point[0], point[1], point[2], AngleType.RADIANS);
                } else {
                    executor.addWaypoint(point[0], point[1], point[2], AngleType.RADIANS);
                }
            } else if (func instanceof Object[]) {
                Object[] newObj = (Object[]) func;

                int type = (int) newObj[1];
                StageList rf = (StageList) newObj[0];

                if (type == 0) {
                    executor.addUnsynchronizedRF(rf);
                } else {
                    executor.addSynchronizedRF(rf);
                }
            }
        }
    }

    @Override
    public void initAuto() {
        defineExecutorAndAddPoints();
        executor.complete();
    }

    @Override
    public void runAuto() {
        onStart();

        executor.resumeMove();

        while (opModeIsActive() && !executor.finished()) {
            executor.update();
            addTelemetry();
            duringLoop();
            update(haveTelemetry);
        }

        onEnd();
    }
}
