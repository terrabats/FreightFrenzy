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
        if (!codeSegs.containsKey(generators.size() - 1)) {
            codeSegs.put(generators.size() - 1, new LinkedList<>());
            synchronizeWithMove.put(generators.size() - 1, new LinkedList<>());
        }

        nonNull(codeSegs.get(generators.size())).add(cs);
        nonNull(synchronizeWithMove.get(generators.size())).add(isSyncedWithMove);
    }

    public void update() {
        if (!runningCodeSeg) {
            if (!moveRunning) {
                if (codeSegs.containsKey(curPath)
                        && !nonNull(codeSegs.get(curPath)).isEmpty()
                        && !nonNull(synchronizeWithMove.get(curPath)).getFirst()) {
                    runningCodeSeg = true;
                    nonNull(nonNull(codeSegs.get(curPath)).poll()).run();
                } else {
                    resumeMove();
                }
            } else {
                updateMovement();
                if (codeSegs.containsKey(curPath) && !nonNull(codeSegs.get(curPath)).isEmpty()) {
                    nonNull(nonNull(codeSegs.get(curPath)).poll()).run();
                }
            }
        } else {
            runningCodeSeg = !bot.rfsHandler.rfsQueue.isEmpty();
        }
    }

    public <T> T nonNull(T obj) {
        return Objects.requireNonNull(obj);
    }
}
