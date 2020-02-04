/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ControlPanel extends SubsystemBase {
  /**
   * Creates a new ControlPanel.
   */
  private CANSparkMax controlPanelMotor = new CANSparkMax(Constants.CONTROLPANELMANIP_MOTOR_ID, MotorType.kBrushless);
  private Solenoid controlPanelTrench = new Solenoid(Constants.CONTROLPANELMANIP_TRENCH_HARDSTOP_SOLENOID_ID);

  public ControlPanel() {
  }

  public void setControlPanelMotor(double speed) {
    controlPanelMotor.set(speed);
  }

  public void setControlPanelTrench(Solenoid controlPanelTrench) {
    this.controlPanelTrench = controlPanelTrench;
  }

  public CANSparkMax getControlPanelMotor() {
    return controlPanelMotor;
  }

  public Solenoid getControlPanelTrench() {
    return controlPanelTrench;
  }
  
  public void getColor() {
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
