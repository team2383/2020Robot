package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.RobotMap;
import frc.robot.HAL;

public class Hood {
    WPI_TalonSRX hood = new WPI_TalonSRX(RobotMap.hoodPort);
    public Hood() 
    {}

    public void slowMoveUP (){
        hood.set(ControlMode.PercentOutput, 0.15);
    }

    public void slowMoveDown (){
        hood.set(ControlMode.PercentOutput, -0.15);
    }

    public void moveto(double pos){
        hood.set(ControlMode.MotionMagic, pos);
      }

    public void off (){
        hood.set(ControlMode.PercentOutput, 0);
    }

    public boolean isangled (){
        double tolerance = 5; //random value
        return HAL.limelight.xOffset() < tolerance;
    }

    
    public void limeH(){
        double area = HAL.limelight.area();
        double factor = 5;
        double height;
        height = area*factor;
        if(isangled()){
            moveto(height);
        }
        else{
            off();
        }
}

public boolean isangledY (){
    double toleranceY = 100; //random value
    return ((hood.getSelectedSensorPosition()) - (HAL.limelight.area()*5)) < toleranceY;
}

public void periodic() {
    SmartDashboard.putBoolean("Ready to Shoot?", isangledY());
}
}