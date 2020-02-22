/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.SmartShooter;
import frc.robot.commands.Autonomous.DriveForward;
import frc.robot.commands.Climber.ManualDown;
import frc.robot.commands.Climber.ManualUp;
import frc.robot.commands.Indexer.FeedShooter;
import frc.robot.commands.Indexer.ForwardConveyer;
import frc.robot.commands.Indexer.ReverseFeedShooter;
import frc.robot.commands.Indexer.StopFeeder;
import frc.robot.commands.Intake.ChangeIntakeSolenoidState;
import frc.robot.commands.Intake.IntakeCells;
import frc.robot.commands.Intake.ReverseIntakeCells;
import frc.robot.commands.Intake.StopIntake;
import frc.robot.commands.Shooter.MaintainRPM;
import frc.robot.commands.Shooter.StopMotors;
import frc.robot.commands.Vision.ToggleLimelightLEDS;
import frc.robot.commands.Vision.ToggleLimelightVisionMode;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private Shooter shooter;
  private Intake intake;
  private Drivetrain drivetrain;
  private Climber climber;
  private Vision vision;
  private Indexer indexer;

  private DriveForward driveForward;

  private JoystickButton reverseFeedShooterButton;
  private JoystickButton toggleLEDS;
  private JoystickButton toggleVisionMode;
  private JoystickButton changeIntakeSolenoidState;
  private JoystickButton intakeCells;
  private JoystickButton reverseIntakeCells;
  private JoystickButton manualUp;
  private JoystickButton manualDown;
  private JoystickButton smartShooter;

  // Joysticks 
  private Joystick leftJoystick;
  private Joystick rightJoystick;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //Subsystems
    vision = new Vision();
    drivetrain = new Drivetrain();
    intake = new Intake();
    shooter = new Shooter();
    indexer = new Indexer(); 
    climber = new Climber();


    //commands
    driveForward = new DriveForward(drivetrain);

    defineButtons();

    // Configure the button bindings
    configureButtonBindings();

    RobotProperties.load();
    
  }

  private void defineButtons() {
    leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_PORT_ID);
    rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_PORT_ID);

    //maintainRPM, forwardConveyorButton, and reverseConveyorButton are deprecated

    //maintainRPM = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_MAINTAIN_RPM);
    //forwardConveyorButton = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_FORWARD_CONVEYOR);
    //reverseConveyorButton = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_REVERSE_CONVEYOR);

    toggleLEDS = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_LIMELIGHT_LEDS);
    toggleVisionMode = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_VISION_MODE);

    reverseFeedShooterButton = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_REVERSE_FEED_SHOOTER);
    
    intakeCells = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INTAKE_CELLS);
    reverseIntakeCells = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_REVERSE_INTAKE_CELLS);

    changeIntakeSolenoidState = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_CHANGE_INTAKE_SOLENOID_STATE);

    manualUp = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_CLIMBER_MANUAL_UP);
    manualDown = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_CLIMBER_MANUAL_DOWN);

    smartShooter = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_SMART_SHOOTER);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //drivetrain command...its quirky and relatable and not like other buttons
    drivetrain.setDefaultCommand(new DriveWithJoysticks(leftJoystick, rightJoystick, drivetrain));

    toggleLEDS.whenPressed(new ToggleLimelightLEDS(vision));
    toggleVisionMode.whenPressed(new ToggleLimelightVisionMode(vision));

    toggleLEDS.whenPressed(new ToggleLimelightLEDS(vision));
    toggleVisionMode.whenPressed(new ToggleLimelightVisionMode(vision));

    intakeCells.whenActive(new IntakeCells(intake));
    intakeCells.whenInactive(new StopIntake(intake));
    
    reverseIntakeCells.whenActive(new ReverseIntakeCells(intake));
    reverseIntakeCells.whenInactive(new StopIntake(intake));

    changeIntakeSolenoidState.whenPressed(new ChangeIntakeSolenoidState(intake));

    manualUp.whileHeld(new ManualUp(climber));
    manualDown.whileHeld(new ManualDown(climber));

    smartShooter.whenHeld(new SmartShooter(indexer, shooter));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveForward;
  }
}