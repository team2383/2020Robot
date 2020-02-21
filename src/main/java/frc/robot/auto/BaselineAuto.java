package frc.robot.auto;

import frc.robot.ninjaLib.*;
import frc.robot.ninjaLib.PathLoader;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

// (X,Y,THETA)
public class BaselineAuto extends CommandGroup {
	Waypoint[] baseline = new Waypoint[] {
			new Waypoint(0, 0, 0),
			new Waypoint(7.3, 0, 0)
			};

	Waypoint[] backward = new Waypoint[] {
		new Waypoint (7.3, 0, 0),
		new Waypoint (9, 0, Pathfinder.d2r(180))
	};

	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
			0.02, 	//delta time
			8.0,		//max velocity in ft/s for the motion profile
			2.0,		//max acceleration in ft/s/s for the motion profile
			500);	//max jerk in f7-8--------------------------------------t/s/s/s for the motion profile

	Trajectory trajectory = PathLoader.get(baseline, config);
	Trajectory trajectory2 = PathLoader.get(backward, config);

	public BaselineAuto(boolean backwards) {
		backwards = false;
		addSequential(new FollowTrajectory2(trajectory, backwards));
		addSequential(new FollowTrajectory2(trajectory2, true));
	}
}