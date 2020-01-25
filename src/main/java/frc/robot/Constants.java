package frc.robot;

public class Constants {

  // DRIVETRAIN
  public static int rightMasterPort = 1;
  public static int rightFollowerPort = 2;
  public static int leftMasterPort = 3;
  public static int leftFollowerPort = 4;

  // SUBSYSTEM PORTS
  public static int turretPort = 5;
  public static int shooterMasterPort = 6;
  public static int shooterFollowerPort = 7;
  public static int transPort = 8;
  public static int shootanglePort = 9;
  public static int colorwheelPort = 10;
  public static int intakePort = 11;
  public static int conveyor1Port = 12;
  public static int conveyor2Port = 13;

  // DRIVE CONSTANTS
  
  //PID 

  public static double kDrive_Motion_P = 0.6;		//originally 1.4
  public static double kDrive_Motion_D = 0.1;	
  public static double kDrive_Motion_A = 0.00;	
  public static double kDrive_Motion_V = 0.058;
  public static double kDrive_Motion_turnP = 0.025;  //originally 0.0175 
  
  public static double kDrive_Motion_talonP = 0.7;	 
  public static double kDrive_Motion_talonI = 0.002;			
  public static double kDrive_Motion_talonD = 15;       
  
  public static double kDrive_Motion_Tolerance = 0.05;

  // DIMENSIONS
  public static double kDrive_Motion_trackwidth = 2.16;
  public static double kDrive_WheelDiameterInch = 6.25;
  public static double getWheelCircumference() { return (kDrive_WheelDiameterInch*Math.PI)/12.0; };
  
  // OUTPUT
  public static double kDrive_peakOutput = 0.8; 
  public static int kDrive_ContinuousCurrentLimit = 60;
  public static int kDrive_PeakCurrentLimit = 80;
  public static int kDrive_PeakCurrentTime_ms = 100;
  
  //MOTION
	public static double kDrive_Motion_Velocity = 6.0;		
	public static double kDrive_Motion_Acceleration = 13.0;
}
