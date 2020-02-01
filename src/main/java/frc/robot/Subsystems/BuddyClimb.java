package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BuddyClimb{


    DoubleSolenoid solenoid4 = new DoubleSolenoid(6,7);

    public BuddyClimb()
    {}

    public void prepClimb(){


        solenoid4.set(Value.kOff);
    }

}