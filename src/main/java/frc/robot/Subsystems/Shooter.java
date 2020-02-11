package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.HAL;
import frc.robot.RobotMap;

import com.revrobotics.CANAnalog.AnalogMode;


public class Shooter{

  WPI_TalonFX shootMaster = new WPI_TalonFX(RobotMap.shooterMasterPort);
  WPI_TalonFX shootFollower = new WPI_TalonFX(RobotMap.shooterFollowerPort);

  //CANEncoder shooterz = new CANEncoder(shootMaster);
  // public CANPIDController shootController = new CANPIDController(shootMaster);
  // public CANPIDController shootControllerFollower = new CANPIDController(shootFollower);
//uncomment
  public Shooter(){
    //configMotorController();
    shootFollower.follow(shootMaster);
  }
  public void out(double power){
    //configMotorController();
    //shootFollower.follow(shootMaster);
    shootMaster.set(power);
    //shootFollower.set(power);
  }
  public void configMotorController(){
    //random value
    shootMaster.config_kP(0, 0);
    shootMaster.config_kI(0,0);
    shootMaster.config_kD(0,0);

    shootMaster.config_IntegralZone(0,0);
    shootMaster.config_kF(0,0);

    int smartMotionSlot = 0;
    shootMaster.configMotionCruiseVelocity(12000, smartMotionSlot);
    shootMaster.configMotionAcceleration(8000, smartMotionSlot);
    
    }

  public double getShooterVelocity(){
    return shootMaster.getSelectedSensorVelocity();
  }

  public void stop(){
    shootMaster.set(0);
    shootFollower.set(0);
  }
  public void in(){
    shootFollower.set(0.75);
    shootMaster.set(0.75);
  }  

  public void shoot(double output){
    shootMaster.set(output);
    shootFollower.set(output);
  }

  public void LimeS(){
  double area = HAL.limelight.area();
  double zone1areathreshold = 20; //random
  double zone2areathreshold = 40; //random
  double zone1output = 0.5; //random
  double zone2output = 0.7; //random
  double zone3output = 0.88; //random

  if (HAL.limelight.area() <= zone1areathreshold){
    shoot(zone1output);
  }

  else if (zone1areathreshold < area && area <= zone2areathreshold){
    shoot(zone2output);
  }

  else{
    shoot(zone3output);
    }
  }

  public void manualorlime(){
  boolean tracking = HAL.limelight.hasTargets();
  double manualoutput = 0.85;

  if (tracking){
    LimeS();
  }
    else{
      shoot(manualoutput);
    }
}
}

