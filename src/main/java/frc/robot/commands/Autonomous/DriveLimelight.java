/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

public class DriveLimelight extends CommandBase {
  private Drivetrain drivetrain;
  private Vision vision;
  /**
   * Creates a new DriveDistance.
   */
  public DriveLimelight(Drivetrain drivetrain, Vision vision) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain, vision);
    this.vision = vision;
    this.drivetrain = drivetrain;
  }
// Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!isFinished()) drivetrain.set(-0.075 * Math.signum(vision.getX()), 0.075 * Math.signum(vision.getX()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //if(!vision.hasTarget()) return true;
    return Math.abs(vision.getX()) < 1;
  }
}
