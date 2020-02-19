package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotMap;


public class Feeder{

  WPI_VictorSPX feeder = new WPI_VictorSPX(RobotMap.feedPort);
  Timer timer = new Timer();

  public Feeder()
  {}
  
  public void feed(){
    feeder.set(ControlMode.PercentOutput, 0.7);
  }

  public void off(){
    feeder.set(ControlMode.PercentOutput, 0);
  }

  public void unfeed(){
    feeder.set(ControlMode.PercentOutput, -0.25);
  }

  public void spin(double speed){
    feeder.set(ControlMode.PercentOutput, speed);
  }
  public void toggle(){
    boolean moving = feeder.get() != 0;
    if (!moving){
    feed();
    }
    else off();
  }

  public void interval_feed(double interval){
    double startTime = Timer.getMatchTime();
    this.feed();
    while(!(Timer.getMatchTime() > (startTime - interval))) {
      ;
    }
    this.off();
    startTime = Timer.getMatchTime();
    while(!(Timer.getMatchTime() > (startTime - interval))) {
      ;
    }
  }
}
