package frc.robot.auto;

import frc.robot.ninjaLib.*;
import frc.robot.ninjaLib.PathLoader;
import frc.robot.HAL;

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

public class StraightUp extends CommandGroup {
	Waypoint[] starttoend = new Waypoint[] {
			new Waypoint(0, 0, 0),
			new Waypoint(5, 0, 0)
            };
        
            




	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
			0.02, 	//delta time
			3.95,		//max velocity in ft/s for the motion profile
			1.0,		//max acceleration in ft/s/s for the motion profile
			500);	//max jerk in f7-8--------------------------------------t/s/s/s for the motion profile
			
    Trajectory trajectory = PathLoader.get(starttoend, config);
    
	public StraightUp(boolean backwards) {
        backwards = true;
        addSequential(new FollowTrajectory2(trajectory, false));
        addParallel(new SetShooter(Shooter.State.FAST, Shooter.State.STOP, 4.0));
        addSequential(new WaitThenCommand(2.0, new SetFire(Feeder.State.FAST, Feeder.State.STOP,
         Conveyor.State.FAST, Conveyor.State.STOP,
         Trigger.State.RSPIN, Trigger.State.STOP, 3.0 )));
        
			}
}
