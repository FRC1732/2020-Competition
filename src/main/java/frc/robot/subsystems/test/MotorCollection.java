/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.test;

import java.util.ArrayList;
import java.util.List;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class MotorCollection {
    List<Motor> motors = new ArrayList<>();
    
    public void createCollection() {
        // Drive train Motors
        motors.add(createCanSparkMax("Drivetrain Left Master", Constants.DRIVETRAIN_LEFTMASTER_ID));
        motors.add(createCanSparkMax("Drivetrain Left Follow 1", Constants.DRIVETRAIN_LEFT1_ID));
        motors.add(createCanSparkMax("Drivetrain Left Follow 2", Constants.DRIVETRAIN_LEFT2_ID));
        motors.add(createCanSparkMax("Drivetrain Right Master", Constants.DRIVETRAIN_RIGHTMASTER_ID));
        motors.add(createCanSparkMax("Drivetrain Right Follow 1", Constants.DRIVETRAIN_RIGHT1_ID));
        motors.add(createCanSparkMax("Drivetrain Right Follow 2", Constants.DRIVETRAIN_RIGHT2_ID));

        // Climber
        //climberRight = new TalonSRX(Constants.CLIMBER_RIGHT_ID);
        //climberLeft = new TalonSRX(Constants.CLIMBER_LEFT_ID);
    }

    public List<Motor> getMotors() {
        return motors;
    }

    private Motor createCanSparkMax(String name, int canId) {
        CANSparkMax canSparkMax = new CANSparkMax(canId, MotorType.kBrushless);
        canSparkMax.setInverted(false);
        canSparkMax.setIdleMode(IdleMode.kBrake);        
        Motor motor = new CanSparkMaxMotorImpl(name, canId, canSparkMax);
        return motor;
    }
}
