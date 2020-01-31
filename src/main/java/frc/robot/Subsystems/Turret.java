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

  public void slimelight(double height){
    double output;
    double xOffset = HAL.slimelight.xOffset();
    double kP = 1;
    double stop = 0;

    if(height == 1){
    output = kP * ((xOffset - 8.5-2.5)/27); 
    }
    else{
      output = kP * ((xOffset - 12.8)/27); //was 13
    }

    if (HAL.slimelight.hasTargets()){
      turret.set(output);
    }
    else{
      turret.set(stop);
    }
  }
  public void off(){
    turret.set(ControlMode.PercentOutput, 0);

  }
}



