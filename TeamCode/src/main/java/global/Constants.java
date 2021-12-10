package global;

public class Constants {
    public static final int THREAD_REFRESH_RATE = 100; //hz
    public static final double MAX_ALLOWED_DELAY = 30; //ms

    public static final int INF = (int) 1e99;

    public static final double ANG_ACC_ARC = 0.1;

    public static final double LIFT_CM_TO_TICKS = (537.6)/(Math.PI*3); //in ticks/cm , (ticks/rev)/(rev/cm)
    public static final double TURRET_ANGLE_DEG_TO_TICKS = (8192.0/360.0)*4.0; //ticks/deg, (ticks/deg)*ratio
}
