package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.HAL;

public class DriveCommand extends CommandBase {
  DoubleSupplier throttle;
  DoubleSupplier turn;
  public DriveCommand(DoubleSupplier throttle,DoubleSupplier turn) {
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.turn = turn;
    this.throttle = throttle;
    //addRequirements(Drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    HAL.drive.arcade(throttle.getAsDouble(), turn.getAsDouble());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  /*@Override
  protected void interrupted() {
  }*/
}
