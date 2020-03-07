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
        double speed = hoodc.getMotorOutputPercent();
        if(this.getHoodPosition() > 6003 - 50) { //6400 is all the way up
            if (speed < 0){

                hoodc.set(ControlMode.PercentOutput, 0);
            }
        }
        else {
            // if(HAL.limelight.getDistanceFromTarget() > 290){
                //   moveto(3425);

            // }
            // else if(HAL.limelight.getDistanceFromTarget() < 100){
                //   moveto(0);

            // }
            // else{
                // hoodc.set(ControlMode.PercentOutput, -0.15);

            // }
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


    public void moveto(double pos){
        hoodc.set(ControlMode.MotionMagic, pos);
      }


    public void off (){
        hoodc.set(ControlMode.PercentOutput, 0);
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


    public int getHoodPosition(){
        return hoodc.getSelectedSensorPosition();
    }
}