/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.*;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
/**
 * Add your docs here.
 */
public class Drivetrain{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  
  DifferentialDrive drive;

  WPI_TalonFX rightMaster = new WPI_TalonFX(3);
  WPI_TalonFX rightSlave = new WPI_TalonFX(4);
  WPI_TalonFX leftMaster = new WPI_TalonFX(2);
  WPI_TalonFX leftSlave = new WPI_TalonFX(1);

  public Drivetrain (){
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
public void setBrake() {
  //leftMaster.configAllSettings(allConfigs, timeoutMs)
}

}