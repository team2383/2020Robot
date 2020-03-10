package frc.robot;

public class RobotMap{
    public static int rightmasterPort = 3;
    public static int leftmasterPort = 2;
    public static int rightfollowerPort = 4;
    public static int leftfollowerPort = 1;

    public static int feedPort = 5;
    public static int conveyPort = 7;

    public static int triggerPort = 9;
    public static int shooterMasterPort = 12;
    public static int shooterFollowerPort = 11;

    public static int hoodPort = 10;
    public static int turretPort = 13;
    public static int deploymentPort = 14;
    public static int deployment2Port = 15;
    public static int wheelPort = 16; 
    public static int spoolLPort = 17;
    public static int spoolRPort = 18;
    
    public static int s1fchannel = 7; 
    public static int s1rchannel = 6;
    public static int s2fchannel = 5;
    public static int s2rchannel = 4;
    public static int s3fchannel = 3; 
    public static int s3rchannel = 2;
    public static int s4fchannel = 1;
    public static int s4rchannel = 0;

    

    
  // CONSTANTS
  public static double kDrive_Motion_P = 0.7;				//0.5, off by two inches %/ft
  public static double kDrive_Motion_D = 0.0;	
  public static double kDrive_Motion_A = 0.0;	
  public static double kDrive_Motion_V = 0.058;

  public static double kDrive_Motion_trackwidth = 2.29;
  public static double kDrive_Motion_turnP = 0.0215; //0.0215

  
  public static double kDrive_Motion_Tolerance = 0.05;// ft
  public static double kDrive_WheelDiameterInch = 6.0; //6.25
  public static double getWheelCircumference() { return (kDrive_WheelDiameterInch*Math.PI)/12.0; };

  public static int kDrive_ContinuousCurrentLimit = 60;
	public static int kDrive_PeakCurrentLimit = 80;
  public static int kDrive_PeakCurrentTime_ms = 100;
  
  public static double kDrive_peakOutput = 0.8;
  public static double kDrive_Motion_talonP = 0.7;			// %/ft
	public static double kDrive_Motion_talonI = 0.002;			//natives
  public static double kDrive_Motion_talonD = 15;
  
  	//talon V and motio V are shared
	public static double kDrive_Motion_Velocity = 6.0;		// for turn
  public static double kDrive_Motion_Acceleration = 13.0;
  
  

}