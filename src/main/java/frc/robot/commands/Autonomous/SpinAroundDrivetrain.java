/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class SpinAroundDrivetrain extends InstantCommand {
  private Drivetrain drivetrain;
  
  public SpinAroundDrivetrain(Drivetrain drivetrain) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  public void initialize() {
    drivetrain.set(0.5, -0.5);
  }

}
