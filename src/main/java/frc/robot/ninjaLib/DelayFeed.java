package frc.robot.ninjaLib;

import static frc.robot.HAL.feeder;
import static frc.robot.HAL.triggered;
import static frc.robot.HAL.conveyor;

import frc.robot.auto.autocommands.*;
import frc.robot.Subsystems.*;
import frc.robot.Subsystems.Feeder.State;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


public class DelayFeed extends CommandGroup{

	  
    public DelayFeed(double delay) {
        //addParallel(new WaitThenCommand(0.5, new SetFeeder(Feeder.State.FAST, Feeder.State.STOP, 2.5)));
  //addSequential(new ConveyTrig(Trigger.State.SPIN, Trigger.State.STOP, Conveyor.State.FAST, Conveyor.State.STOP, 3));
        
    }


}