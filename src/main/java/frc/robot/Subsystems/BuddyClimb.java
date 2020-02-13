package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;


public class BuddyClimb{

    int toggle=1;
    public boolean run=false;
    DoubleSolenoid solenoid1 = new DoubleSolenoid(RobotMap.s1fchannel,RobotMap.s1rchannel);


    public BuddyClimb()
    {
        solenoid1.set(Value.kForward);
    }

    public void stopClimb(){
        if (toggle>0){
            solenoid1.set(Value.kForward);
        }else{
            solenoid1.set(Value.kReverse);
        }
        run=false;
        toggle*=-1;
    }

    public void prepClimb(){
        run=true;
    }

}