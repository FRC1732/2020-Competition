/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.SmartShooter;
import frc.robot.commands.Autonomous.AutomomousShooting;
import frc.robot.commands.Autonomous.DriveForward;
import frc.robot.commands.Autonomous.FiveBallShooting;
import frc.robot.commands.Climber.ManualDown;
import frc.robot.commands.Climber.ManualUp;
import frc.robot.commands.Indexer.FeedShooter;
import frc.robot.commands.Indexer.ForwardConveyer;
import frc.robot.commands.Indexer.ReverseFeedShooter;
import frc.robot.commands.Indexer.StopFeeder;
import frc.robot.commands.Intake.ChangeIntakeSolenoidState;
import frc.robot.commands.Intake.IntakeCells;
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
  /**
   *
   */
  private static final String AUTONOMOUS_SHOOTING = "Autonomous Shooting";
  /**
   *
   */
  private static final String FIVE_BALL_SHOOTING = "Five ball shooting";
  /**
   *
   */
  private static final String DRIVE_FORWARD = "Drive forward";
  // The robot's subsystems and commands are defined here...
  private Shooter shooter;
  private Intake intake;
  private Drivetrain drivetrain;
  private Climber climber;
  private Vision vision;
  private Indexer indexer;

  private DriveWithJoysticks driveWithJoysticksCommand;
  private DriveForward driveForward;

  private JoystickButton maintainRPM;
  private JoystickButton toggleLEDS;
  private JoystickButton toggleVisionMode;
  private JoystickButton changeIntakeSolenoidState;
  private JoystickButton intakeCells;
  private JoystickButton reverseIntakeCells;
  private JoystickButton feedShooterButton;
  private JoystickButton reverseFeedShooterButton;
  private JoystickButton stopFeedShooterButton;
  private JoystickButton forwardConveyorButton;
  private JoystickButton reverseConveyorButton;
  private JoystickButton manualUp;
  private JoystickButton manualDown;
  private JoystickButton smartShooter;

  // Joysticks 
  private Joystick leftJoystick;
  private Joystick rightJoystick;
  private AutomomousShooting automomousShooting;
  private FiveBallShooting fiveBallShooting; 

  private SendableChooser<String> autoModeOptions;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Subsystems
    vision = new Vision();
    drivetrain = new Drivetrain();
    shooter = new Shooter();
    indexer = new Indexer(); 
    climber = new Climber();


    // commands
    driveForward = new DriveForward(drivetrain);
    driveWithJoysticksCommand = new DriveWithJoysticks(leftJoystick, rightJoystick, drivetrain);
    automomousShooting = new AutomomousShooting(drivetrain);

    defineButtons();

    // Configure the button bindings
    configureButtonBindings();

    RobotProperties.load();

    initShuffleboard();
    
  }

  private void defineButtons() {
    leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_PORT_ID);
    rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_PORT_ID);

    // this has a permanent button binding
    // maintainRPM = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_MAINTAIN_RPM);

    toggleLEDS = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_LIMELIGHT_LEDS);
    toggleVisionMode = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_VISION_MODE);

    feedShooterButton = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_FEED_SHOOTER);
    reverseFeedShooterButton = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_REVERSE_FEED_SHOOTER);
    forwardConveyorButton = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_FORWARD_CONVEYOR);
    reverseConveyorButton = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_REVERSE_CONVEYOR);

    // this has a permanent button binding
    intakeCells = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INTAKE_CELLS);

    changeIntakeSolenoidState = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_CHANGE_INTAKE_SOLENOID_STATE);
    reverseIntakeCells = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INTAKE_CELLS);
    // stopIntake = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_STOP_INTAKE);
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

    // drivetrain button
   // drivetrain.setDefaultCommand(new DriveWithJoysticks(leftJoystick, leftJoystick, drivetrain));

    // all the indexer buttons are placeholders and the structure isn't fully worked
    // out
    // indexer buttons
   //feedShooterButton.whenPressed(new FeedShooter(indexer));
   // reverseFeedShooterButton.whenPressed(new ReverseFeedShooter(indexer));
    //stopFeedShooterButton.whenPressed(new StopFeeder(indexer));
    //forwardConveyorButton.whenPressed(new ForwardConveyer(indexer));
    //reverseConveyorButton.whenPressed(new ReverseFeedShooter(indexer));

    // shooter buttons
    //maintainRPM.whenActive(new MaintainRPM(shooter));
    //maintainRPM.whenInactive(new StopMotors(shooter));

    // vision buttons
    //toggleLEDS.whenPressed(new ToggleLimelightLEDS(vision));
    //toggleVisionMode.whenPressed(new ToggleLimelightVisionMode(vision));

    // intake buttons
    //intakeCells.whenActive(new IntakeCells(intake));
    //intakeCells.whenInactive(new StopIntake(intake));
    //changeIntakeSolenoidState.whenPressed(new ChangeIntakeSolenoidState(intake));

    //manualUp.whileHeld(new ManualUp(climber));
    //manualDown.whileHeld(new ManualDown(climber));

    //smartShooter.whenHeld(new SmartShooter(indexer, shooter));

  }

  private void initShuffleboard(){
    autoModeOptions = new SendableChooser<>();
    autoModeOptions.setDefaultOption(DRIVE_FORWARD, DRIVE_FORWARD);
    autoModeOptions.addOption(FIVE_BALL_SHOOTING, FIVE_BALL_SHOOTING);
    autoModeOptions.addOption(AUTONOMOUS_SHOOTING, AUTONOMOUS_SHOOTING);
    SmartDashboard.putData("Auto data", autoModeOptions);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    //Command selectAutoCommand = new SelectCommand(Map.ofEntries(
      //Map.entry("Drive forward", )
    //), selector)

    Map<Object, Command> selectableCommands = new HashMap<>();
    selectableCommands.put(AUTONOMOUS_SHOOTING, automomousShooting);
    selectableCommands.put(DRIVE_FORWARD, driveForward);
    selectableCommands.put(FIVE_BALL_SHOOTING,new PrintCommand(FIVE_BALL_SHOOTING));
    
    Supplier<Object> selector = this::getOperatingAutoCommand;
    
    return new SelectCommand(selectableCommands, selector);
  }

  private String getOperatingAutoCommand() {
    // select value from shuffle board
    String selected = autoModeOptions.getSelected();
    System.out.println(" hey selected option is "+selected);
    return selected;
  }
}