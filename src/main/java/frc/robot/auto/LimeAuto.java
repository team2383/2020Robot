package frc.robot.auto;

import frc.robot.ninjaLib.*;
import frc.robot.ninjaLib.PathLoader;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Subsystems.Feeder;
import frc.robot.Subsystems.*;
import frc.robot.Subsystems.Shooter;
import frc.robot.Subsystems.Feeder.State;
import frc.robot.auto.autocommands.*;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class LimeAuto extends CommandGroup {
	
	public LimeAuto() {
        addSequential(new LimeTurn());
	}
}