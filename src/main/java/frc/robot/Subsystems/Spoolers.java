package frc.robot.Subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode; 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import static frc.robot.HAL.drive;
import frc.robot.Subsystems.*;
public class Spoolers{
    public static WPI_VictorSPX spoolL = new WPI_VictorSPX(RobotMap.spoolLPort);
    public static WPI_VictorSPX spoolR = new WPI_VictorSPX(RobotMap.spoolRPort);

    public static boolean both = false;

    public Spoolers(){
        configmotorcontroller();
    }

    public void configmotorcontroller(){
        spoolL.setNeutralMode(NeutralMode.Brake);
        spoolR.setNeutralMode(NeutralMode.Brake);
    }
    public void setL(double speedL){
        spoolL.set(speedL);
    }

    public void setR(double speedR){
        spoolR.set(speedR);
    }

    public void setB(double speedB){
        spoolL.set(speedB);
        spoolR.set(speedB);
    }

    public void toggle(){
        both = true;
    }
    
    public void off(){
        spoolL.set(0);
        spoolR.set(0);
    }

    public void both(){
        if(both=true){
            setB(drive.getoutput());
        }
        else{
            off();
        }
    }





  
}
