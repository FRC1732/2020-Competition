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

public class PerpendicularAlign extends CommandBase {
  private double distance;
  private double angle;
  private Drivetrain drivetrain;
  private Vision vision;

  /**
   * Creates a new perpendicularAlign.
   */
  public PerpendicularAlign(Drivetrain drivetrain, Vision vision, double distance) {
    addRequirements(drivetrain, vision);
    this.drivetrain = drivetrain;
    this.vision = vision;
    this.distance = distance;
    angle = Math.acos(distance/vision.getDistance());
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!isFinished()) drivetrain.set(-0.075 * Math.signum(vision.getX()+angle), 0.075 * Math.signum(vision.getX()+angle));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(angle-vision.getX()) < 2;
  }
}
