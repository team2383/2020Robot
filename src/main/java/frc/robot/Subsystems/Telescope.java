package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;

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
    //TelescopeDeploy.
  }

  public void TelescopeGo(double stick){
    if (teleEncoder.getPosition() < 5.5){
    TelescopeDeploy.set(stick);}
    else TelescopeDeploy.set(0);
  }
  public void off(){
    TelescopeDeploy.set(0);
  }


  //@Override
  public double getPosition() {
    // SmartDashboard.putNumber("ep", teleEncoder.getPosition());
    return teleEncoder.getPosition();
  }
  // @Override
  // public void telePeriodic(){
  //   TelescopeDeploy.setPeriodicFrameRate(PeriodicFrame.kStatus2,10);
  // }

  
}
