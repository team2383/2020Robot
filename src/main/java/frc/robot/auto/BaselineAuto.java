package frc.robot.auto;

import frc.robot.auto.FollowTrajectory2;
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
			new Waypoint(3, 0, 0)
			};

	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
			0.02, 	//delta time
			1.0,		//max velocity in ft/s for the motion profile
			0.8,		//max acceleration in ft/s/s for the motion profile
			500);	//max jerk in ft/s/s/s for the motion profile

	Trajectory trajectory = PathLoader.get(baseline, config);

	public BaselineAuto(boolean backwards) {
		backwards = false;
		addSequential(new FollowTrajectory2(trajectory, backwards));
	}
}