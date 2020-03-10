package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class Field {
    //Global Checkmarks
    static public boolean armUp = false;
    static public boolean wristUp = false;
    static public boolean operatorCool = true;
    static public boolean limelightOn = false;
    static public boolean spooltoggled = false;
    static public boolean limelightHeadache = false;

    static public boolean maxArea = true;

    static public boolean handJob = false;


    static public double startTimerDelay2 = Timer.getFPGATimestamp();
    static public double shooterClose = 4000;

    public static double shooter_kP = .31; //.29 //.31
    public static double shooter_kI = 0.000; //0.001
    public static double shooter_kD = 0;//0.1 //0.00
    public static double shooter_kF = 0.0475;//0.0475
    public static double desiredRPM = 5000;

    public static boolean slowDown = true;

    

    // //short bitches only
    // static public double RPM = 3500;
    // static public double Hood = 0;

}
