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
			new Waypoint(5, 0, 0)
			};

	Waypoint[] backward = new Waypoint[] {
		new Waypoint (0, 0, 0),
		//new Waypoint (2, -3, Pathfinder.d2r(-45)),
		//new Waypoint(3.5, -7, Pathfinder.d2r(-75)),
		new Waypoint(4, -4, Pathfinder.d2r(-85)),
	
		new Waypoint(4, -16.25, Pathfinder.d2r(-90))
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

	public BaselineAuto(boolean backwards) {
		//backwards = false;
		addSequential(new FollowTrajectory2(trajectory, false));
		addSequential(new FollowTrajectory2(trajectory2, true));
	}
}