/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveForward extends SequentialCommandGroup {
  //private Drivetrain m_Drivetrain;
  /**
   * Creates a new DriveForward.
   */
  public DriveForward(Drivetrain drivetrain) {
    //m_Drivetrain = subsystem;
    addCommands(new DrivetrainOn(drivetrain), new WaitCommand(5), new DrivetrainOff(drivetrain) );
    
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand())
  }
}
