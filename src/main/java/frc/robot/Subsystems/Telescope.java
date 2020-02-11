package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;


public class Telescope{

  WPI_VictorSPX TelescopeDeploy = new WPI_VictorSPX(RobotMap.telescopePort);

  public Telescope(){

  }
  public static enum TelescopePreset{
    //random values
    TOP(0),
    BOTTOM(0);

    public double telescopePosition;
    private TelescopePreset(double telescopePosition){
      this.telescopePosition = telescopePosition;
    }
  }
  public void setPosition(TelescopePreset position){
    TelescopeDeploy.set(ControlMode.MotionMagic, position.telescopePosition);

  }

  public void TelescopeGo(){
    TelescopeDeploy.set(ControlMode.PercentOutput, 0.5);
  }
  public void off(){
    TelescopeDeploy.set(ControlMode.PercentOutput, 0);
  }


}
