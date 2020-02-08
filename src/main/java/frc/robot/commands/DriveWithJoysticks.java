/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveWithJoysticks extends CommandBase {
  private Joystick leftJoystick;
  private Joystick rightJoystick;
  private Drivetrain drivetrain;
  /**
   * Creates a new DriveWithJoysticks.
 * @param rightJoystick
 * @param leftJoystick
 * @param drivetrainSubsystem
   */
  public DriveWithJoysticks(Joystick leftJoystick, Joystick rightJoystick, Drivetrain drivetrainSubsystem) {
    addRequirements(drivetrainSubsystem);
    this.leftJoystick = leftJoystick;
    this.rightJoystick = rightJoystick;
    this.drivetrain = drivetrainSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double left = leftJoystick.getY() * leftJoystick.getY() * Math.signum(leftJoystick.getY());
    double right = rightJoystick.getY() * rightJoystick.getY() * Math.signum(rightJoystick.getY());

    drivetrain.set(left,right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
