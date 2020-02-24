package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.shoot;
import frc.robot.Subsystems.*;


public class SetShooter extends Command {
	private Shooter.State startState;
	private Shooter.State endState;
    double timeout;
	  
    public SetShooter(Shooter.State startState, Shooter.State endState, double timeout) {
    	super(timeout);
    	this.startState = startState;
    	this.endState = endState;
    }
    

    @Override
    protected void initialize() {
    }
  
    @Override
    protected void execute() {
        shoot.setState(startState);
    }

    @Override
    protected boolean isFinished() {
      return isTimedOut();
        }
    

    @Override
    protected void end() {
        shoot.setState(endState);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}