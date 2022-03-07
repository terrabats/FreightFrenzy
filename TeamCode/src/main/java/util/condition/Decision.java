package util.condition;

import java.util.HashMap;

import util.codeseg.CodeSeg;

public class Decision {
    private final HashMap<DecisionList, CodeSeg> decisions = new HashMap<>();


    public Decision(){}
    public Decision(DecisionList decision, CodeSeg codeSeg){
        addOption(decision, codeSeg);
    }

    public Decision addOption(DecisionList decision, CodeSeg codeSeg){
        decisions.put(decision, codeSeg);
        return this;
    }

    public void check(DecisionList decisionToCheck){
        if(decisions.containsKey(decisionToCheck)){
            decisions.get(decisionToCheck).run();
        }
    }

    public interface DecisionList{}
}
