package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.drive;
import static frc.robot.HAL.limelight;

import frc.robot.HAL;
import frc.robot.Subsystems.*;


public class LimeTurn extends Command {
	  
    public LimeTurn() {
    }
    

    @Override
    protected void initialize() {
    }
  
    @Override
    protected void execute() {
        if (HAL.limelight.hasTargets()){
            drive.arcade(0, (limelight.xOffset()/54) + 0.1);;
        }
        else{
            drive.arcade(0,0.4);
        }
    }

    @Override
    protected boolean isFinished() {
      return drive.pivoted();
        }
    

    @Override
    protected void end() {
        drive.arcade(0, 0);

    }

    @Override
    protected void interrupted() {
    	end();
    }
}