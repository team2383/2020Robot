package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;

public class WheelLifter2{
    DoubleSolenoid solenoid2= new DoubleSolenoid(RobotMap.s2fchannel, RobotMap.s2rchannel);

    public WheelLifter2(){
    }

    public void wheelUp(){
        solenoid2.set(Value.kForward);
    }
    public void wheelDown(){
        solenoid2.set(Value.kReverse);
    }
}