package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Telescope{


  CANSparkMax TelescopeDeploy = new CANSparkMax(RobotMap.telescopePort, MotorType.kBrushless);
  CANEncoder teleEncoder = new CANEncoder(TelescopeDeploy);

  //WPI_VictorSPX TelescopeDeploy = new WPI_VictorSPX(RobotMap.telescopePort);
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
    //TelescopeDeploy.set(ControlMode.MotionMagic, position.telescopePosition);
    //TelescopeDeploy.
  }

  public void TelescopeGo(){
    TelescopeDeploy.set(0.75);
  }
  public void off(){
    TelescopeDeploy.set(0);
  }

  //@Override
  public double getPosition() {
    // SmartDashboard.putNumber("ep", teleEncoder.getPosition());
    return teleEncoder.getPosition();
  }


}
