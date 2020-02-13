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

  public void setIntakeMotor (double motor){
    intakeMotor.set(ControlMode.PercentOutput, motor);
  }

  public void intakeCells(){
    setIntakeMotor(1);
  }

  public void reverseIntakeCells(){
    setIntakeMotor(-1);
  }

  public void stopIntake(){
    setIntakeMotor(0);
  }

  public void toggleSolenoidState(){
    intakeSolenoid.set(!intakeSolenoid.get());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
