package autoutil.display;

import javax.swing.JFrame;

import math.Function;
import math.calculus.Integrator;

public class Display extends Drawer{
    private static final int width = 700;
    private static final int height = 700;

    public static void main(String[] args) {
        Function f = new Function() {
            @Override
            public double f(double x) {
                return Math.pow(x,2);
            }
        };
        Integrator integrator = new Integrator();
        integrator.defineFunction(f);
        double step = 0.01;
        double x = 0;
        while (x < (3-step)){
            integrator.integrate(x, step);
            x += step;
        }
        System.out.println("Integral: " + integrator.getIntegral());
//        System.out.println("Integral: " + integrator.getIntegral());
        //drawWindow(width, height, "Display");
    }
}
