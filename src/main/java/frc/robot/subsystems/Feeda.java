package frc.robot.subsystems;

import frc.robot.ninjaLib.*;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;


public class Feeda extends StatefulSubsystem<Feeda.State> {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  TalonSRX feeda = new TalonSRX(Constants.intakePort);

  public void configMotorController(int timeout){
    feeda.config_kP(0, 0, timeout);
    feeda.config_kI(0, 0, timeout);
    feeda.config_kD(0, 0, timeout);
    feeda.config_kF(0, 0, timeout);

    feeda.configMotionAcceleration(4000, timeout);
    feeda.configMotionCruiseVelocity(4000, timeout);
    feeda.setNeutralMode(NeutralMode.Brake);

    feeda.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeout);

  }

  public enum State{
    EATIN,
    SPITTIN,
    VIBIN
  }

  
  public Feeda()
  {
   
  }

  public int getCurrentPosition() {
    return feeda.getSelectedSensorPosition(0);
  }

  public void setPosition(int position){
    feeda.set(ControlMode.MotionMagic, position);
  }

  public void setSpeed(double speed){
    feeda.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void setState(State state) {
		switch (state) {
      case EATIN:
        //runs at 50%
        feeda.set(ControlMode.PercentOutput, 1.0); //normally 0.8 SoFlo
        break;
        case SPITTIN:
        //runs at 50%
        feeda.set(ControlMode.PercentOutput, -1.0); //normally -0.5
        break;  
      default:
      case VIBIN:
        feeda.set(ControlMode.PercentOutput,0);
        break;
    }
    }

  @Override
  public void initDefaultCommand() {
    
  }
}