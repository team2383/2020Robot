package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import java.lang.Object;

import frc.robot.RobotMap;
import frc.robot.ninjaLib.StatefulSubsystem;


public class Feeder extends StatefulSubsystem<Feeder.State>{

  WPI_VictorSPX feeder = new WPI_VictorSPX(RobotMap.feedPort);

  public Feeder()
  {
    feeder.setInverted(true);
  }
  
  public enum State{
    STOP,
    SLOW,
    MID,
    FAST,
    RSLOW,
    RMID,
    RFAST
  }

  
  public void feed(){
    feeder.set(ControlMode.PercentOutput, 1.0);
  }

  public void off(){
    feeder.set(ControlMode.PercentOutput, 0);
  }

  public void fire(){
    feeder.set(ControlMode.PercentOutput, 0.3);
  }

  public void unfeed(){
    feeder.set(ControlMode.PercentOutput, -0.45);
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
    //double startTime = Timer.getMatchTime();
    double startTime = Timer.getFPGATimestamp();
    this.feed();
    while(!(Timer.getFPGATimestamp() > (startTime - interval))) {
      ;
    }
    this.off();
    startTime = Timer.getMatchTime();
    while(!(Timer.getMatchTime() > (startTime - interval))) {
      ;
    }
  }

public double displayTimer(){
  double startTime2 = Timer.getFPGATimestamp();
  return startTime2;
}

public void setState(State state){
    
  switch (state) {
    case SLOW:
      spin(0.3);
      break;
    case MID:
      spin(0.6);
      break;
    case FAST:
      spin(0.8);
      break;
    case RSLOW:
      spin(-0.3);
      break;
    case RMID:
      spin(-0.6);
      break;
    case RFAST:
      spin(-0.8);
      break;
  default:
    case STOP:
      off();
    }
  }
  
  public void initDefaultCommand() { 
  }
}
