package frc.robot.auto;

import frc.robot.Subsystems.Shooter;
import frc.robot.Subsystems.Feeder;
import frc.robot.auto.autocommands.LimeTurn;
import frc.robot.auto.autocommands.PointTurn;
import frc.robot.auto.autocommands.SetFeeder;
import frc.robot.auto.autocommands.SetShooter;
import frc.robot.auto.autocommands.WaitThenCommand;
import frc.robot.ninjaLib.*;
import frc.robot.ninjaLib.DelayFeed;
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
 **/

public class RightStart extends CommandGroup {
	Waypoint[] prefeed1 = new Waypoint[] {
			new Waypoint(0, 0, Pathfinder.d2r(90)),
			new Waypoint(8.5, 5, Pathfinder.d2r(0)),
			};
	
	Waypoint[] duringfeed = new Waypoint[] {
			new Waypoint(0, 0, 0),
			new Waypoint(9, 0, Pathfinder.d2r(0)),
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
	Trajectory prefeed1traj = PathLoader.get(prefeed1, config);
	Trajectory duringfeedtraj = PathLoader.get(duringfeed, config);
	public RightStart(boolean backwards) {
        backwards = false;
        //addSequential(new LimeTurn());
        addSequential(new SetShooter(Shooter.State.FAST, Shooter.State.STOP, 3));
        addParallel(new WaitThenCommand(0.5, new DelayFeed(0.5)), 2);
        addSequential(new PointTurn(90));
        addSequential(new FollowTrajectory2(prefeed1traj, false));
        addSequential(new FollowTrajectory2(duringfeedtraj, true));
        addParallel(new SetFeeder(Feeder.State.FAST, Feeder.State.STOP, 3));

	}
}