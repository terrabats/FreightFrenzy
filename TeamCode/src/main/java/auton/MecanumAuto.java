package auton;

import android.os.Build;

import java.util.Objects;
import java.util.function.Supplier;

import androidx.annotation.RequiresApi;
import automodules.StageList;
import autoutil.executors.Executor;
import autoutil.executors.MecanumExecutor;
import autoutil.generators.Generator;
import autoutil.reactors.Reactor;
import autoutil.reactors.mecanum.MecanumReactor;
import teleutil.GamepadHandler;
import teleutil.button.Button;
import teleutil.button.ButtonEventHandler;
import util.ExceptionCatcher;
import util.codeseg.CodeSeg;

import static global.General.automodules;

public abstract class MecanumAuto<R extends MecanumReactor, G extends Generator> extends Auto{
    protected MecanumExecutor executor;
    protected R reactor;
    protected G generator;


    public abstract void define();

    @Override
    public void initAuto() {
        executor = new MecanumExecutor(this);
        define();
        executor.setPath(generator.getPath());
        executor.setReactor(reactor);
    }

    @Override
    public void runAuto() {
        executor.followPath();
    }
}
