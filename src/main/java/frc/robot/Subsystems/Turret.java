package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;
import frc.robot.HAL;

public class Turret{

  WPI_TalonSRX turret = new WPI_TalonSRX(RobotMap.turretPort);

  public Turret()
  {}
  
  public void move(double stick){
    turret.set(ControlMode.PercentOutput, stick);
  }

  public void limeT(double pipeline){
    double output;
    double xOffset = HAL.limelight.xOffset();
    double stop = 0;
    double dividend = 27;
    output = (xOffset/dividend); 
    if (HAL.limelight.hasTargets()){
      HAL.limelight.setPipeline(pipeline);
      turret.set(ControlMode.PercentOutput, output);
    }
    else{
      HAL.limelight.setPipeline(pipeline);
      turret.set(ControlMode.PercentOutput, stop);
    }
  }

  public void off(){
    turret.set(ControlMode.PercentOutput, 0);

  }
}



