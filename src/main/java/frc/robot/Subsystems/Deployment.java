package frc.robot.Subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode; 

import frc.robot.Field;


public class Deployment{
    public static WPI_TalonSRX deploymentR = new WPI_TalonSRX(RobotMap.deploymentPort);
    public static WPI_TalonSRX deploymentL = new WPI_TalonSRX(RobotMap.deployment2Port);

    public Deployment(){

    }

    public static enum DeploymentPreset{
        //random values
        TOP(20.0),
        BOTTOM(0.0);

        public double deploymentPosition;
        private DeploymentPreset(double deploymentPosition){
        this.deploymentPosition = deploymentPosition;
        }
    }

    public void DeploymentGo(double location, double speed){
        
    }

    public void moveto(double pos){
        
    }

    public void setPosition(DeploymentPreset position){
        
    }

    public void setSpeed(double speed1, double speed2){
        deploymentR.set(speed1);
        deploymentL.set(-speed2);
    }

    public void setSpeedL(double speed){
        if(getLDeployPosition()>=107000){
            if(speed>0){
                deploymentL.set(speed/2);
            }
            else{
                deploymentL.set(0);
            }

        }
        else if(getLDeployPosition()<=50){
            if(speed<0){
                deploymentL.set(speed);
            }
            else{
                deploymentL.set(0);
            }
        }
        else{
            if(speed>0){
                deploymentL.set(speed/2);
            }
            else{
                deploymentL.set(speed);
            }
        }

        // deployment.set(speed);
    }
    
    public void setSpeedR(double speed){
        if(getRDeployPosition()>=107000){
            if(speed>0){
                deploymentR.set(speed/2);
            }
            else{
                deploymentR.set(0);
            }

        }
        else if(getRDeployPosition()<=50){
            if(speed<0){
                deploymentR.set(speed);
            }
            else{
                deploymentR.set(0);
            }
        }
        else{
            if(speed>0){
                deploymentR.set(speed/2);
            }
            else{
                deploymentR.set(speed);
            }
        }
        // deployment2.set(speed);
    }
    
    public void off(){
        // deployment.set(0);
        // deployment2.set(0);
    }

    public int getRDeployPosition() {
        return deploymentR.getSelectedSensorPosition();
    }

    public int getLDeployPosition() {
        return deploymentL.getSelectedSensorPosition();
    }


  
}
