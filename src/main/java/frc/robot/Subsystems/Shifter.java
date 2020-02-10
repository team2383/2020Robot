package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.RobotMap;


public class Shifter {
  DoubleSolenoid solenoid1 = new DoubleSolenoid(RobotMap.s1fchannel,RobotMap.s1rchannel);
  DoubleSolenoid solenoid2 = new DoubleSolenoid(RobotMap.s4fchannel,RobotMap.s4rchannel);

  public Shifter()
  {}

  public void shift() {
    solenoid1.set(Value.kForward);
    solenoid2.set(Value.kForward);
  }

  public void unShift() {
    solenoid1.set(Value.kReverse);
    solenoid2.set(Value.kReverse);
  }





}