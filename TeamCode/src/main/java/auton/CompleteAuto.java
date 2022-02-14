package auton;

import autoutil.executors.Executor;

public abstract class CompleteAuto extends Auto {
    protected Executor executor;

    boolean haveTelemetry = true;

    public void addTelemetry() { haveTelemetry = false; }

    public void duringLoop() {}

    public void onStart() {}

    public abstract void defineExecutor();

    public abstract void setPoints();

    public abstract void onEnd();

    @Override
    public void initAuto() {
        defineExecutor();
        setPoints();
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
