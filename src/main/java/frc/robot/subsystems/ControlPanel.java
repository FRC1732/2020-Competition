/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanel extends SubsystemBase {
  /**
   * Creates a new ControlPanel.
   */
  private VictorSPX controlPanelMotor;
  private Solenoid controlPanelTrench;
  private final I2C.Port i2cPort;
  private final ColorSensorV3 colorSensor;
  private Color gameData;
  private Color startingColor;

  public ControlPanel() {    
    controlPanelMotor = new VictorSPX(Constants.CONTROLPANELMANIP_MOTOR_ID);
    controlPanelTrench = new Solenoid(Constants.CONTROLPANELMANIP_TRENCH_SOLENOID_ID);
    i2cPort = I2C.Port.kOnboard;
    colorSensor = new ColorSensorV3(i2cPort);
  }
  // this gets the color
  public Color readColor() {
    return colorSensor.getColor();
  }
  // Gets the speed of the control panel motor 
  public double getControlPanelMotor() {
    return controlPanelMotor.getSelectedSensorVelocity();
  }
  // Gets the position of the control panel trench
  public boolean getControlPanelTrench() {
    return controlPanelTrench.get();
  }
  // Sets the control panel motor
  public void setControlPanelMotor(double speed) {
    controlPanelMotor.set(ControlMode.PercentOutput, speed);
  }
  // sets the control panel trench 
  public void toggleControlPanelTrenchState() {
    controlPanelTrench.set(!controlPanelTrench.get());
  }

  public void setStartingColor(){
    startingColor = roundColor(readColor());
  }

  public Boolean atPosition(){
    return readColor().equals(gameData);
  }

  public Color roundColor(Color c){
    double red = c.red;
    double blue = c.blue; 
    double green = c.green;
    if(red > .5) {
      red = 1;
    } else {
      red =0;
    }
    if(blue > .5) {
      blue = 1;
    } else {
      blue =0;
    }
    if(green > .5) {
      green = 1;
    } else {
      green =0;
    }
    return new Color(red,green,blue);
  }

  public void ProcessGameData(){
    switch(DriverStation.getInstance().getGameSpecificMessage()){
      case("R"): gameData = Color.kRed; break;
      case("G"): gameData = Color.kGreen; break;
      case("B"): gameData = Color.kCyan; break;
      case("Y"): gameData = Color.kYellow; break;
    }
  }
  
  public void spinMotor(){
    controlPanelMotor.set(ControlMode.PercentOutput, .5);
  }

  public void stopMotor(){
    controlPanelMotor.set(ControlMode.PercentOutput, 0);
  }
  
  public void rotation() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
