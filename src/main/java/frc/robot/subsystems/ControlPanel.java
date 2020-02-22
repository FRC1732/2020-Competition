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

  public ControlPanel() {
    controlPanelMotor = new VictorSPX(Constants.CONTROLPANELMANIP_MOTOR_ID);
    controlPanelTrench = new Solenoid(Constants.CONTROLPANELMANIP_TRENCH_HARDSTOP_SOLENOID_ID);
    i2cPort = I2C.Port.kOnboard;
    colorSensor = new ColorSensorV3(i2cPort);
  }
  // this gets the color
  public Color readColor() {
    return colorSensor.getColor();
  }
  // Gets the speed of the control panel motor 
  public VictorSPX getControlPanelMotor() {
    return controlPanelMotor;
  }
  // Gets the position of the control panel trench
  public Solenoid getControlPanelTrench() {
    return controlPanelTrench;
  }
  // Sets the control panel motor
  public void setControlPanelMotor(double speed) {
    controlPanelMotor.set(ControlMode.PercentOutput, speed);
  }
  // sets the control panel trench 
  public void setControlPanelTrench(Solenoid controlPanelTrench) {
    this.controlPanelTrench = controlPanelTrench;
  }
  
  public void rotation() {
  }

  public void position() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
