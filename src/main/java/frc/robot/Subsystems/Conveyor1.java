package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Conveyor1{

  WPI_VictorSPX conveyor1 = new WPI_VictorSPX(7);

  public Conveyor1(){
  }
  
  public void pull(){
    conveyor1.set(ControlMode.PercentOutput, -0.95);
  }

  public void off(){
    conveyor1.set(ControlMode.PercentOutput, 0);
  }

  public void out(){
    conveyor1.set(ControlMode.PercentOutput, 0.95);
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
}
