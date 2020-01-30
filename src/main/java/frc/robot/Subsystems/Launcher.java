/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Launcher{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  CANSparkMax launchMaster = new CANSparkMax(12, MotorType.kBrushless);
  CANSparkMax launchFollower = new CANSparkMax(11, MotorType.kBrushless);

  public void out(){
    launchMaster.set(-0.75);
    launchFollower.set(-0.75);
  }

  public void stop(){
    launchFollower.set(0);
    launchMaster.set(0);
  }
  public void in(){
    launchMaster.set(0.75);
    launchFollower.set(0.75);
  }

  
}
