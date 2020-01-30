package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.HAL;

public class DriveCommand extends CommandBase {
  DoubleSupplier throttle;
  DoubleSupplier turn;
  public DriveCommand(final DoubleSupplier throttle, final DoubleSupplier turn) 
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

  

}
