/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.SmartShooter;
import frc.robot.commands.Intake.ToggleIntakeSolenoidState;
import frc.robot.commands.Shooter.MaintainRPM;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveForward extends SequentialCommandGroup {
  // private Drivetrain m_Drivetrain;
  /**
   * Creates a new DriveForward.
   */
  public DriveForward(Drivetrain drivetrain, Intake intake, Shooter shooter, Indexer indexer) {
    // m_Drivetrain = subsystem;
    addCommands( new ThreeBall(shooter, indexer)); // new ToggleIntakeSolenoidState(intake),new
                                                  // DriveDistance(drivetrain,- 55, intake), new SmartShooter(indexer,
                                                  // shooter) );
    
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand())
  }
}
