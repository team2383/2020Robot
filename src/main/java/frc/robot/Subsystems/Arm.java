/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
    solenoid1.set(Value.kOff);
    solenoid2.set(Value.kOff);
  }



}
