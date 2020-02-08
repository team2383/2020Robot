package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;


public class BuddyClimb{


    DoubleSolenoid solenoid4 = new DoubleSolenoid(RobotMap.s2fchannel,RobotMap.s2rchannel);

    public BuddyClimb()
    {}

    public void prepClimb(){


        solenoid4.set(Value.kForward);
    }

    public void stopClimb(){
        solenoid4.set(Value.kReverse);
    }

}