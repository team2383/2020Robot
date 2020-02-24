package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.feeder;
import frc.robot.Subsystems.*;


public class SetFeeder extends Command {
	private Feeder.State startState;
	private Feeder.State endState;
    double timeout;
	  
    public SetFeeder(Feeder.State startState, Feeder.State endState, double timeout) {
    	super(timeout);
    	this.startState = startState;
    	this.endState = endState;
    }
    

    @Override
    protected void initialize() {
    }
  
    @Override
    protected void execute() {
        feeder.setState(startState);
    }

    @Override
    protected boolean isFinished() {
      return isTimedOut();
        }
    

    @Override
    protected void end() {
        feeder.setState(endState);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}