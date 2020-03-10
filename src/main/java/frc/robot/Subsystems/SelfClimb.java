package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;


public class SelfClimb{
    
    int toggle=1;
    public boolean run=false;
    DoubleSolenoid solenoid4 = new DoubleSolenoid(RobotMap.s4fchannel, RobotMap.s4rchannel);


    public SelfClimb(){

    }

    public void prepClimb(){
        run=true;
    }

    public void stopClimb(){
        if (toggle>0){
            solenoid4.set(Value.kForward);
        }else{
            solenoid4.set(Value.kReverse);
        }
        run=false;
        toggle*=-1;
    }

    public void startClimb(){
        solenoid4.set(Value.kForward);
        run=true;
    }

    public void endClimb(){
        run=false;
    }

    public void retract(){
        solenoid4.set(Value.kReverse);
        run=true;
    }
}