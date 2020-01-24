/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  CANSparkMax Leftmaster = new CANSparkMax(Constants.DRIVETRAIN_LEFTMASTER_ID, MotorType.kBrushless);
  CANSparkMax Left1 = new CANSparkMax(Constants.DRIVETRAIN_LEFT1_ID, MotorType.kBrushless);
  CANSparkMax Left2 = new CANSparkMax(Constants.DRIVETRAIN_LEFT2_ID, MotorType.kBrushless);
  CANSparkMax Rightmaster = new CANSparkMax(Constants.DRIVETRAIN_RIGHTMASTER_ID, MotorType.kBrushless);
  CANSparkMax Right1 = new CANSparkMax(Constants.DRIVETRAIN_RIGHT1_ID, MotorType.kBrushless);
  CANSparkMax Right2 = new CANSparkMax(Constants.DRIVETRAIN_RIGHT2_ID, MotorType.kBrushless);
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    Left1.follow(Leftmaster);
    Left2.follow(Leftmaster);
    Right1.follow(Rightmaster);
    Right2.follow(Rightmaster);
  }

  public void set(double Left, double Right) {
    Leftmaster.set(Left);
    Rightmaster.set(Right);
  }

  public void stop() {
    set(0, 0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
