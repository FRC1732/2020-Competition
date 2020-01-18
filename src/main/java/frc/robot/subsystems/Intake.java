/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  VictorSPX intakeMotor = new VictorSPX(Constants.INTAKE_INTAKE_MOTOR_ID);
  Solenoid intakeSolenoid = new Solenoid(Constants.INTAKE_INTAKE_SOLENOID_ID);

  public Intake() {
  }

  public void setIntakeMotor (double motor){
    intakeMotor.set(ControlMode.PercentOutput, motor);
  }

  public void extendIntake(boolean extendIntakeSolenoid){
    intakeSolenoid.set(extendIntakeSolenoid);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
