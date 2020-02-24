package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;
import frc.robot.ninjaLib.StatefulSubsystem;
import frc.robot.Field;

public class Conveyor extends StatefulSubsystem<Conveyor.State>{

  WPI_VictorSPX conveyor = new WPI_VictorSPX(RobotMap.conveyPort);

  public Conveyor(){}
  
  public void pull(){
    if (Field.operatorCool){
      conveyor.set(ControlMode.PercentOutput, .80);
    }
    else{
      conveyor.set(ControlMode.PercentOutput, 0);
    }
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

  public void off(){
    conveyor.set(ControlMode.PercentOutput, 0);
  }

  public void out(){
    conveyor.set(ControlMode.PercentOutput, -0.50);
  }

  public void spin(double speed){
    conveyor.set(ControlMode.PercentOutput, speed);
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