package autoutil;

import static global.General.*;

import java.util.LinkedList;
import java.util.Objects;
import java.util.TreeMap;

import automodules.StageList;
import geometry.AngleType;
import util.codeseg.CodeSeg;

public class Executor extends MovementExecutor {

    private final TreeMap<Integer, LinkedList<CodeSeg>> codeSegs = new TreeMap<>();
    private final TreeMap<Integer, LinkedList<Boolean>> synchronizeWithMove = new TreeMap<>();

    private boolean runningCodeSeg = false;

    //region CONSTRUCTORS
    public Executor() {
        super(0, 0, Math.PI/2, AngleType.RADIANS);
    }

    public Executor(double stH, AngleType angleType) {
        super(0, 0, stH, angleType);
    }

    public Executor(double stX, double stY, double stH, AngleType angleType) {
        super(stX, stY, stH, angleType);
    }
    //endregion

    public void addSynchronizedRF(StageList rf) {
        addCodeseg(() -> bot.addAutoModule(rf), true);
    }

    public void addUnsynchronizedRF(StageList rf) {
        addCodeseg(() -> bot.addAutoModule(rf), false);
    }

    private void addCodeseg(CodeSeg cs, boolean isSyncedWithMove) {
        if (!codeSegs.containsKey(curPath)) {
            codeSegs.put(curPath, new LinkedList<>());
            synchronizeWithMove.put(curPath, new LinkedList<>());
        }

        Objects.requireNonNull(codeSegs.get(curPath)).add(cs);
        Objects.requireNonNull(synchronizeWithMove.get(curPath)).add(isSyncedWithMove);
    }

    public boolean finished() {
        return finishedMove() && (!codeSegs.containsKey(curPath) ||
                Objects.requireNonNull(codeSegs.get(curPath)).isEmpty())
                && bot.rfsHandler.rfsQueue.isEmpty();
    }

    public void update() {
        if (!runningCodeSeg) {
            if (!moveRunning) {
                if (codeSegs.containsKey(curPath)
                        && !Objects.requireNonNull(codeSegs.get(curPath)).isEmpty()
                        && !Objects.requireNonNull(synchronizeWithMove.get(curPath)).getFirst()) {
                    log.show("Running a CodeSeg");
                    runningCodeSeg = true;
                    Objects.requireNonNull(Objects.requireNonNull(codeSegs.get(curPath)).poll()).run();
                } else if (!finishedMove()) {
                    resumeMove();
                    log.show("Resumed Movement");
                } else {
                    log.show("Finished");
                }
            } else {
                log.show("Updating Movement");
                updateMovement();
                if (codeSegs.containsKey(curPath) && !Objects.requireNonNull(codeSegs.get(curPath)).isEmpty()) {
                    Objects.requireNonNull(Objects.requireNonNull(codeSegs.get(curPath)).poll()).run();
                }
            }
        } else {
            log.show("Running CodeSeg");
            runningCodeSeg = !bot.rfsHandler.rfsQueue.isEmpty();
        }
    }

}
