package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import static frc.robot.HAL.limelight;

import edu.wpi.first.wpilibj.Timer;

public class Drivetrain{
  
  DifferentialDrive drive;
  double limeDegree;
  public boolean run=false;

  WPI_TalonFX rightMaster = new WPI_TalonFX(RobotMap.rightmasterPort);
  WPI_TalonFX rightSlave = new WPI_TalonFX(RobotMap.rightfollowerPort);
  WPI_TalonFX leftMaster = new WPI_TalonFX(RobotMap.leftmasterPort);
  WPI_TalonFX leftSlave = new WPI_TalonFX(RobotMap.leftfollowerPort);

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

  public void run(double speed){
    this.arcade(speed, 0);
  }

  public void distance_drive(double distance){
    System.out.println("start");
    double speed=0;
    if(distance<0){
      speed=-0.3;
    }else{
      speed=0.3;
    }
    double startPos= this.getLeftPosition();
    while(Math.abs(this.getLeftPosition()-startPos)<Math.abs(this.getLeftPosition()-startPos+distance)) {
      this.arcade(0, speed);
    }
    this.arcade(0, 0);
    System.out.println("end");
    run=false;
  }

  public void endRun(){
    run=true;
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

  public void limeAlign() {
    if(limelight.xOffset() >= 3){
      arcade(0.4,0);
    }
    else if(limelight.xOffset() <= -3){
      arcade(-0.4,0);
    }
    //double divisor = 30;
    //arcade(offset/divisor,0);
  }

  public boolean pivoted(){
    double offset = limelight.xOffset();
    return Math.abs(offset) < 0.5;
  }

  public void limeApache() {
    double xOffset = limelight.xOffset();
    double area = limelight.area();
    double approachSpeed  = .32 / area;
    boolean hasTarget = limelight.hasTargets();
    if (xOffset > -27 && xOffset <= -4 && hasTarget == true){
        arcade(approachSpeed + .22, ((xOffset-0.5)/27)-.07);
    }  
    else if (xOffset < 27 && xOffset >= 4 && hasTarget == true){
        arcade(approachSpeed + .22, ((xOffset+0.5)/27)+.07);
    }
    else if (xOffset > -4 && xOffset < 0 && hasTarget == true){
      arcade(approachSpeed + .22, ((xOffset-0.5)*2/27)-.07);
    }
    else if (xOffset >= 0 && xOffset < 4 && hasTarget == true){
      arcade(approachSpeed + .22, ((xOffset+0.5)*2/27)+.07);
    }
    else{
      arcade(0, 0.0);
    }
  }

  
}