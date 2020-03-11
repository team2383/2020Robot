// package frc.robot.auto;

// import frc.robot.ninjaLib.*;
// import frc.robot.ninjaLib.PathLoader;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.Subsystems.Feeder;
// import frc.robot.Subsystems.*;
// import frc.robot.Subsystems.Shooter;
// import frc.robot.Subsystems.Feeder.State;
// import frc.robot.auto.autocommands.*;
// import jaci.pathfinder.Pathfinder;
// import jaci.pathfinder.Trajectory;
// import jaci.pathfinder.Waypoint;

// // (X,Y,THETA)
// public class BaselineAuto extends CommandGroup {
// 	Waypoint[] baseline = new Waypoint[] {
// 			new Waypoint(0, 0, 0),
// 			new Waypoint(8, 0, 0)
// 			};

// 	Waypoint[] backward = new Waypoint[] {
// 		new Waypoint (0, 0, 0),
// 		//new Waypoint (2, -3, Pathfinder.d2r(-45)),
// 		//new Waypoint(3.5, -7, Pathfinder.d2r(-75)),
// 		new Waypoint(4.5, -4, Pathfinder.d2r(-85)),
	
//         new Waypoint(4.5, -14, Pathfinder.d2r(-90)),
//         new Waypoint(1, -17.25, Pathfinder.d2r(-10))
//     };
    
//     Waypoint[] secondCurve = new Waypoint[]
//     {
//         new Waypoint(0, 0, Pathfinder.d2r(90))
//     };

// 	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
// 			0.02, 	//delta time
// 			4.0,		//max velocity in ft/s for the motion profile
// 			2.0,		//max acceleration in ft/s/s for the motion profile
// 			500);	//max jerk in f7-8--------------------------------------t/s/s/s for the motion profile
			
			
// 	Trajectory.Config config2 = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
// 			0.02, 	//delta time
// 			3.95,		//max velocity in ft/s for the motion profile
// 			1.0,		//max acceleration in ft/s/s for the motion profile
// 			500);	//max jerk in f7-8--------------------------------------t/s/s/s for the motion profile

// 	Trajectory trajectory = PathLoader.get(baseline, config);
//     Trajectory trajectory2 = PathLoader.get(backward, config2);
    

// 	public BaselineAuto(boolean backwards) {
//         backwards = false;
//         addParallel(new SetFeeder(Feeder.State.FAST, Feeder.State.STOP, 4));
//         addSequential(new FollowTrajectory2(trajectory, false));
//         addSequential(new FollowTrajectory2(trajectory2, true));
//         //addSequential(new LimeAllign());
//         addParallel(new SetShooter(Shooter.State.FAST, Shooter.State.STOP, 10));
//         addSequential(new WaitThenCommand(3, new SetFire(Feeder.State.FAST, Feeder.State.STOP,
//          Conveyor.State.FAST, Conveyor.State.STOP,
//          Trigger.State.RSPIN, Trigger.State.STOP, 3.0 )));
// 	}
// }