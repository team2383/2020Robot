package frc.robot;

import frc.robot.Subsystems.*;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
/**
 * (HAL) Hardware Abstraction Layer
 */
public class HAL {
    public static Drivetrain drivetrain = new Drivetrain();
    public static Feeder feeder = new Feeder();
    public static AHRS navX = new AHRS(SPI.Port.kMXP);
    public static AHRS TnavX = new AHRS(SPI.Port.kMXP);
    public static Shooter shoot = new Shooter();
    public static Conveyor conveyor = new Conveyor();
    public static Trigger triggered = new Trigger();
    public static Turret turret = new Turret();
    public static Hood hood = new Hood();
    public static LimelightSubsystem limelight = new LimelightSubsystem();
    //public static Shifter shifty = new Shifter();
    public static SelfClimb selfClimb = new SelfClimb();
    public static BuddyClimb buddyClimb = new BuddyClimb();
    public static WheelofFortune wheelofFortune = new WheelofFortune();
    public static Telescope telescope = new Telescope();
}
