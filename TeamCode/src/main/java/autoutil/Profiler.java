package autoutil;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

import geometry.position.Point;
import math.Function;
import math.calculus.Differentiator;
import math.calculus.Integrator;
import util.Timer;
import util.codeseg.ReturnCodeSeg;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.fault;

public class Profiler {


    private final ReturnCodeSeg<Double> processVariable;
    private final Integrator integrator;
    private final Differentiator differentiator;
    private final Timer timer = new Timer();
    private ArrayList<Double> values = new ArrayList<>();
    private ArrayList<Double> times = new ArrayList<>();

    public Profiler(ReturnCodeSeg<Double> processVariable){
        this.processVariable = processVariable;
        integrator = new Integrator();
        integrator.defineFunction(processVariable);
        differentiator = new Differentiator();
        differentiator.defineFunction(processVariable);
        values.add(0.0);
        times.add(0.0);
        timer.reset();
    }

    public void update(){
        values.add(processVariable.run());
        times.add(timer.seconds());
        updateIntegrator();
        updateDifferentiator();
    }

    private void updateIntegrator(){
        if(getUpdateNumber() == 1){
            integrator.integrate(getPreviousProcess(1), getCurrentProcess());
        }else{
            integrator.integrate(getPreviousProcess(2), getPreviousProcess(1), getCurrentProcess());
        }
    }
    private void updateDifferentiator(){
        if(getUpdateNumber() == 1){
            differentiator.differentiate(getPreviousProcess(1), getCurrentProcess());
        }else{
            differentiator.differentiate(getPreviousProcess(2), getPreviousProcess(1), getCurrentProcess());
        }
    }

    public void reset(){
        integrator.reset();
        values = new ArrayList<>();
        times = new ArrayList<>();
        values.add(0.0);
        times.add(0.0);
        timer.reset();
    }

    private int getUpdateNumber(){
        return values.size()-1;
    }

    private double getCurrentValue(){
        return getPreviousValue(0);
    }

    private double getCurrentTime(){
        return getPreviousTime(0);
    }

    private Point getCurrentProcess(){
        return getPreviousProcess(0);
    }

    private double getPreviousValue(int n){
        fault.check("You tried to get a value that doesn't exist", Expectation.EXPECTED, Magnitude.CRITICAL, (values.size()-n) < 0, false);
        return values.get(values.size()-n-1);
    }

    private double getPreviousTime(int n){
        fault.check("You tried to get a time that doesn't exist", Expectation.EXPECTED, Magnitude.CRITICAL, (times.size()-n) < 0, false);
        return times.get(times.size()-n-1);
    }

    private Point getPreviousProcess(int n){
        fault.check("You tried to get a time that doesn't exist", Expectation.EXPECTED, Magnitude.CRITICAL, (times.size()-n) < 0, false);
        return new Point(getPreviousTime(n), getPreviousValue(n));
    }

    public double getDerivative(){
        return differentiator.getDerivative();
    }

    public double getIntegral(){
        return integrator.getIntegral();
    }


//    private final Timer timer = new Timer();
//
//    private final double kp, ki, kd;
//
//    private double cur_integral = 0, prev_error = 0;
//
//    private boolean prevUsed;
//
//    public Profiler(double kp, double ki, double kd) {
//        this.kp = kp;
//        this.ki = ki;
//        this.kd = kd;
//        timer.reset();
//        prevUsed = false;
//    }
//
//    public double getPow(double error) {
//        if (!prevUsed) {
//            prevUsed = true;
//            prev_error = error;
//        }
//        double dV = error - prev_error;
//        cur_integral += (error + prev_error)/2 * timer.seconds(); // trapezoidal Riemann sum
//        timer.reset();
//        prev_error = error;
//        return kd * dV + ki * cur_integral + kp * error;
//    }
}
