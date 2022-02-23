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

    public CodeSeg wayPoint(double x, double y, double h) {
        checkHeadingWrong(h);
        return () -> executor.addWaypoint(x, y, h, AngleType.RADIANS);
    }

    public CodeSeg setPoint(double x, double y, double h) {
        checkHeadingWrong(h);
        return () -> executor.addSetpoint(x, y, h, AngleType.RADIANS);
    }

    public CodeSeg unsyncedRF(StageList rf) {
        return () -> executor.addUnsynchronizedRF(rf);
    }

    public CodeSeg syncedRF(StageList rf) {
        return () -> executor.addSynchronizedRF(rf);
    }

    public CodeSeg custom(CodeSeg in) {
        return in;
    }

    public void addExecutorFuncs(@NonNull CodeSeg... funcs) {
        for (CodeSeg func : funcs) {
            func.run();
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
