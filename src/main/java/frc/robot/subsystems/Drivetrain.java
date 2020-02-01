package frc.robot.Subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import frc.robot.RobotMap;

public class Drivetrain{
  
  DifferentialDrive drive;

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
    rightMaster.setInverted(true);
    rightSlave.setInverted(true);
    leftMaster.setNeutralMode(NeutralMode.Brake);
    leftSlave.setNeutralMode(NeutralMode.Brake);
    rightMaster.setNeutralMode(NeutralMode.Brake);
    rightSlave.setNeutralMode(NeutralMode.Brake);
  }
  public void tank(double leftValue, double rightValue) {
		drive.tankDrive(leftValue, rightValue, false);
  }
  
  public void arcade(double move, double turn){
    drive.arcadeDrive(move,turn);
  }

  public double getRightPosition() {
    return leftSlave.getSelectedSensorPosition();
  }

  public double getLeftPosition(){
    return rightSlave.getSelectedSensorPosition();
  }

  public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(0, 0, 0);
		rightMaster.setSelectedSensorPosition(0, 0, 0);
	}
}