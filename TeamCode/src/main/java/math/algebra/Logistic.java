package math.algebra;

/**
 * NOTE: Uncommented
 */

public class Logistic extends Function{
    private final double m;
    private final double b;
    private final double k;
    /**
     * Represents the equation m/(1+be^-kx)
     */
    public Logistic(double m, double b, double k){
        this.m = m;
        this.b = b;
        this.k = k;
    }

    public Logistic( double b, double k){
        this.m = 1+b*Math.exp(-k);
        this.b = b;
        this.k = k;
    }

    @Override
    public double y(double x) {
        return m/(1+b*Math.exp(-k*x));
    }

    public double yr(double x){return Math.signum(x)*y(Math.abs(x));}
}
