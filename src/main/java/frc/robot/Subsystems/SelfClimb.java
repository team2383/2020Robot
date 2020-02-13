package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;


public class SelfClimb{
    

    DoubleSolenoid solenoid4 = new DoubleSolenoid(RobotMap.s4fchannel, RobotMap.s4rchannel);


    public SelfClimb(){

    }

    public void prepClimb(){

        solenoid4.set(Value.kForward);

    }

    public void stopClimb(){
        solenoid4.set(Value.kReverse);
    }

}