/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//great reference ->
//https://github.com/team2383/2018Kappa/blob/5ff1b0afae8c319719aa4fdfe2d89dc8e98971cd/src/
//com/team2383/robot/commands/FollowTrajectory.java

//To Do: reintegrate navx

package frc.robot;

import jaci.pathfinder.*;
import jaci.pathfinder.modifiers.TankModifier;
import frc.robot.ninjaLib.PathFollower;

import com.ctre.phoenix.CANifier.LEDChannel;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HAL;
import frc.robot.Subsystems.Drivetrain;


/**
 * Add your docs here.
 */

public class Pathfinding {
    Waypoint[] points = new Waypoint[] {
        new Waypoint(-4, -1, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
        new Waypoint(-2, -2, 0),                        // Waypoint @ x=-2, y=-2, exit angle=0 radians
        new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
    };
    Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
// Sample Count:        SAMPLES_HIGH (100 000)
//                      SAMPLES_LOW  (10 000)
//                      SAMPLES_FAST (1 000)
// Time Step:           0.05 Seconds
// Max Velocity:        1.7 m/s
// Max Acceleration:    2.0 m/s/s
// Max Jerk:            60.0 m/s/s/s
    Trajectory trajectory = Pathfinder.generate(points, config);
    double wheelbase_width = 0.6;
    TankModifier modifier = new TankModifier(trajectory);
    Trajectory left  = modifier.getLeftTrajectory();       // Get the Left Side
    Trajectory right = modifier.getRightTrajectory();      // Get the Right Side
	PathFollower leftFollower;
	PathFollower rightFollower;
	Double startingAngle = 0.0;
	double angleDifference;


    Pathfinding(){
        modifier.modify(wheelbase_width);
		
		leftFollower = new PathFollower(modifier.getLeftTrajectory());
		rightFollower = new PathFollower(modifier.getRightTrajectory());
		
		leftFollower.configurePIDVA(
            1, // proportional
			0.0,//integral
			0.0,//derivative
			0.5,//Max velocity percentage of totakl
			0.5);//acceleration

		rightFollower.configurePIDVA(
            1, // proportional
            0.0,//integral
            0.0,//derivative
            0.5,//Max velocity percentage of totakl
            0.5);//acceleration
    
		leftFollower.reset();
        rightFollower.reset();
        
        HAL.drivetrain.resetEncoders();
        HAL.navX.zeroYaw();
        
        

    }
	
    public void followPath(){
        SmartDashboard.putNumber("MP Target Left Position (ft)", leftFollower.getSegment().position);
		SmartDashboard.putNumber("MP Target Left Velocity (ft-s)", leftFollower.getSegment().velocity);

		SmartDashboard.putNumber("MP Target Right Position (ft)", rightFollower.getSegment().position);
		SmartDashboard.putNumber("MP Target Right Velocity (ft-s)", rightFollower.getSegment().velocity);
		
		SmartDashboard.putNumber("MP Target Heading", leftFollower.getSegment().heading);
		
		double leftOutput;
		double rightOutput;
		//forwards

			//backwards
//			leftOutput = leftFollower.calculate(HAL.drivetrain.getMotion().rightPosition); //left = -right
//			rightOutput = rightFollower.calculate(HAL.drivetrain.getMotion().leftPosition); //right = -left
			leftOutput = 0;
			rightOutput = 0;
		
		double gyro_heading = -HAL.navX.getAngle(); //axis is the same
		
		double desired_heading = Pathfinder.r2d(leftFollower.getHeading() - startingAngle);  // Should also be in degrees, make sure its in phase

		SmartDashboard.putNumber("MP1 gyro_heading", gyro_heading);
		SmartDashboard.putNumber("MP2 desired_heading", desired_heading);
		SmartDashboard.putNumber("MP3 error", desired_heading-gyro_heading);
		SmartDashboard.putNumber("MP6 turnP", 0.5);
		
		angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);

		SmartDashboard.putNumber("MP4 angleDiff", angleDifference);
		
		double turn = 0.2 * angleDifference;
		SmartDashboard.putNumber("MP Left Output (%)", leftOutput);
		SmartDashboard.putNumber("MP Right Output (%)", rightOutput);
		SmartDashboard.putNumber("MP Left Output BCKWRDS (%)", -rightOutput);
		SmartDashboard.putNumber("MP Right Output BCKWRDS(%)", -leftOutput);
		SmartDashboard.putNumber("MP5 Heading Adj. Output (%)", turn);
	
			//forwards
		
		HAL.drivetrain.tank(leftOutput - turn, rightOutput + turn);
		
			//backwards
			/*.tank is forwards, so (fwd_left, fwd_right)
			 * back_left = -fwd_right
			 * 	so fwd_right = -back_left
			 * back_right = -fwd_left
			 * 	so fwd_left = -back_right
			 * 
			 * turn input is relative to true (fwd) drivetrain output, not the actual direction
			 * so fwd_left(back_right) has to be less negative (going slower) then fwd_right(back_left), when turning right (negative turn)
			 */
			//drive.tank(-rightOutput - turn, -leftOutput + turn);
		}
		
    }

    


