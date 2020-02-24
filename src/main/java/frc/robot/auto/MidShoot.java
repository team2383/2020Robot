// package frc.robot.auto;

// import frc.robot.Subsystems.Shooter.State;
// import frc.robot.auto.autocommands.SetShooter;
// import frc.robot.ninjaLib.*;
// import frc.robot.ninjaLib.PathLoader;
// import frc.robot.ninjaLib.StatefulSubsystem;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.command.CommandGroup;
// import jaci.pathfinder.Pathfinder;
// import jaci.pathfinder.Trajectory;
// import jaci.pathfinder.Waypoint;

// // (X,Y,THETA)
// public class MidShoot extends CommandGroup {
// 	Waypoint[] starttoend = new Waypoint[] {
// 			new Waypoint(0, 0, 0),
// 			new Waypoint(5, 0, 0)
// 			};


// 	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
// 			0.02, 	//delta time
// 			5.0,		//max velocity in ft/s for the motion profile
// 			2.5,		//max acceleration in ft/s/s for the motion profile
// 			500);	//max jerk in f7-8--------------------------------------t/s/s/s for the motion profile
			
//     Trajectory trajectory = PathLoader.get(starttoend, config);
    
// 	public MidShoot(boolean backwards) {
//         //backwards = false;
//         addSequential(new SetShooter(State.LIME, State.STOP, 3));
// 		addSequential(new FollowTrajectory2(trajectory, false));
// 			}
// }