/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Subsystem_Climber extends SubsystemBase {
  /**
   * Creates a new Subsystem_Climber.
   */
  TalonSRX climberRight = new TalonSRX(Constants.CLIMBER_RIGHT_ID);
  TalonSRX climberLeft = new TalonSRX(Constants.CLIMBER_LEFT_ID);
  VictorSPX climberDrive = new VictorSPX(Constants.CLIMBER_DRIVE_ID);
  Solenoid solenoid = new Solenoid(Constants.CLIMBING_SOLENOID_ID);

  //need button to control each motor
  /**
   * @param climberLeft the climberLeft to set
   */
  public void setRight(double right) {
    climberRight.set(ControlMode.PercentOutput, right);
  }

  public void setLeft(double left) {
    climberLeft.set(ControlMode.PercentOutput, left);
  }

  public void setDrive(double drive) {
    climberLeft.set(ControlMode.PercentOutput, drive);
  }

  public void extendLeft(boolean extend) {
    solenoid.set(extend);
  }

  public Subsystem_Climber() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
