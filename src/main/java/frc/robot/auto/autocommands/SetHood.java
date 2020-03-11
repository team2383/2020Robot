/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.HAL;
// import frc.robot.Subsystems.Conveyor;
// import frc.robot.Subsystems.Shooter;
// import frc.robot.Subsystems.Trigger;
// import frc.robot.Subsystems.Feeder;
import frc.robot.Subsystems.*;

import static frc.robot.HAL.feeder;
import static frc.robot.HAL.shoot;
import static frc.robot.HAL.triggered;
import static frc.robot.HAL.conveyor;


public class SetHood extends Command {
    double timeout;

  public SetHood(double timeout) {
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   HAL.hood.moveto(2777);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
