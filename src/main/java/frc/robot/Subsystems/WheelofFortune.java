package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.DriverStation;


public class WheelofFortune{

  WPI_VictorSPX wheelGo = new WPI_VictorSPX(RobotMap.wheelPort);
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  

  public WheelofFortune(){
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 
  }


  public void init(){
    
  }

  public void wheelie(){
    wheelGo.set(ControlMode.PercentOutput, 0.5);
  }


  public void wheelieControl() throws InterruptedException {
    //sensor control
    String gameData;
    String colorString;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    Color detectedColorPrimer = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColorPrimer);

    
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          if(colorString == "Blue"){
            break;
          }
          else{
            wheelGo.set(ControlMode.PercentOutput, 0.5);
          }
          
        case 'G' :
          if(colorString == "Green"){
            break;
          }
          else{
            wheelGo.set(ControlMode.PercentOutput, 0.5);
          }
          
        case 'R' :
          if(colorString == "Red"){
            break;
          }
          else{
            wheelGo.set(ControlMode.PercentOutput, 0.5);
          }
          
        case 'Y' :
          if(colorString == "Yellow"){
            break;
          }
          else{
          wheelGo.set(ControlMode.PercentOutput, 0.5);
        }

        default :
          //This is corrupt data
          break;
      }
    } else {
      //Rotation Control
      for(int x = 0; x < 8;){
        Color detectedColorDos = m_colorSensor.getColor();
        ColorMatchResult match2 = m_colorMatcher.matchClosestColor(detectedColorDos);
  
        wheelGo.set(ControlMode.PercentOutput, 0.5);
        if(match.color == match2.color){
          wait(300);
          x = x + 1;
          
        }
  
        break;
      }
      

    }
    

  }
    
  
  public void off(){
    wheelGo.set(ControlMode.PercentOutput, 0);
  }


}
