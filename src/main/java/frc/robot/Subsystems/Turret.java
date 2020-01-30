package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Turret{

  WPI_TalonSRX turret = new WPI_TalonSRX(14);

  public Turret(){

  }
  
  public void move(double stick){
    turret.set(ControlMode.PercentOutput, stick);

  }

  public void off(){
    turret.set(ControlMode.PercentOutput, 0);

  }

//   public void unfeed(){
//     feeder.set(ControlMode.PercentOutput, 0.80);

//   }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


}
