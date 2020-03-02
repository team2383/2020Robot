package frc.robot.auto;

import frc.robot.ninjaLib.*;
import frc.robot.ninjaLib.PathLoader;
import frc.robot.Subsystems.Feeder.State;
import frc.robot.auto.autocommands.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitUntilCommand;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

// (X,Y,THETA)
/**Starting condition: begin in fron of enemy trench
 * Back up and grab first two balls in enemy trench
 * Rush to the line and shoot loaded balls into high goal
 * Turn around and go grab balls underneath shield generator
 * Rush back to line and shoot balls into high goal
 */
public class LeftStartExtended extends CommandGroup {
	Waypoint[] baseline = new Waypoint[] {
			new Waypoint(0, 0, 0),
			new Waypoint(8, 0, 0)
			};

	Waypoint[] backward = new Waypoint[] {
		new Waypoint (0, 0, 0),
		
	};

	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
			0.02, 	//delta time
			4.0,		//max velocity in ft/s for the motion profile
			2.0,		//max acceleration in ft/s/s for the motion profile
			500);	//max jerk in f7-8--------------------------------------t/s/s/s for the motion profile
			
	Trajectory.Config config2 = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
			0.02, 	//delta time
			3.95,		//max velocity in ft/s for the motion profile
			1.0,		//max acceleration in ft/s/s for the motion profile
			500);	//max jerk in f7-8--------------------------------------t/s/s/s for the motion profile

	Trajectory trajectory = PathLoader.get(baseline, config);
	Trajectory trajectory2 = PathLoader.get(backward, config2);

	public LeftStartExtended(boolean backwards) {
		addSequential(new FollowTrajectory2(trajectory, false));
		///addParallel(new WaitThenCommand(1, new SetFeeder(State.FAST, State.STOP, 0.5)));
		addSequential(new FollowTrajectory2(trajectory2, true));
	}
}