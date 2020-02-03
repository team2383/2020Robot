package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;


public class SelfClimb{

    DoubleSolenoid solenoid3 = new DoubleSolenoid(RobotMap.s3fchannel, RobotMap.s3rchannel);


    public SelfClimb(){

    }

    public void prepClimb(){

        solenoid3.set(Value.kForward);

    }

    public void stopClimb(){
        solenoid3.set(Value.kReverse);
    }

}