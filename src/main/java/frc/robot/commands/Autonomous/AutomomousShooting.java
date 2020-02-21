/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;


import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;

public class AutomomousShooting extends SequentialCommandGroup {
  /**
   * Creates a new AutomomousShooting.
   */
  public AutomomousShooting(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(new DrivetrainOn(drivetrain),
      new WaitCommand(5),
      new DrivetrainOff(drivetrain),
      new PrintCommand("Use Sams Shooter commands"),
      new WaitCommand(9),
      new PrintCommand("use Sam's Shooter off command"),
      new PrintCommand("do a spin "),);
  }
}