package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.triggered;
import frc.robot.Subsystems.*;


public class SetTrigger extends Command {
	private Trigger.State startState;
	private Trigger.State endState;
    double timeout;
	  
    public SetTrigger(Trigger.State startState, Trigger.State endState, double timeout) {
    	super(timeout);
    	this.startState = startState;
    	this.endState = endState;
    }
    

    @Override
    protected void initialize() {
    }
  
    @Override
    protected void execute() {
        triggered.setState(startState);
    }

    @Override
    protected boolean isFinished() {
      return isTimedOut();
        }
    

    @Override
    protected void end() {
        triggered.setState(endState);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}