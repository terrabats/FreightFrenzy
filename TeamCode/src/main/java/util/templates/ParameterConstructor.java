package util.templates;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import util.codeseg.ReturnParameterCodeSeg;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.fault;

// TODO TEST VARINI
// Does this actually work?
// Make a unit test for this class
public interface ParameterConstructor<T> {
    HashMap<ParameterType, Integer> constructorMap = new HashMap<>();
    HashMap<ParameterType, ReturnParameterCodeSeg<Double[], Double[]>> preprocessorMap = new HashMap<>();

    // TODO FIX
// Make this parameterized
    void construct(Double[] in);

    default void addConstructor(ParameterType parameterType, int numberOfParameters){
        constructorMap.put(parameterType, numberOfParameters);
        preprocessorMap.put(parameterType, input -> input);
    }

    default void addConstructor(ParameterType parameterType, int numberParameters, ReturnParameterCodeSeg<Double[], Double[]> preprocessor){
        constructorMap.put(parameterType, numberParameters);
        preprocessorMap.put(parameterType, preprocessor);
    }

    default void createConstructors(ParameterType inputType, Double[] inputParameters, Double[] defaults){
        if(constructorMap.containsKey(inputType)){
            int numberOfParameters = Objects.requireNonNull(constructorMap.get(inputType));
            fault.check("Wrong number of parameters for constructor of type: " + inputType.getName(), Expectation.UNEXPECTED, Magnitude.MAJOR, numberOfParameters == inputParameters.length, true);
            Double[] inputPart = new ArrayList<>(Arrays.asList(inputParameters)).subList(0, numberOfParameters).toArray(inputParameters);
            inputPart = Objects.requireNonNull(preprocessorMap.get(inputType)).run(inputPart);
            Double[] defaultPart = new ArrayList<>(Arrays.asList(inputParameters)).subList(numberOfParameters+1, defaults.length).toArray(defaults);
            List<Double> combined = new ArrayList<>(Arrays.asList(inputPart));
            combined.addAll(Arrays.asList(defaultPart));
            construct(combined.toArray(inputPart));
        }else{
            fault.check("Constructor not found", Expectation.UNEXPECTED, Magnitude.MAJOR);
        }
    }


}
