package frc.robot.auto.autocommands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.HAL.drive;
import static frc.robot.HAL.navX;
import static frc.robot.HAL.limelight;
import frc.robot.Subsystems.*;


public class PointTurn extends Command {

    double desiredangle;
    
    public PointTurn(double desiredangle) {
    }
    

    @Override
    protected void initialize() {
    }
  
    @Override
    protected void execute() {
        double delta = desiredangle - navX.getAngle();
        drive.arcade(0, (delta/100));
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