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

  public Color readColor() {
    return colorSensor.getColor();
  }

  public VictorSPX getControlPanelMotor() {
    return controlPanelMotor;
  }

  public Solenoid getControlPanelTrench() {
    return controlPanelTrench;
  }

  public void setControlPanelMotor(double speed) {
    controlPanelMotor.set(ControlMode.PercentOutput, speed);
  }

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
