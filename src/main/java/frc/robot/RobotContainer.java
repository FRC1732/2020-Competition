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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Drivetrain.DriveWithJoysticks;
import frc.robot.commands.Drivetrain.LockSteering;
import frc.robot.commands.PrintCommand;
import frc.robot.commands.Autonomous.AutomomousShooting;
import frc.robot.commands.Autonomous.FiveBallShooting;
import frc.robot.commands.Autonomous.ThreeBall;
import frc.robot.commands.Intake.IntakeCells;
import frc.robot.commands.Intake.ToggleIntakeSolenoidState;
import frc.robot.commands.Shooter.SetShooterMode;
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
  private static final String THREE_BALL = "Three ball";
  // The robot's subsystems and commands are defined here...
  private Shooter shooter;
  private Intake intake;
  private Drivetrain drivetrain;
  private Climber climber;
  private Vision vision;
  private Indexer indexer;
  private ControlPanel controlPanel;

  // Driver Joysticks
  private Joystick leftJoystick;
  private Joystick rightJoystick;
  private AutomomousShooting automomousShooting;
  private FiveBallShooting fiveBallShooting;
  private ThreeBall threeBall;

  private SendableChooser<String> autoModeOptions;

  // LeftJoystick Buttons
  private JoystickButton intakeCells;
  private JoystickButton toggleIntakeSolenoidState;

  // RightJoystick Buttons
  private JoystickButton smartShooter;
  private JoystickButton lockSteering;
  private JoystickButton visionAlign;

  // Operator Joysticks
  private Joystick operatorJoystick;

  // Operator1Joystick Buttons
  private JoystickButton o_enableClimb;
  private JoystickButton o_manualUp;
  private JoystickButton o_manualDown;
  private JoystickButton o_manualSpeedUp;
  private JoystickButton o_manualSpeedDown;
  private JoystickButton o_maintainRPM;
  private JoystickButton o_unallocatedButton;
  private JoystickButton o_changeIntakeSolenoidState;
  private JoystickButton o_intake;
  private JoystickButton o_reverseIntake;
  private JoystickButton o_feedShooter;
  private JoystickButton o_reverseFeedShooter;

  // Composed triggers
  private Trigger shoot;
  private Trigger climbUp;
  private Trigger climbDown;

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
    threeBall = new ThreeBall(shooter, indexer, operatorJoystick);
    automomousShooting = new AutomomousShooting(drivetrain);

    // Define Buttons
    defineButtons();

    // Configure the button bindings
    configureButtonBindings();

    drivetrain.setDefaultCommand(new DriveWithJoysticks(leftJoystick, rightJoystick, drivetrain));
    vision.setDefaultCommand(new BasicVisionAlign(vision));
    //shooter.setDefaultCommand(new SetShooterMode(shooter, operatorJoystick));

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
    lockSteering = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_LOCK_STEERING);
    visionAlign = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_VISION_ALIGN);

    // Operator joystick declaration
    operatorJoystick = new Joystick(Constants.OPERATOR_JOYSTICK_PORT_ID);

    // Operator1Joystick button declaration
    o_enableClimb = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_ENABLE_CLIMB);
    o_manualUp = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_MANUAL_UP);
    o_manualDown = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_MANUAL_DOWN);
    o_manualSpeedUp = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_MANUAL_SPEED_UP);
    o_manualSpeedDown = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_MANUAL_SPEED_DOWN);
    o_maintainRPM = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_MAINTAIN_RPM);
    o_unallocatedButton = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_UNALLOCATED_BUTTON);
    o_changeIntakeSolenoidState = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_CHANGE_INTAKE_SOLENOID_STATE);
    o_intake = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_FEED_INTAKE);
    o_reverseIntake = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_REVERSE_INTAKE);
    o_feedShooter = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_FEED_SHOOTER);
    o_reverseFeedShooter = new JoystickButton(operatorJoystick, Constants.O_JOYSTICKBUTTON_REVERSE_FEED_SHOOTER);

    // Trigger declaration
    shoot = o_maintainRPM.and(smartShooter);
    climbUp = o_enableClimb.and(o_manualUp);
    climbDown = o_enableClimb.and(o_manualDown);   

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
    lockSteering.whileHeld(new LockSteering(drivetrain, rightJoystick));

    // Operator1Joystick button configuration
    //enable climb do the shuffleboard
    
    // o_enableClimb.whenActive(new EnableClimb());
    // o_enableClimb.whenInactive(new DisableClimb());
    o_enableClimb.whenActive(new PrintCommand("o_enableClimb active"));
    o_enableClimb.whenInactive(new PrintCommand("o_enableClimb inactive"));
    // o_manualSpeedUp.whenPressed(new ShooterManualUp(shooter));
    o_manualSpeedUp.whenPressed(new PrintCommand("o_manualSpeedUp pressed"));
    // o_manualSpeedDown.whenPressed(new ShooterManualDown(shooter));
    o_manualSpeedDown.whenPressed(new PrintCommand("o_manualSpeedDown pressed"));
    // o_maintainRPM.whenActive(new MaintainRPM(shooter, operatorJoystick));
    // o_maintainRPM.whenInactive(new StopMotors(shooter));
    o_maintainRPM.whenActive(new PrintCommand("o_maintainRPM active"));
    o_maintainRPM.whenInactive(new PrintCommand("o_maintainRPM inactive"));
    // o_unallocatedButton.whenActive(new ToggleLimelightLEDS(vision));
    // o_unallocatedButton.whenInactive(new ToggleLimelightLEDS(vision));
    o_unallocatedButton.whenActive(new PrintCommand("o_unallocatedButton active"));
    o_unallocatedButton.whenInactive(new PrintCommand("o_unallocatedButton inactive"));
    // o_changeIntakeSolenoidState.whenActive(new SetIntakeSolenoidExtended(intake));
    // o_changeIntakeSolenoidState.whenInactive(new SetIntakeSolenoidRetracted(intake));
    o_changeIntakeSolenoidState.whenActive(new PrintCommand("o_changeIntakeSolenoidState active"));
    o_changeIntakeSolenoidState.whenInactive(new PrintCommand("o_changeIntakeSolenoidState inactive"));
    // o_intake.whenHeld(new IntakeCells(intake));
    o_intake.whenActive(new PrintCommand("o_intake active"));
    o_intake.whenInactive(new PrintCommand("o_intake inactive"));
    // o_reverseIntake.whenHeld(new ReverseIntakeCells(intake));
    o_reverseIntake.whenActive(new PrintCommand("o_reverseIntake active"));
    o_reverseIntake.whenInactive(new PrintCommand("o_reverseIntake inactive"));
    // o_feedShooter.whenHeld(new FeedShooter(indexer));
    o_feedShooter.whenActive(new PrintCommand("o_feedShooter active"));
    o_feedShooter.whenInactive(new PrintCommand("o_feedShooter inactive"));
    // o_reverseFeedShooter.whenHeld(new ReverseFeedShooter(indexer));
    o_reverseFeedShooter.whenActive(new PrintCommand("o_reverseFeedShooter active"));
    o_reverseFeedShooter.whenInactive(new PrintCommand("o_reverseFeedShooter inactive"));

    // // Trigger declaration
    // shoot.whenActive(new SmartShooter(indexer, shooter, operatorJoystick));
    // shoot.whenInactive(new StopSmartShooter(shooter, indexer));
    shoot.whenActive(new PrintCommand("shoot active"));
    shoot.whenInactive(new PrintCommand("shoot inactive"));
    // climbUp.whenActive(new ManualUp(climber));
    // climbUp.whenInactive(new StopClimber(climber));
    climbUp.whenActive(new PrintCommand("climbUp active"));
    climbUp.whenInactive(new PrintCommand("climbUp inactive"));
    // climbDown.whenActive(new ManualDown(climber));
    // climbDown.whenInactive(new StopClimber(climber));
    climbDown.whenActive(new PrintCommand("climbDown active"));
    climbDown.whenInactive(new PrintCommand("climbDown inactive"));
  }

  private void initShuffleboard(){
    // autoModeOptions = new SendableChooser<>();
    // autoModeOptions.setDefaultOption(THREE_BALL, THREE_BALL);
    // autoModeOptions.addOption(FIVE_BALL_SHOOTING, FIVE_BALL_SHOOTING);
    // autoModeOptions.addOption(AUTONOMOUS_SHOOTING, AUTONOMOUS_SHOOTING);
    // SmartDashboard.putData("Auto selection", autoModeOptions);
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
    selectableCommands.put(FIVE_BALL_SHOOTING, fiveBallShooting) ;
    selectableCommands.put(THREE_BALL, threeBall);
    
    Supplier<Object> selector = this::getOperatingAutoCommand;

    return threeBall; //new SelectCommand(selectableCommands, selector);
  }

  private String getOperatingAutoCommand() {
    // select value from shuffle board
    String selected = autoModeOptions.getSelected();
    System.out.println(" hey selected option is "+selected);
    return selected;
    }
}