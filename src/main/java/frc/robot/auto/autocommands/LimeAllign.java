/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.HAL;


public class LimeAllign extends Command {

  double leftOutput;
  double rightOutput;
  public LimeAllign() {
    leftOutput = -HAL.limelight.xOffset()/29.8;
    rightOutput = HAL.limelight.xOffset()/29/8;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    HAL.limelight.setPipeline(1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    while(HAL.limelight.xOffset() >= 2.0 || HAL.limelight.xOffset() <= -2.0){
      HAL.drive.limeAlign();
      //HAL.drive.arcade(0, rightOutput);
      //HAL.drive.tank(leftOutput, rightOutput);
    }
    end();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }   

  // Called once after isFinished returns true
  @Override
  protected void end() {
    HAL.limelight.setPipeline(3);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
