package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;


public class Drivetrain{
  
  DifferentialDrive drive;
  double limeDegree;

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
  public double getLimeDegree(){
    return limeDegree;
  }
  public void setLimeDegree(double degree){
    this.limeDegree = degree;
  }
  public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(50, 0, 0);
    rightMaster.setSelectedSensorPosition(50, 0, 0);
    
  }
  public double getLeftVelocity() {
		return leftMaster.getSelectedSensorVelocity(0); // 16384.0 * RobotMap.getWheelCircumference();
	}
	public double getRightVelocity() {
		return rightMaster.getSelectedSensorVelocity(0); // 16384.0 * RobotMap.getWheelCircumference();
  }
}