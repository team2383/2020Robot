package frc.robot.Subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;

public class Trigger {
    WPI_VictorSPX trigger = new WPI_VictorSPX(RobotMap.triggerPort);

    public Trigger() 
    {}
    
    public void spinLow (){
        trigger.set(ControlMode.PercentOutput, -0.15);
    }

    public void spinMedium (){
        trigger.set(ControlMode.PercentOutput, -0.5);
    }

    public void spinHigh (){
        trigger.set(ControlMode.PercentOutput, -1.0);
    }

    public void spinOut (){
        trigger.set(ControlMode.PercentOutput, 0.4);
    }

    public void off (){
        trigger.set(ControlMode.PercentOutput, 0);
    }
}