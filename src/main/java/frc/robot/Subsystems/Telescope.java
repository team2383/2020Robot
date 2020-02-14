package frc.robot.Subsystems;


import frc.robot.RobotMap;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Telescope{


  CANSparkMax TelescopeDeploy = new CANSparkMax(RobotMap.telescopePort, MotorType.kBrushless);
  CANEncoder teleEncoder = new CANEncoder(TelescopeDeploy);
  CANPIDController telePID = new CANPIDController(TelescopeDeploy);

  public Telescope()
  {}
  public static enum TelescopePreset{
    //random values
    TOP(1.5),
    BOTTOM(1.5);

    public double telescopePosition;
    private TelescopePreset(double telescopePosition){
      this.telescopePosition = telescopePosition;
    }
  }

  public void TelescopeGo(double location, double speed){
    if (teleEncoder.getPosition() != location){
    TelescopeDeploy.set(speed);
    }
    else TelescopeDeploy.set(0);
  }

  public void moveto(double pos){
    telePID.setReference(pos, ControlType.kSmartMotion);
  }
  public void setPosition(TelescopePreset position){
    telePID.setReference(position.telescopePosition, ControlType.kSmartMotion);
  }
  public void off(){
    TelescopeDeploy.set(0);
  }

  public double getPosition() {
    return teleEncoder.getPosition();
  }


  
}
