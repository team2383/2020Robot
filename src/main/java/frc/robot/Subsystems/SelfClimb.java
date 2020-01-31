package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SelfClimb{

    DoubleSolenoid solenoid3 = new DoubleSolenoid(4,5);


    public SelfClimb(){

    }

    public void prepClimb(){

        solenoid3.set(Value.kOff);

    }

}