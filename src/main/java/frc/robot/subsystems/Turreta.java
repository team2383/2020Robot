package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Constants;


public class Turreta extends Subsystem {

  public TalonSRX turreta = new TalonSRX(Constants.turretPort);

  public void periodic(){
    SmartDashboard.putNumber("TurretPos", turreta.getSelectedSensorPosition());
  }
 
  @Override
  public void initDefaultCommand() {  
    
    //setDefaultCommand(new HoldTurreta(true));
  }
  public void setSpeed(double speed){
    turreta.set(ControlMode.PercentOutput, speed);
  }
 

  // Method that will stop the clock motor when called
  public void stop() {
    turreta.set(ControlMode.PercentOutput, 0);
  }

}