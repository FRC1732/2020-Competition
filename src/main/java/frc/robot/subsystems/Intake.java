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
  private VictorSPX intakeMotor;
  private Solenoid intakeSolenoid;

  public Intake() {
    intakeMotor = new VictorSPX(Constants.INTAKE_INTAKEMOTOR_ID);
    intakeSolenoid = new Solenoid(Constants.INTAKE_INTAKESOLENOID_ID);
  }
  // sets the intake motor to a speed 
  // starts the intake 
  public void intakeCells(){
    intakeMotor.set(ControlMode.PercentOutput, .6);
  }
  // reverses the intake motor
  public void reverseIntakeCells(){
    intakeMotor.set(ControlMode.PercentOutput, -1);
  }
  // stops the intake motor
  public void stopIntake(){
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }
  // toggle the solenoid.
  public void toggleSolenoidState(){
    intakeSolenoid.set(!intakeSolenoid.get());
  }

  public void setSolenoidExtended(){
    intakeSolenoid.set(true);
  }

  public void setSolenoidRetracted(){
    intakeSolenoid.set(false);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}