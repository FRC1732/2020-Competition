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
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.SmartShooter;
import frc.robot.commands.StopSmartShooter;
import frc.robot.commands.TestCommand;
import frc.robot.commands.Autonomous.AutomomousShooting;
import frc.robot.commands.Autonomous.DriveForward;
import frc.robot.commands.Autonomous.FiveBallShooting;
import frc.robot.commands.Climber.ManualUp;
import frc.robot.commands.Climber.StopClimber;
import frc.robot.commands.ControlPanel.ToggleControlPanelTrenchState;
import frc.robot.commands.Indexer.IndexerOverride;
import frc.robot.commands.Indexer.ReverseFeedShooter;
import frc.robot.commands.Intake.IntakeCells;
import frc.robot.commands.Intake.ReverseIntakeCells;
import frc.robot.commands.Intake.SetIntakeSolenoidExtended;
import frc.robot.commands.Intake.SetIntakeSolenoidRetracted;
import frc.robot.commands.Intake.ToggleIntakeSolenoidState;
import frc.robot.commands.Shooter.MaintainRPM;
import frc.robot.commands.Shooter.ShooterManualDown;
import frc.robot.commands.Shooter.ShooterManualUp;
import frc.robot.commands.Shooter.StopMotors;
import frc.robot.commands.Vision.BasicVisionAlign;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ControlPanel;
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
  private ControlPanel controlPanel;

  private DriveForward driveForward;

  // Driver Joysticks
  private Joystick leftJoystick;
  private Joystick rightJoystick;
  private AutomomousShooting automomousShooting;
  private FiveBallShooting fiveBallShooting; 

  private SendableChooser<String> autoModeOptions;

  // LeftJoystick Buttons
  private JoystickButton intakeCells;
  private JoystickButton toggleIntakeSolenoidState;

  // RightJoystick Buttons
  private JoystickButton smartShooter;
  private JoystickButton toggleHardStops;
  private JoystickButton visionAlign;

  // Operator Joysticks
  private Joystick operator1Joystick;
  private Joystick operator2Joystick;

  // Operator1Joystick Buttons
  private JoystickButton o_indexerOverride;
  private JoystickButton o_reverseIntake;
  private JoystickButton o_reverseFeedShooter;
  private JoystickButton o_positionControl;
  private JoystickButton o_rotationControl;
  private JoystickButton o_maintainRPM;
  private JoystickButton o_changeIntakeSolenoidState;
  private JoystickButton o_enableClimb;

  // Operator2Joystick Buttons
  private JoystickButton o_toggleHardstops;
  private JoystickButton o_toggleControlPanel;
  private JoystickButton o_shooterSpeedDown;
  private JoystickButton o_shooterSpeedUp;
  private JoystickButton o_manualUp;

  // Composed triggers
  private Trigger shoot;
  private Trigger climb;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Subsystems
    vision = new Vision();
    drivetrain = new Drivetrain();
    intake = new Intake();
    shooter = new Shooter();
    indexer = new Indexer();
    climber = new Climber();
    controlPanel = new ControlPanel();


    // commands
    driveForward = new DriveForward(drivetrain);
    automomousShooting = new AutomomousShooting(drivetrain);

    // Define Buttons
    defineButtons();

    // Configure the button bindings
    configureButtonBindings();

    drivetrain.setDefaultCommand(new DriveWithJoysticks(leftJoystick, rightJoystick, drivetrain));
    vision.setDefaultCommand(new BasicVisionAlign(vision));

    RobotProperties.load();

    initShuffleboard();
    
  }

  private void defineButtons() {
    // Joystick declaration
    leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_PORT_ID);
    rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_PORT_ID);

    // Leftjoystick button declaration
    intakeCells = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INTAKE_CELLS);
    toggleIntakeSolenoidState = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_INTAKE_SOLENOID_STATE);

    // Rightjoystick button declartion
    smartShooter = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_SMART_SHOOTER);
    toggleHardStops = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_TOGGLE_HARDSTOPS);
    visionAlign = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_VISION_ALIGN);

    // Operator joystick declaration
    operator1Joystick = new Joystick(Constants.OPERATOR_1_JOYSTICK_PORT_ID);
    operator2Joystick = new Joystick(Constants.OPERATOR_2_JOYSTICK_PORT_ID);

    // Operator1Joystick button declaration
    o_indexerOverride = new JoystickButton(operator1Joystick, Constants.O_JOYSTICKBUTTON_INDEXER_OVERRIDE);
    o_reverseIntake = new JoystickButton(operator1Joystick, Constants.O_JOYSTICKBUTTON_REVERSE_INTAKE);
    o_reverseFeedShooter = new JoystickButton(operator1Joystick, Constants.O_JOYSTICKBUTTON_REVERSE_FEED_SHOOTER);
    o_positionControl = new JoystickButton(operator1Joystick, Constants.O_JOYSTICKBUTTON_POSITION_CONTROL);
    o_rotationControl = new JoystickButton(operator1Joystick, Constants.O_JOYSTICKBUTTON_ROTATION_CONTROL);
    o_maintainRPM = new JoystickButton(operator1Joystick, Constants.O_JOYSTICKBUTTON_MAINTAIN_RPM);
    o_changeIntakeSolenoidState = new JoystickButton(operator1Joystick, Constants.O_JOYSTICKBUTTON_CHANGE_INTAKE_SOLENOID_STATE);
    o_enableClimb = new JoystickButton(operator1Joystick, Constants.O_JOYSTICKBUTTON_ENABLE_CLIMB);

    // Operator2Joystick button declaration
    o_toggleHardstops = new JoystickButton(operator2Joystick, Constants.O_JOYSTICKBUTTON_TOGGLE_HARDSTOPS);
    o_toggleControlPanel = new JoystickButton(operator2Joystick, Constants.O_JOYSTICKBUTTON_TOGGLE_CONTROL_PANEL);
    o_shooterSpeedDown = new JoystickButton(operator2Joystick, Constants.O_JOYSTICKBUTTON_MANUAL_SPEED_DOWN);
    o_shooterSpeedUp = new JoystickButton(operator2Joystick, Constants.O_JOYSTICKBUTTON_MANUAL_SPEED_UP);
    o_manualUp = new JoystickButton(operator2Joystick, Constants.O_JOYSTICKBUTTON_MANUAL_CLIMBER_UP);

    // Trigger declaration
    shoot = smartShooter.and(o_maintainRPM);
    climb = o_enableClimb.and(o_manualUp);
    

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {
    // leftJoystick button configuration
    intakeCells.whileHeld(new IntakeCells(intake));
    toggleIntakeSolenoidState.whenPressed(new ToggleIntakeSolenoidState(intake));

    // RightJoystick button configuration
    smartShooter.whenPressed(new TestCommand());
    // toggleHardStops.whenPressed(new ToggleHardstops(shooter));
    // visionAlign.whileHeld(new VisionAlign(vision));

    // Operator1Joystick button configuration
    o_indexerOverride.whenPressed(new IndexerOverride(indexer));
    o_reverseIntake.whenHeld(new ReverseIntakeCells(intake));
    o_reverseFeedShooter.whenHeld(new ReverseFeedShooter(indexer));
    // o_positionControl.whenPressed(new PositionControl(ControlPanel));
    // o_rotationControl.whenPressed(new RotationControl(ControlPanel));
    o_maintainRPM.whenActive(new MaintainRPM(shooter));
    o_maintainRPM.whenInactive(new StopMotors(shooter));
    o_changeIntakeSolenoidState.whenActive(new SetIntakeSolenoidExtended(intake));
    o_changeIntakeSolenoidState.whenInactive(new SetIntakeSolenoidRetracted(intake));

    // Operator2Joystick button configuration
    // o_toggleHardstops.whileHeld(new ToggleHardstops(Shooter));
    o_toggleControlPanel.whenPressed(new ToggleControlPanelTrenchState(controlPanel));
    o_shooterSpeedDown.whenPressed(new ShooterManualDown(shooter));
    o_shooterSpeedUp.whenPressed(new ShooterManualUp(shooter));

    // Trigger declaration
    shoot.whenActive(new SmartShooter(indexer, shooter));
    shoot.whenInactive(new StopSmartShooter(shooter, indexer));
    climb.whenActive(new ManualUp(climber));
    climb.whenActive(new StopClimber(climber));

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