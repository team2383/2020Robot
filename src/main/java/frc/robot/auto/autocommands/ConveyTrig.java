package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.triggered;
import static frc.robot.HAL.conveyor;
import frc.robot.Subsystems.*;


public class ConveyTrig extends Command {
	private Trigger.State startState;
    private Trigger.State endState;
    private Conveyor.State startStatec;
	private Conveyor.State endStatec;
	  
    public ConveyTrig(Trigger.State startState) {
    	this.startState = startState;
        this.endState = endState;
        this.startStatec = startStatec;
    	this.endStatec = endStatec;
    }
    

    @Override
    protected void initialize() {
    }
  
    @Override
    protected void execute() {
        triggered.setState(startState);
        conveyor.setState(startStatec);
    }

    @Override
    protected boolean isFinished() {
      return isTimedOut();
        }
    

    @Override
    protected void end() {
        triggered.setState(endState);
        conveyor.setState(endStatec);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}