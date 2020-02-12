package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.RobotMap;


public class Shifter {
  DoubleSolenoid solenoid4 = new DoubleSolenoid(RobotMap.s4fchannel,RobotMap.s4rchannel);

  public Shifter()
  {}

  public void shift() {
    solenoid4.set(Value.kForward);
  }

  public void unShift() {
    solenoid4.set(Value.kReverse);
  }





}