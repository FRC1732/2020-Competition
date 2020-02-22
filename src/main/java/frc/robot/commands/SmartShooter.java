/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class SmartShooter extends CommandBase {
  private Indexer indexer;
  private Shooter shooter;
  /**
   * Creates a new SmartShooter.
   */
  public SmartShooter(Indexer indexer, Shooter shooter) {
    addRequirements(indexer, shooter);
    this.indexer = indexer;
    this.shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.maintainRPM();
    if(shooter.getAtSpeed()){
      indexer.feedShooter();
      indexer.forwardConveyor();
    } else {
      indexer.stopConveyor();
      indexer.stopFeeder();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopMotors();
    indexer.stopConveyor();
    indexer.stopFeeder();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
