package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;


public class BuddyClimb{


    DoubleSolenoid solenoid4 = new DoubleSolenoid(RobotMap.s4fchannel,RobotMap.s4rchannel);

    public BuddyClimb()
    {}

    public void prepClimb(){


        solenoid4.set(Value.kOff);
    }

}