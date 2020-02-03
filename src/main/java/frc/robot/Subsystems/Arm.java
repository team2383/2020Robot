package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.RobotMap;


public class Arm {
  DoubleSolenoid solenoid1 = new DoubleSolenoid(RobotMap.s1fchannel,RobotMap.s1rchannel);
  DoubleSolenoid solenoid2 = new DoubleSolenoid(RobotMap.s2fchannel,RobotMap.s2rchannel);

  public Arm()
  {}

  public void deployArm() {
    solenoid1.set(Value.kForward);
    solenoid2.set(Value.kForward);
  }

  public void stopArm() {
    solenoid1.set(Value.kReverse);
    solenoid2.set(Value.kReverse);
  }





}
