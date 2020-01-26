package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.*;
// import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.OI;
import frc.robot.commands.*;



public class Turreta extends Subsystem {

  public TalonSRX Turreta = new TalonSRX(10);

 
  @Override
  public void initDefaultCommand() {  
    
    setDefaultCommand(new HoldTurreta());
  }
  public void setSpeed(double speed){
    Turreta.set(ControlMode.PercentOutput, speed);
  }
 

  // Method that will stop the clock motor when called
  public void stop() {
    Turreta.set(ControlMode.PercentOutput, 0);
  }

}