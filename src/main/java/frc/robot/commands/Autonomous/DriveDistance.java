/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class DriveDistance extends CommandBase {
  private Drivetrain drivetrain;
  private double distance;
  private double leftStart;
  private double rightStart;
  private double scale = 1.0;
  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(Drivetrain drivetrain, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    this.distance = distance;
    this.drivetrain = drivetrain;
  }
// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    leftStart = drivetrain.getLeftEncoder();
    rightStart = drivetrain.getRightEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.set(scale*-0.2 * Math.signum(distance), scale*-0.2 * Math.signum(distance));   

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(drivetrain.getLeftEncoder() - leftStart) > Math.abs(distance);
  }
}
