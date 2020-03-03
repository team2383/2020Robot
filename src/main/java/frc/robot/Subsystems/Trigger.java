package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.RobotMap;
import frc.robot.ninjaLib.StatefulSubsystem;


public class Trigger extends StatefulSubsystem<Trigger.State> {
    WPI_VictorSPX trigger = new WPI_VictorSPX(RobotMap.triggerPort);

    public Trigger() 
    {
      configMotorController();
    }
    public void configMotorController(){
      trigger.setNeutralMode(NeutralMode.Brake);
      trigger.setInverted(true);
    }
    
    public enum State{
        SPIN,
        RSPIN,
        STOP,
      }
    public void spinLow (){
        trigger.set(ControlMode.PercentOutput, -0.15);
    }

    public void spinMedium (){
        trigger.set(ControlMode.PercentOutput, -1);
    }

    public void spin (double speed){
        trigger.set(ControlMode.PercentOutput, speed);
    }

    public void spinOut (){
        trigger.set(ControlMode.PercentOutput, 1);
    }

    public void interval_trigger(double interval){
      //System.out.println("start");
      double startTime = Timer.getFPGATimestamp();
      this.spinLow();
      while(Timer.getFPGATimestamp() <= (startTime + interval)) {
        ;
      }
      this.off();
      System.out.println("end");
    }


    public void off (){
        trigger.set(ControlMode.PercentOutput, 0);
    }

    public void setState(State state){
    
        switch (state) {
          case RSPIN:
            spin(-0.6);
            break;
          case SPIN:
            spin(0.6);
            break;
        default:
          case STOP:
            off();
          }
        }

        public void initDefaultCommand() { 
        }
}