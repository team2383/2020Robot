package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;
import frc.robot.Field;

public class Conveyor{

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

  public void off(){
    conveyor.set(ControlMode.PercentOutput, 0);
  }

  public void out(){
    conveyor.set(ControlMode.PercentOutput, -0.50);
  }
}