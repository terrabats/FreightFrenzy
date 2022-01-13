package math.polynomial;

/**
 * NOTE: Uncommented
 */

public class Cubic extends Polynomial{
    double a;
    double b;
    double c;
    double d;

    public Cubic(double a, double b, double c, double d){
        super(a,b,c,d);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double[] roots() {
        // TODO FIX
        // Either dont do this or use newtons method or something
        return new double[]{0,0,0};
    }
}
