package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;


public class Telescope{

  WPI_VictorSPX TelescopeDeploy = new WPI_VictorSPX(RobotMap.telescopePort);

  public Telescope(){

  }

  public void TelescopeGo(){
    TelescopeDeploy.set(ControlMode.PercentOutput, 0.5);
  }
  public void off(){
    TelescopeDeploy.set(ControlMode.PercentOutput, 0);
  }


}
