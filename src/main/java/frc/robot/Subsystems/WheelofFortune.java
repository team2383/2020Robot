package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;


public class WheelofFortune{

  WPI_VictorSPX wheelGo = new WPI_VictorSPX(RobotMap.wheelPort);

  public WheelofFortune(){

  }

  public void wheelie(){
    wheelGo.set(ControlMode.PercentOutput, 0.5);
  }
  public void off(){
    wheelGo.set(ControlMode.PercentOutput, 0);
  }


}
