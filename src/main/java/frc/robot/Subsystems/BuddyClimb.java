package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;


public class BuddyClimb{

    int toggle=1;
    DoubleSolenoid solenoid4 = new DoubleSolenoid(RobotMap.s2fchannel,RobotMap.s2rchannel);
    DoubleSolenoid solenoid3 = new DoubleSolenoid(RobotMap.s3fchannel,RobotMap.s3rchannel);

    public BuddyClimb()
    {
        solenoid4.set(Value.kForward);
        solenoid3.set(Value.kForward);
    }

    public void prepClimb(){
        if (toggle>0){
            solenoid4.set(Value.kForward);
            solenoid3.set(Value.kForward);
        }else{
            solenoid4.set(Value.kReverse);
            solenoid3.set(Value.kReverse);
        }
        toggle*=-1;
    }

    public void stopClimb(){
    }

}