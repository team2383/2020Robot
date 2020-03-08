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


public class SetFire extends Command {
    private Feeder.State fStartState;
    private Feeder.State fEndState;

    private Trigger.State tStartState;
    private Trigger.State tEndState;

    private Conveyor.State cStartState;
    private Conveyor.State cEndState;


    double timeout;
  public SetFire(Feeder.State fStartState, Feeder.State fEndState,
     Conveyor.State cStartState, Conveyor.State cEndState,
    Trigger.State tStartState, Trigger.State tEndState,
     double timeout) {

      super(timeout);
      this.fStartState = fStartState;
      this.fEndState = fEndState;

      this.tStartState = tStartState;
      this.tEndState = tEndState;

      this.cStartState = cStartState;
      this.cEndState = cEndState;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   triggered.setState(tStartState);
   feeder.setState(fStartState);
   conveyor.setState(cStartState);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    triggered.setState(tEndState);
    feeder.setState(fEndState);
    conveyor.setState(cEndState);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
