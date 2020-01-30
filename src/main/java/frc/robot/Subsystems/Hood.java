package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;

public class Hood {
    WPI_TalonSRX hood = new WPI_TalonSRX(RobotMap.hoodPort);
    public Hood() {

    }
    public void slowMoveUP (){
        hood.set(ControlMode.PercentOutput, 0.15);
    }

    public void slowMoveDown (){
        hood.set(ControlMode.PercentOutput, -0.15);
    }

    public void off (){
        hood.set(ControlMode.PercentOutput, 0);
    }
}