package frc.robot.Subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode; 

public class Deployment{
    public static WPI_TalonSRX deployment = new WPI_TalonSRX(RobotMap.deploymentPort);

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

    public void setSpeed(double speed){
        deployment.set(speed);
    }
    
    public void off(){
        deployment.set(0);
    }

    public int getPosition() {
        return deployment.getSelectedSensorPosition();
    }


  
}
