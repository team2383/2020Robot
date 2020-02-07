package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotMap;
import frc.robot.HAL;


public class Hood {
   public static WPI_TalonSRX hoodc = new WPI_TalonSRX(RobotMap.hoodPort);
   
    public Hood() 
    {
        hoodc.setSensorPhase(true);
        
    }

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
        boolean hasTarget = HAL.limelight.hasTargets();
        double factor = 8; //random value
        double height;
        height = area*factor;

        if(hoodc.getSelectedSensorPosition()>4900) {
            moveto(4850);
        }

        else if(hoodc.getSelectedSensorPosition()<0){
            moveto(50);
        }

        else{
            if(hasTarget){
                moveto(height); 
            }
            else{
                moveto(0);
            }
        }

        
    }


    public boolean isangledY (){
        double toleranceY = 100; //random value
        return ((hoodc.getSelectedSensorPosition()) - (HAL.limelight.area()*8)) < toleranceY;
    }

    public int getCurrentPosition(){
        return hoodc.getSelectedSensorPosition();
    }

    public void periodic() {
        SmartDashboard.putNumber("height", HAL.limelight.area() * 8);
        }
}