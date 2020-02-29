package frc.robot.ninjaLib;

import frc.robot.ninjaLib.*;
import frc.robot.ninjaLib.PathLoader;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
public class HelperCommand extends CommandGroup{
    Waypoint[] testPoint = new Waypoint[] {
        new Waypoint(0, 0, 0),
        new Waypoint(1, 0, 0)
  };
  Trajectory.Config testConfig = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
        0.02, 	//delta time
        4.0,		//max velocity in ft/s for the motion profile
        2.0,		//max acceleration in ft/s/s for the motion profile
  500);	//max j
  Trajectory testTrajectory = PathLoader.get(testPoint, testConfig);
  public HelperCommand(boolean backwards){
    addSequential(new FollowTrajectory2(testTrajectory, backwards));
  }

}