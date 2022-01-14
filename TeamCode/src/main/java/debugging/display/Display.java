package debugging.display;

import math.Function;
import math.calculus.Differentiator;
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
        Differentiator differentiator = new Differentiator();
        differentiator.defineFunction(f);
        double step = 0.01;
        double x = 0;
        while (x < (3-step)){
            integrator.integrate(x, step);
            x += step;
        }
        differentiator.differentiate(3, 0.01);
        System.out.println("Integral: " + Math.round(integrator.getIntegral()));
        System.out.println("Derivative: " + Math.round(differentiator.getDerivative()));
//        System.out.println("Integral: " + integrator.getIntegral());
        //drawWindow(width, height, "Display");
    }
}
