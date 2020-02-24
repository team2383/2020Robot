package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.conveyor;
import frc.robot.Subsystems.*;


public class SetConveyor extends Command {
	private Conveyor.State startState;
	private Conveyor.State endState;
    double timeout;
	  
    public SetConveyor(Conveyor.State startState, Conveyor.State endState, double timeout) {
    	super(timeout);
    	this.startState = startState;
    	this.endState = endState;
    }
    

    @Override
    protected void initialize() {
    }
  
    @Override
    protected void execute() {
        conveyor.setState(startState);
    }

    @Override
    protected boolean isFinished() {
      return isTimedOut();
        }
    

    @Override
    protected void end() {
        conveyor.setState(endState);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}