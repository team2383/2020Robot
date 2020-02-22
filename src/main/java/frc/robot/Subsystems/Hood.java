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
        double speed = hoodc.getMotorOutputPercent();
        if(this.getHoodPosition() > 3500) {
            if (speed < 0){
                hoodc.set(ControlMode.PercentOutput, 0);
            }
            // else{
            //     hoodc.set(ControlMode.PercentOutput, -0.15);
            // }
        }


        else {
            hoodc.set(ControlMode.PercentOutput, -0.15);
        }


    }

    public void slowMoveDown (){
        //hoodc.set(ControlMode.PercentOutput, 0.15);
        double speed = hoodc.getMotorOutputPercent();
        if(this.getHoodPosition() < 50) {
            if (speed > 0){
                hoodc.set(ControlMode.PercentOutput, 0);
            }
        }
        else {
            hoodc.set(ControlMode.PercentOutput, 0.15);
        }
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

        if(hasTarget){
            if (getHoodPosition() > 3700 && hoodc.getMotorOutputPercent() < 0){
                hoodc.set(ControlMode.PercentOutput, 0);
          
            }
            else if (getHoodPosition() < -2700 && hoodc.getMotorOutputPercent() > 0){
                hoodc.set(ControlMode.PercentOutput, 0);
          
            }
            else{
               moveto(height);
          
            } 
        }
        else{
            moveto(100);
        }
        
    }


    public boolean isangledY (){
        double toleranceY = 100; //random value
        return ((hoodc.getSelectedSensorPosition()) - (HAL.limelight.area()*8)) < toleranceY;
    }

    public int getHoodPosition(){
        return hoodc.getSelectedSensorPosition();
    }

    public void periodic() {
        SmartDashboard.putNumber("height", HAL.limelight.area() * 8);
        }
}