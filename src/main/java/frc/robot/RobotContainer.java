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
import frc.robot.commands.ChangeIntakeSolenoidState;
import frc.robot.commands.DecreaseMotorSpeed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.IncreaseMotorSpeed;
import frc.robot.commands.IntakeCells;
import frc.robot.commands.MaintainRPM;
import frc.robot.commands.ReverseIntakeCells;
import frc.robot.commands.StopIntake;
import frc.robot.commands.ToggleLimelightLEDS;
import frc.robot.commands.ToggleLimelightVisionMode;
import frc.robot.commands.Autonomous.DriveForward;
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
  private Vision vision;
  private Indexer indexer;

  private DriveWithJoysticks driveWithJoysticksCommand;
  private DriveForward driveForward;

  private JoystickButton decreaseMotorSpeed;
  private JoystickButton increaseMotorSpeed;
  private JoystickButton maintainRPM;
  private JoystickButton toggleLEDS;
  private JoystickButton toggleVisionMode;
  private JoystickButton feedShooterButton; 
  private JoystickButton reverseFeedShooterButton;
  private JoystickButton changeIntakeSolenoidState;
  private JoystickButton intakeCells;
  private JoystickButton reverseIntakeCells;
  private JoystickButton stopIntake;

  private Joystick leftJoystick;
  private Joystick rightJoystick;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //Subsystems
    vision = new Vision();
    drivetrain = new Drivetrain();
    shooter = new Shooter();
    indexer = new Indexer(); 


    //commands
    driveForward = new DriveForward(drivetrain);
    driveWithJoysticksCommand = new DriveWithJoysticks(leftJoystick,rightJoystick,drivetrain);

    defineButtons();

    // Configure the button bindings
    configureButtonBindings();

    System.out.println(RobotProperties.getProperty("name"));
    
  }

  private void defineButtons() {
    leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_PORT_ID);
    rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_PORT_ID);

    decreaseMotorSpeed = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_DECREASE_MOTOR_SPEED);
    increaseMotorSpeed = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INCREASE_MOTOR_SPEED);
    maintainRPM = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_MAINTAIN_RPM);

    toggleLEDS = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_LIMELIGHT_LEDS);
    toggleVisionMode = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_TOGGLE_VISION_MODE);
    feedShooterButton = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_FEED_SHOOTER);
    reverseFeedShooterButton = new JoystickButton(rightJoystick, Constants.JOYSTICKBUTTON_REVERSE_FEED_SHOOTER);
    intakeCells = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INTAKE_CELLS);
    changeIntakeSolenoidState = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_CHANGE_INTAKE_SOLENOID_STATE);
    reverseIntakeCells = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_INTAKE_CELLS);
    stopIntake = new JoystickButton(leftJoystick, Constants.JOYSTICKBUTTON_STOP_INTAKE);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivetrain.setDefaultCommand(driveWithJoysticksCommand);

    decreaseMotorSpeed.whenPressed(new DecreaseMotorSpeed(shooter));
    increaseMotorSpeed.whenPressed(new IncreaseMotorSpeed(shooter));
    maintainRPM.whenPressed(new MaintainRPM(shooter)); 
    toggleLEDS.whenPressed(new ToggleLimelightLEDS(vision));
    toggleVisionMode.whenPressed(new ToggleLimelightVisionMode(vision));
    maintainRPM.whenPressed(new MaintainRPM(shooter));

    intakeCells.whenPressed(new IntakeCells(intake));
    reverseIntakeCells.whenPressed(new ReverseIntakeCells(intake));
    stopIntake.whenPressed(new StopIntake(intake));
    changeIntakeSolenoidState.whenPressed(new ChangeIntakeSolenoidState(intake));
    

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