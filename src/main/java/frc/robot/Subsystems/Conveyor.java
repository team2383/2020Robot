package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotMap;


public class Conveyor{

  WPI_VictorSPX conveyor = new WPI_VictorSPX(RobotMap.conveyPort);
  Timer timer = new Timer();

  public Conveyor() 
  {}
  
  public void pull(){
    conveyor.set(ControlMode.PercentOutput, .250);
  }

  public void off(){
    conveyor.set(ControlMode.PercentOutput, 0);
  }

  public void out(){
    conveyor.set(ControlMode.PercentOutput, -0.80);
  }

  public void interval_conveyor(double interval){
    double startTime = Timer.getMatchTime();
    this.pull();
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
