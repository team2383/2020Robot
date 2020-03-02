package frc.robot.auto;

import frc.robot.ninjaLib.*;
import frc.robot.ninjaLib.PathLoader;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

// (X,Y,THETA)
/**  Starting condition: Start in front of alliance trench
 * Use limelight, align in place and shoot preloaded balls into high goal
 * Back up and pick up balls in our trench
 * Rush back and shoot balls into high goal
 * Cross line
 * **/

public class RightStart extends CommandGroup {
	Waypoint[] baseline = new Waypoint[] {
			new Waypoint(0, 0, 0),
			new Waypoint(6, 5.6, Pathfinder.d2r(0)),
			new Waypoint(20, 5.6, Pathfinder.d2r(0))
			};
	
	Waypoint[] baseline2 = new Waypoint[] {
			new Waypoint(0, 0, 0),
			new Waypoint(13, 7.4, Pathfinder.d2r(0)),
			};

	Waypoint[] rsSingleBalls = new Waypoint[]{
		new Waypoint(0, 0, 0)
	};
	Waypoint[] rsBallPair = new Waypoint[]{};
	Waypoint[] rsReturn = new Waypoint[]{};


	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
			0.02, 	//delta time
			2.5,		//max velocity in ft/s for the motion profile
			1.0,		//max acceleration in ft/s/s for the motion profile
			500);	//max jerk in ft/s/s/s for the motion profile

	// Trajectory.Config config2 = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
	// 		0.02, 	//delta time
	// 		3.0,		//max velocity in ft/s for the motion profile
	// 		2.0,		//max acceleration in ft/s/s for the motion profile
	// 		500);	
	Trajectory trajectory = PathLoader.get(baseline, config);
	Trajectory trajectory2 = PathLoader.get(baseline2, config);
	public RightStart(boolean backwards) {
		backwards = false;
		addSequential(new FollowTrajectory2(trajectory, false));
		addSequential(new FollowTrajectory2(trajectory2, true));
	}
}