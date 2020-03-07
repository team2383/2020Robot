package frc.robot.Subsystems;


import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HAL;
import frc.robot.Field;

import jaci.pathfinder.Pathfinder;


public class LimelightSubsystem {

  private double get(String name) {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry(name).getDouble(0);
  }

  public boolean hasTargets() {
    return get("tv") > 0;
  }

  public double pipeline() {
    return get("getpipe");
  }

  public double xOffset() {
    return get("tx");
  }

  public double yOffset() {
    return get("ty");
  }

  public double area() {
    return get("ta");
  }

  public double skew() {
    return get("ts");
  }

  public double latency() {
    return get("tl");
  }

  public double shortSideLength() {
    return get("tshort");
  }

  public double longSideLength() {
    return get("tlong");
  }

  public double horizontalLength() {
    return get("thoriz");
  }

  public double verticalLength() {
    return get("tvert");
  }

  public double camtran()
  {
    return get("camtran");
  }
  
  public void setPipeline(double pipeline) {
		NetworkTableEntry pipelineEntry = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline");
    	pipelineEntry.setNumber(pipeline);
    }

    public double getDistanceFromTarget()
    {
      //Units: Inches
      double h1 = 21.5; //need to confirm21
      double h2 = 91.5; //need to confirm81
      double netHeight = h2-h1;
      //Unit: Degrees
      double a1 = yOffset();
      double a2 = 90-54.5; //need to confirm
      double netAngle = a1+a2;
    
      double distance = netHeight / (Math.tan(Pathfinder.d2r(netAngle)) );
    
      if(hasTargets() == true)
      {
        return distance;
      }
      else
      {
        return 0.0;
      }

    }

    public double desiredHoodAngle(double ballVelocity)
    {
      double velocity = ballVelocity;
      double output = (Math.asin((2*386.09* this.getDistanceFromTarget())/(velocity*velocity)))/2;
      return output;
    }

    public double desiredBallVelocity(double hoodAngle)
    {
      double angle = hoodAngle; //radians
      double output = Math.sqrt((2*386.09*this.getDistanceFromTarget())/(Math.sin(2*angle)));
      return output;
    }
    public void limelightOnOff(){
      if(Field.limelightElisa){
        setPipeline(1);
      }
      else{
        setPipeline(3);
      }
    }



}