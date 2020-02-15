package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.HAL;
import frc.robot.RobotMap;


public class Shooter{

  WPI_TalonFX shootMaster = new WPI_TalonFX(RobotMap.shooterMasterPort);
  WPI_TalonFX shootFollower = new WPI_TalonFX(RobotMap.shooterFollowerPort);

  public Shooter(){
    //configMotorController(output);
    shootFollower.follow(shootMaster);
  }
  public void out(double power){
    //configMotorController();
    // shootFollower.follow(shootMaster);
    shootMaster.set(-power);
    //shootFollower.set(power);
  }
  public double desiredRPM = 6000; //Max RPM is around 5800

  public void Run() {
    //configMotorController();
    double velocity = desiredRPM * 2048.0 / 600.0 * (38.0/48.0); //was 2048
    shootMaster.set(ControlMode.Velocity, -velocity);
  }

  public static double rpmToNative(double rpm, double countsPerRevolution){
    return rpm * countsPerRevolution / 600.0;
}

  public double differential(){
    double currentRPM = nativeToRPM(shootMaster.getSelectedSensorVelocity(), 2048);
    return desiredRPM + currentRPM;
  }
  public static double nativeToRPM(double nativeUnits, double countsPerRevolution) {
    return nativeUnits * 600.0 / 2048;
  }
  public double getRPM() {
    double currentRPM = nativeToRPM(shootMaster.getSelectedSensorVelocity(), 2048);
    return currentRPM;
  }

  public double getPercentageOutput(){
    return shootMaster.getMotorOutputPercent();
  }
public double getClosedLoopError(){
  return shootMaster.getClosedLoopError();
}

  public void configMotorController(double nativeoutput){
    double maxvel = 21000.0;
    double currentvel = shootMaster.getSelectedSensorVelocity();
    double error = ((maxvel*nativeoutput) - currentvel);
    double clerror = shootMaster.getClosedLoopError();
    shootMaster.config_kP(0, 1/(Math.pow(clerror, 2)));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
  //  shootMaster.config_kI(0,0.0);
    shootMaster.config_kD(0,0.0);
 //   shootMaster.config_kF(0, (nativeoutput * 1023)/maxvel);
    //shootMaster.config_IntegralZone(0, 9);

    shootMaster.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    // shootFollower.follow(shootMaster);
    // shootMaster.configClosedloopRamp(.25);

  //  int smartMotionSlot = 0;
    //shootMaster.configMotionCruiseVelocity(100, smartMotionSlot);
    //shootMaster.configMotionAcceleration(100, smartMotionSlot);


    //shootMaster.setInverted(true);
   //shootFollower.setInverted(false);
    // shootMaster.setSensorPhase(false);
    
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
    configMotorController(output);
    shootMaster.set(output);
    shootFollower.set(-output);
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

