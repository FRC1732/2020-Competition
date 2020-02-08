/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
  
    //Declare all constants in the form: SUBSYSTEM_NAME_ID = x;

    //Climber mapping
    public static final int CLIMBER_RIGHT_ID = 13;
    public static final int CLIMBER_LEFT_ID = 32;
    public static final int CLIMBER_DRIVE_ID = 17;
    public static final int CLIMBING_SOLENOID_ID = 1;
    
    //Drivetrain mapping
    public static final int DRIVETRAIN_LEFTMASTER_ID = 33;
    public static final int DRIVETRAIN_LEFT1_ID = 34;
    public static final int DRIVETRAIN_LEFT2_ID = 35;
    public static final int DRIVETRAIN_RIGHTMASTER_ID = 10;
    public static final int DRIVETRAIN_RIGHT1_ID = 11; 
    public static final int DRIVETRAIN_RIGHT2_ID = 12;
    
    //Shooter mapping
    public static final int SHOOTER_SHOOTER_MASTER_ID = 30;
    public static final int SHOOTER_SHOOTER_FOLLOWER_ID = 28;
    public static final int SHOOTER_ADJUSTMENT_SOLENOID_ID = 5;
    public static final int SHOOTER_ROTATION_SOLENOID_ID = 6;

    //Indexer mapping
    public static final int INDEXER_CELLHOLDER1_ID = 29;
    public static final int INDEXER_CELLHOLDER2_ID = -99999;
    public static final int INDEXER_CONVEYER_ID = 15;
    public static final int INDEXER_CELLGATE_ID = 3;

    //Intake mapping
    public static final int INTAKE_INTAKEMOTOR_ID = 16;
    public static final int INTAKE_INTAKESOLENOID_ID = 0;

    //ControlPanelManip mapping
    public static final int CONTROLPANELMANIP_MOTOR_ID = 14;
    public static final int CONTROLPANELMANIP_TRENCH_HARDSTOP_SOLENOID_ID = 4;

    //Vision mapping
    public static final int VISION_LIMELIGHT_ID = 31;

    //Driver Interface Components
    public static final int LEFT_JOYSTICK_PORT_ID = 0;
    public static final int RIGHT_JOYSTICK_PORT_ID = 1;

    //Operator Interface (buttons)
    public static final int JOYSTICKBUTTON_INCREASE_MOTOR_SPEED = 1;
    public static final int JOYSTICKBUTTON_DECREASE_MOTOR_SPEED = 2;
    public static final int JOYSTICKBUTTON_MAINTAIN_RPM = 0;
}
