/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.SmartShooter;
import frc.robot.commands.Intake.IntakeCells;
import frc.robot.commands.Intake.StopIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FiveBallShooting extends SequentialCommandGroup {
  /**
   * Creates a new FiveBallShooting.
   */
  public FiveBallShooting(Drivetrain drivetrain, Intake intake, Indexer indexer, Shooter shooter ) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

    addCommands(new DrivetrainOn(drivetrain),
     new IntakeCells(intake), 
     new WaitCommand(8), 
     new DrivetrainOff(drivetrain), 
     new StopIntake(intake), 
     new SmartShooter(indexer, shooter) );
    //super();
  }
}
