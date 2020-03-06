package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import frc.robot.ninjaLib.FollowTrajectory2;
import frc.robot.Field;
import frc.robot.ninjaLib.HelperCommand;
import frc.robot.ninjaLib.PathLoader;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

import static frc.robot.HAL.limelight;

import edu.wpi.first.wpilibj.Timer;

public class Drivetrain{

  Command autoCommand;
  
  DifferentialDrive drive;
  double limeDegree;
  public boolean run=false;

  WPI_TalonFX rightMaster = new WPI_TalonFX(RobotMap.rightmasterPort);
  WPI_TalonFX rightSlave = new WPI_TalonFX(RobotMap.rightfollowerPort);
  WPI_TalonFX leftMaster = new WPI_TalonFX(RobotMap.leftmasterPort);
  WPI_TalonFX leftSlave = new WPI_TalonFX(RobotMap.leftfollowerPort);

  Waypoint[] baseline = new Waypoint[] {
    new Waypoint(0, 0, 0),
    new Waypoint(20.0/12.0, 0, Pathfinder.d2r(0)),
    };
    
  
  Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
  0.02, 	//delta time
  1.0,		//max velocity in ft/s for the motion profile
  1.0,		//max acceleration in ft/s/s for the motion profile
  500);	//max jerk in ft/s/s/s for the motion profile

  Trajectory trajectory = PathLoader.get(baseline, config);

  public Drivetrain () {
    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);
    drive = new DifferentialDrive(leftMaster, rightMaster);
    configMotorController();
  }

  public void configMotorController(){
    leftMaster.setInverted(false);
    leftSlave.setInverted(false);
    leftMaster.setSensorPhase(false);
    leftSlave.setSensorPhase(true);

    rightMaster.setInverted(true);
    rightSlave.setInverted(true);
    rightMaster.setSensorPhase(false);
    rightSlave.setSensorPhase(true);
    
    leftMaster.setNeutralMode(NeutralMode.Brake);
    leftSlave.setNeutralMode(NeutralMode.Brake);
    rightMaster.setNeutralMode(NeutralMode.Brake);
    rightSlave.setNeutralMode(NeutralMode.Brake);
    
    rightMaster.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    leftMaster.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
  }
  
  public void tank(double leftValue, double rightValue) {
		drive.tankDrive(leftValue, rightValue, false);
  }
  
  public void arcade(double move, double turn){
    drive.arcadeDrive(move,turn);
  }





  public void driveBack()
  {
    FollowTrajectory2 back;
    back = new FollowTrajectory2(trajectory, false);
  }

  public void individualBoomer(double leftSpeed, double rightSpeed){
    leftMaster.set(leftSpeed);
    rightMaster.set(rightSpeed);
  }

  public void coastMode(){
    leftMaster.setNeutralMode(NeutralMode.Coast);
    leftSlave.setNeutralMode(NeutralMode.Coast);
    rightMaster.setNeutralMode(NeutralMode.Coast);
    rightSlave.setNeutralMode(NeutralMode.Coast);
  }

  public double getRightPosition() {
    return rightMaster.getSelectedSensorPosition()/16384.0*RobotMap.getWheelCircumference();
  }

  public double getLeftPosition(){
    return leftMaster.getSelectedSensorPosition()/16384.0*RobotMap.getWheelCircumference();
  }

  public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(0, 0, 0);
    rightMaster.setSelectedSensorPosition(0, 0, 0);
  }
  
  public double getLeftVelocity() {
		return leftMaster.getSelectedSensorVelocity(0); // 16384.0 * RobotMap.getWheelCircumference();
  }
  
	public double getRightVelocity() {
		return rightMaster.getSelectedSensorVelocity(0); // 16384.0 * RobotMap.getWheelCircumference();
  }

  // public void limeAlign() {
  //   if(limelight.xOffset() >= 2.0){
  //     // arcade(limelight.xOffset()/27 + .15,0);
  //     arcade(.4,0);
  //   }
  //   else if(limelight.xOffset() <= -2.0){
  //     // arcade(limelight.xOffset()/27 - .15,0);
  //     arcade(-.4,0);
  //   }
  //   //double divisor = 30;
  //   //arcade(offset/divisor,0);
  // }

  public void limeAlign() {
    if(limelight.xOffset() >= 2.0){
      // arcade(limelight.xOffset()/27 + .15,0);
      arcade(.4,0);
    }
    else if(limelight.xOffset() <= -2.0){
      // arcade(limelight.xOffset()/27 - .15,0);
      arcade(-.4,0);
    }

  }

  public boolean pivoted(){
    double offset = limelight.xOffset();
    return Math.abs(offset) < 0.5;
  }

  public void limeApache() {
    double xOffset = limelight.xOffset();
    double area = limelight.area();
    double approachSpeed  = -0.402*Math.log(area) + 0.9112;
    boolean hasTarget = limelight.hasTargets();
    if (4.8 < area){
      Field.maxArea = false;
    }
    
    else if (Field.maxArea){
      if (xOffset > -27 && xOffset <= -1 && hasTarget == true){
          arcade(-.4, approachSpeed);
      }  
      else if (xOffset < 27 && xOffset >= 1 && hasTarget == true){
          arcade(.4, approachSpeed);
      }
      else if (xOffset > -1 && xOffset < 0 && hasTarget == true){
        arcade(0, approachSpeed);
      }
      else if (xOffset >= 0 && xOffset < 1 && hasTarget == true){
        arcade(0, approachSpeed);
      }
    }
    else{
      arcade(0, 0.0);
      Field.maxArea = true;

    }
  }

  HelperCommand help = new HelperCommand(false);

  public void test(){
      
      help.start();
      
  }

  public void endTest(){
    help.cancel();
  }

  
}