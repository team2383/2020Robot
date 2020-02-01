package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;


public class Feeder{

  WPI_VictorSPX feeder = new WPI_VictorSPX(RobotMap.feedPort);

  public Feeder()
  {}
  
  public void feed(){
    feeder.set(ControlMode.PercentOutput, -1.0);
  }

  public void off(){
    feeder.set(ControlMode.PercentOutput, 0);
  }

  public void unfeed(){
    feeder.set(ControlMode.PercentOutput, 1.0);
  }
}
