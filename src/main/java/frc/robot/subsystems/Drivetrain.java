
package frc.robot.subsystems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.ninjaLib.MotionUtils;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.commands.DriveCommand;


import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drivetrain extends SubsystemBase {

	boolean i = true;

  	public final WPI_TalonFX leftMaster;
	public final WPI_TalonFX leftFollower;

	public final WPI_TalonFX rightMaster;
	public final WPI_TalonFX rightFollower;

  	public final DifferentialDrive drive;

  	public Drivetrain (){

    ////////////////
    // LEFT DRIVE //
    ////////////////
    
    leftMaster = new WPI_TalonFX(Constants.leftMasterPort);
	leftFollower = new WPI_TalonFX(Constants.leftFollowerPort);
	 leftFollower.follow(leftMaster);
    // leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	// leftMaster.setNeutralMode(NeutralMode.Brake);

	// leftMaster.enableVoltageCompensation(true);
	// leftMaster.configVoltageCompSaturation(12, 10);
	// leftMaster.configForwardSoftLimitEnable(false, 10);
	// leftMaster.configReverseSoftLimitEnable(false, 10);
		
	// leftMaster.setControlFramePeriod(ControlFrame.Control_3_General, 5);
	// leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 10);

	// leftMaster.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_20Ms, 10);

    /////////////////
    // RIGHT DRIVE //
	/////////////////
	
    rightMaster = new WPI_TalonFX(Constants.rightMasterPort);
    rightFollower = new WPI_TalonFX(Constants.rightFollowerPort);
	
	rightFollower.follow(rightMaster);

	drive = new DifferentialDrive(leftMaster, rightMaster);}
	public void arcade(double move, double turn){
		drive.arcadeDrive(move,turn);
		drive.setSafetyEnabled(false);
	  }
	
	  public double getRightPosition() {
		return rightMaster.getSelectedSensorPosition();
	  }
	
	  public double getLeftPosition(){
		return rightMaster.getSelectedSensorPosition();
	  }

	//   public void periodic(){
	// 	  SmartDashboard.putNumber('R', getRightPosition())
	//   }
	
	  public void resetEncoders() {
			leftMaster.setSelectedSensorPosition(0, 0, 0);
			rightMaster.setSelectedSensorPosition(0, 0, 0);
		}

		

	
		
		


		
//     rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
// 	rightMaster.setNeutralMode(NeutralMode.Brake);

// 	rightMaster.enableVoltageCompensation(true);
// 	rightMaster.configVoltageCompSaturation(12, 10);
// 	rightMaster.configForwardSoftLimitEnable(false, 10);
// 	rightMaster.configReverseSoftLimitEnable(false, 10);
		
// 	rightMaster.setControlFramePeriod(ControlFrame.Control_3_General, 5);
// 	rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 10);
		
// 	rightMaster.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_20Ms, 10);

//     configMotorControllers(10);
    
		
// 		setBrake(true);
//     drive = new DifferentialDrive(leftMaster, rightMaster);
//     drive.setSafetyEnabled(false);
// 		drive.setMaxOutput(1.0);
//   }

//   public void configMotorControllers(int timeout) {
// 		double kWheelCircumference = Constants.getWheelCircumference();

// 		leftMaster.setInverted(true);
// 		leftFollower.setInverted(true);
// 		leftMaster.setSensorPhase(false);
		 
// 		leftMaster.configSetParameter(ParamEnum.eContinuousCurrentLimitAmps, Constants.kDrive_ContinuousCurrentLimit, 0x00, 0x00, timeout);
// 		leftMaster.configSetParameter(ParamEnum.ePeakCurrentLimitAmps, Constants.kDrive_PeakCurrentLimit, 0x00, 0x00, timeout);
// 		leftMaster.configSetParameter(ParamEnum.ePeakCurrentLimitMs, Constants.kDrive_PeakCurrentTime_ms, 0x00, 0x00, timeout);
// 		// leftMaster.enableCurrentLimit(false); // UNDEFINED FOR TALONFX
		
// 		leftMaster.configPeakOutputForward(Constants.kDrive_peakOutput, timeout);
// 		leftMaster.configPeakOutputReverse(-Constants.kDrive_peakOutput, timeout);
// 		leftMaster.configOpenloopRamp(0.0, timeout);
		
		
// 		rightMaster.setInverted(true);
// 		rightFollower.setInverted(true);
// 		rightMaster.setSensorPhase(true);

// 		rightMaster.configSetParameter(ParamEnum.eContinuousCurrentLimitAmps, Constants.kDrive_ContinuousCurrentLimit, 0x00, 0x00, timeout);
// 		rightMaster.configSetParameter(ParamEnum.ePeakCurrentLimitAmps, Constants.kDrive_PeakCurrentLimit, 0x00, 0x00, timeout);
// 		rightMaster.configSetParameter(ParamEnum.ePeakCurrentLimitMs, Constants.kDrive_PeakCurrentTime_ms, 0x00, 0x00, timeout);
// 		// rightMaster.enableCurrentLimit(false); // UNDEFINED FOR TALONFX
		
// 		rightMaster.configPeakOutputForward(Constants.kDrive_peakOutput, timeout);
// 		rightMaster.configPeakOutputReverse(-Constants.kDrive_peakOutput, timeout);
// 		rightMaster.configOpenloopRamp(0.0, timeout);
		
// 		//PID
// 		rightMaster.config_kP(0, -(Constants.kDrive_Motion_talonP * (1023.0/1.0) * (1.0/(kWheelCircumference)) * (1.0/4096.0)), 10);
// 		rightMaster.config_kI(0, -Constants.kDrive_Motion_talonI, 10);
// 		rightMaster.config_kD(0, -(Constants.kDrive_Motion_talonD * (1023.0/1.0) * (1.0/(1.0/kWheelCircumference)) * (1.0/4096.0) * (10.0)), 10);
// 		rightMaster.config_kF(0, -(Constants.kDrive_Motion_V * (1023.0/1.0) * (1.0/(1.0/kWheelCircumference)) * (1.0/4096.0) * (10.0)),  10);
// 		rightMaster.config_IntegralZone(0, 50, 10);

// 		rightMaster.config_kP(1, -(Constants.kDrive_Motion_talonP * (1023.0/1.0) * (1.0/(kWheelCircumference)) * (1.0/4096.0)), 10);
// 		rightMaster.config_kI(1, -Constants.kDrive_Motion_talonI, 10);
// 		rightMaster.config_kD(1, -(Constants.kDrive_Motion_talonD * (1023.0/1.0) * (1.0/(1.0/kWheelCircumference)) * (1.0/4096.0) * (10.0)), 10);
// 		rightMaster.config_kF(1, 0,  10);
// 		rightMaster.config_IntegralZone(1, 50, 10);

// 		leftMaster.config_kP(0, (Constants.kDrive_Motion_talonP * (1023.0/1.0) * (1.0/(kWheelCircumference)) * (1.0/4096.0)), 10);
// 		leftMaster.config_kI(0, Constants.kDrive_Motion_talonI, 10);
// 		leftMaster.config_kD(0, (Constants.kDrive_Motion_talonD * (1023.0/1.0) * (1.0/(1.0/kWheelCircumference)) * (1.0/4096.0) * (10.0)), 10);
// 		leftMaster.config_kF(0, (Constants.kDrive_Motion_V * (1023.0/1.0) * (1.0/(1.0/kWheelCircumference)) * (1.0/4096.0) * (10.0)),  10);
// 		leftMaster.config_IntegralZone(0, 50, 10);
		
// 		leftMaster.config_kP(1, (Constants.kDrive_Motion_talonP * (1023.0/1.0) * (1.0/(kWheelCircumference)) * (1.0/4096.0)), 10);
// 		leftMaster.config_kI(1, Constants.kDrive_Motion_talonI, 10);
// 		leftMaster.config_kD(1, (Constants.kDrive_Motion_talonD * (1023.0/1.0) * (1.0/(1.0/kWheelCircumference)) * (1.0/4096.0) * (10.0)), 10);
// 		leftMaster.config_kF(1, 0,  10);
// 		leftMaster.config_IntegralZone(1, 50, 10);
		
		/*
		 * ft/s -> ticks per 100ms
		 * so ft/s * 1 rotation / circumference ft * 4096 / 1 rotation / 10
		 */
// 		int nativeVelocity = (int) (Constants.kDrive_Motion_Velocity * 1.0/Constants.getWheelCircumference() * 4096.0 / 10.0);

// 		/*
// 		 * ft/s/s -> ticks per 100ms per s
// 		 * so ft/s/s * 1 rotation / circumference ft * 4096 / 1 rotation / 10
// 		 */
// 		int nativeAcceleration = (int) (Constants.kDrive_Motion_Acceleration * 1.0/Constants.getWheelCircumference() * 4096.0 / 10.0);
// 		leftMaster.configMotionCruiseVelocity(nativeVelocity, timeout);
// 		leftMaster.configMotionAcceleration(nativeAcceleration, timeout);
// 		rightMaster.configMotionCruiseVelocity(nativeVelocity, timeout);
// 		rightMaster.configMotionAcceleration(nativeAcceleration, timeout);
// 	}

//   public void arcade(double move, double turn){
//     drive.arcadeDrive(move, turn);
//   }

//   public void tank(double left, double right)
//   {
//     drive.tankDrive(left, right);
//   }

//   public void resetEncoders() {
// 		leftMaster.setSelectedSensorPosition(0, 0, 0);
// 		rightMaster.setSelectedSensorPosition(0, 0, 0);
//   }
  
//   public double getLeftVelocity() {
// 		return leftMaster.getSelectedSensorVelocity(0) / 4096.0 * 10.0 * Constants.getWheelCircumference();
// 	}
	
// 	public double getRightVelocity() {
// 		return rightMaster.getSelectedSensorVelocity(0) / 4096.0 * 10.0 * Constants.getWheelCircumference();
//   }
  
//   public void positionPDauxF(double leftPos, double leftFF, double rightPos, double rightFF) {
// 		leftMaster.selectProfileSlot(1, 0);
// 		rightMaster.selectProfileSlot(1, 0);

// 		leftMaster.set(ControlMode.Position, MotionUtils.distanceToRotations(leftPos, Constants.getWheelCircumference()) * 4096, DemandType.ArbitraryFeedForward, leftFF);
// 		rightMaster.set(ControlMode.Position, MotionUtils.distanceToRotations(rightPos, Constants.getWheelCircumference()) * 4096, DemandType.ArbitraryFeedForward, -rightFF);
//   }
//   public void periodic(){
//   SmartDashboard.putNumber("LMPos", leftMaster.getSelectedSensorPosition());
//   SmartDashboard.putNumber("RMPos", rightMaster.getSelectedSensorPosition());
// }
//   @Override
//   public void initDefaultCommand() {
//     // Set the default command for a subsystem here.
//     setDefaultCommand(new DriveCommand(OI.throttle,OI.turn));
//   }

@Override
public void periodic(){
	
	//setDefaultCommand(new DriveCommand(OI.throttle, OI.turn));
	arcade(OI.throttle.getAsDouble(), OI.turn.getAsDouble());
	System.out.print("running");

}
//   public double inches(int ticks) {
// 		return MotionUtils.rotationsToDistance(MotionUtils.ticksToRotations(ticks, 4096, 1), Constants.getWheelCircumference());
//   }
  
//   public boolean atTarget() {
// 		return Math.abs(inches(leftMaster.getClosedLoopError(0))) < Constants.kDrive_Motion_Tolerance && Math.abs(inches(leftMaster.getClosedLoopError(0))) < Constants.kDrive_Motion_Tolerance;
//   }

//   public double getLeftPosition() {
// 		return MotionUtils.rotationsToDistance(MotionUtils.ticksToRotations(leftMaster.getSelectedSensorPosition(0), 4096, 1), Constants.getWheelCircumference());
// 	}
	
// 	public double getRightPosition() {
// 		return MotionUtils.rotationsToDistance(MotionUtils.ticksToRotations(rightMaster.getSelectedSensorPosition(0), 4096, 1), Constants.getWheelCircumference());
//   }
  
//   public void setBrake(boolean brake) {
// 	//	NeutralMode mode = brake ? NeutralMode.Brake : NeutralMode.Coast;
// 		leftMaster.setNeutralMode(NeutralMode.Brake);
// 		leftFollower.setNeutralMode(NeutralMode.Brake);
// 		// leftFollowerB.setNeutralMode(mode);
// 		// leftFollowerC.setNeutralMode(mode);
// 		rightMaster.setNeutralMode(NeutralMode.Brake);
// 		rightFollower.setNeutralMode(NeutralMode.Brake);
// 		// rightFollowerB.setNeutralMode(mode);
// 		// rightFollowerC.setNeutralMode(mode);
// 	}
  
}