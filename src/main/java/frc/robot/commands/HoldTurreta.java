package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.HAL;



public class HoldTurreta extends Command {
  DoubleSupplier turret;

  public HoldTurreta() {
    
  
    //this.turret = turret;
   
    requires(HAL.turreta);
  }

 
  @Override
  protected void initialize() {
  }

 
  @Override
  protected void execute() {
    HAL.turreta.setSpeed(0.25);
  }

 
  @Override
  protected boolean isFinished() {
    return false;
  }

  
  @Override
  protected void end() {
  }

  
  @Override
  protected void interrupted() {
  }
}

