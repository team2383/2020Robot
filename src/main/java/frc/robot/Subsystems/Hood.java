package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.RobotMap;
import frc.robot.HAL;

public class Hood {
   public static WPI_TalonSRX hoodc = new WPI_TalonSRX(RobotMap.hoodPort);
   
    public Hood() 
    {
        hoodc.setSensorPhase(false);
        hoodc.setInverted(false);
    }


    public void slowMoveUP (){
        // up is negative % output
        double speed = hoodc.getMotorOutputPercent();
        if(this.getHoodPosition() > 6003 - 50) { //6400 is all the way up
            if (speed < 0){
                hoodc.set(ControlMode.PercentOutput, 0);
            }
        }
        else {
  
            hoodc.set(ControlMode.PercentOutput, -0.15);
        }
        // hoodc.set(ControlMode.PercentOutput, -0.15);
    }


    public void slowMoveDown (){
        double speed = hoodc.getMotorOutputPercent();
        if(this.getHoodPosition() < 50) {
            if (speed > 0){
                hoodc.set(ControlMode.PercentOutput, 0);
            }
        }
        else {
            hoodc.set(ControlMode.PercentOutput, 0.15);
        }
        // hoodc.set(ControlMode.PercentOutput, 0.15);
    }
    public void johnIsJohn(){
        double speed = hoodc.getMotorOutputPercent();
        if(this.getHoodPosition() > -6003 + 50 && speed < 0) { //6400 is all the way up
            hoodc.set(ControlMode.PercentOutput, 0);
        }
        else if(this.getHoodPosition() < -50 && speed > 0) {
                hoodc.set(ControlMode.PercentOutput, 0);
        }

        else if(HAL.limelight.area() >= 4.2){
            moveto(-55);
        }
        else if(HAL.limelight.area() <= 1.2){
            moveto(-3425);
        }
   
    }


    public void moveto(double pos){
        hoodc.set(ControlMode.Position, pos);
      }


    public void off (){
        hoodc.set(ControlMode.PercentOutput, 0);
    }

    
    // public void limeH(){
    //     double area = HAL.limelight.area();
    //     boolean hasTarget = HAL.limelight.hasTargets();
    //     double factor = 8; //random value
    //     double height;
    //     height = area*factor;

    //     if(hasTarget){
    //         if (getHoodPosition() > 3700 && hoodc.getMotorOutputPercent() < 0){
    //             hoodc.set(ControlMode.PercentOutput, 0);
    //         }
    //         else if (getHoodPosition() < -2700 && hoodc.getMotorOutputPercent() > 0){
    //             hoodc.set(ControlMode.PercentOutput, 0);
    //         }
    //         else{
    //            moveto(height);
    //         } 
    //     }
    //     else{
    //         moveto(100);
    //     }
    // }


    public int getHoodPosition(){
        return hoodc.getSelectedSensorPosition();
    }
}