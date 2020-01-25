package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.HAL;

public class DriveCommand extends CommandBase {
  DoubleSupplier throttle;
  DoubleSupplier turn;
  public DriveCommand(DoubleSupplier throttle,DoubleSupplier turn) 
  {
    this.turn = turn;
    this.throttle = throttle;
  }
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    HAL.drive.arcade(throttle.getAsDouble(), turn.getAsDouble());
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
  }

}
