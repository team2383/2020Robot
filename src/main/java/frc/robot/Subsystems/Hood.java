package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotMap;
import frc.robot.HAL;


public class Hood {
   public static WPI_TalonSRX hoodc = new WPI_TalonSRX(RobotMap.hoodPort);
    public Hood() 
    {}

    public void slowMoveUP (){
        hoodc.set(ControlMode.PercentOutput, 0.15);
    }

    public void slowMoveDown (){
        hoodc.set(ControlMode.PercentOutput, -0.15);
    }

    public void moveto(double pos){
        hoodc.set(ControlMode.MotionMagic, pos);
      }

    public void off (){
        hoodc.set(ControlMode.PercentOutput, 0);
    }

    public boolean isangled (){
        double tolerance = 5; //random value
        return HAL.limelight.xOffset() < tolerance;
    }

    
    public void limeH(){
        double area = HAL.limelight.area();
        double factor = 8; //random value
        double height;
        height = area*factor;
            moveto(height);
        }


public boolean isangledY (){
    double toleranceY = 100; //random value
    return ((hoodc.getSelectedSensorPosition()) - (HAL.limelight.area()*8)) < toleranceY;
}

public void periodic() {
    }
}