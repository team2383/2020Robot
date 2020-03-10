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


  public LimeAllign() {
    double turn = HAL.limelight.xOffset()/29.8;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    HAL.limelight.setPipeline(1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if(Math.abs(HAL.limelight.xOffset()) > 1){
        HAL.drive.arcade(0, HAL.limelight.xOffset()/54 + 0.1);
      }
      else{
        HAL.drive.arcade(0, 0);
      }

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
