/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.SmartShooter;
import frc.robot.commands.StopSmartShooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class ThreeBall extends CommandBase {
  /**
   * Creates a new ThreeBall.
   */
  private Shooter shooter; 
  private Indexer indexer;
  public ThreeBall(Shooter shooter,Indexer indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, indexer);
    this.shooter = shooter; 
    this.indexer = indexer;
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    new SmartShooter(indexer, shooter);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    new StopSmartShooter(shooter, indexer);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
