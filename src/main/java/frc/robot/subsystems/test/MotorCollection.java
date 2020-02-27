/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.test;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
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
        motors.add(createCanSparkMax("Drivetrain Right Master", Constants.DRIVETRAIN_RIGHTMASTER_ID, true));
        motors.add(createCanSparkMax("Drivetrain Right Follow 1", Constants.DRIVETRAIN_RIGHT1_ID, true));
        motors.add(createCanSparkMax("Drivetrain Right Follow 2", Constants.DRIVETRAIN_RIGHT2_ID, true));

        // Climber
        motors.add(createTalonSrx("Climber Right Motor", Constants.CLIMBER_RIGHT_ID));
        motors.add(createTalonSrx("Climber Left Motor", Constants.CLIMBER_LEFT_ID));

        // Control Pannel
        //controlPanelTrench = new Solenoid(Constants.CONTROLPANELMANIP_TRENCH_SOLENOID_ID);
        motors.add(createVictorSpx("Control Panel Motor", Constants.CONTROLPANELMANIP_MOTOR_ID, true));

        // Indexer 
        motors.add(createTalonSrx("Feeder Motor", Constants.INDEXER_FEEDER_ID, true));
        motors.add(createVictorSpx("Conveyor Motor", Constants.INDEXER_CONVEYER_ID, true));

        // Shooter
        motors.add(createTalonSrx("Shooter Master Motor", Constants.SHOOTER_SHOOTER_MASTER_ID, false));
        motors.add(createVictorSpx("Shooter Follower Motor", Constants.SHOOTER_SHOOTER_FOLLOWER_ID, true));
        //shooterMaster.setSensorPhase(true);
        
    
    }
    
    private Motor createVictorSpx(String name, int canId, boolean isInverted) {
        VictorSPX victorSpx = new VictorSPX(canId);
        victorSpx.setInverted(isInverted);
        victorSpx.setNeutralMode(NeutralMode.Coast);
        Motor motor = new VictorSpxMotorImpl(name, canId, victorSpx);
        return motor;
    }

    private Motor createTalonSrx(String name, int canId) {
        return createTalonSrx(name, canId, false);
    }

    private Motor createTalonSrx(String name, int canId, boolean isInverted) {
        TalonSRX talonSrx = new TalonSRX(canId);
        talonSrx.setInverted(isInverted);
        talonSrx.setNeutralMode(NeutralMode.Coast);
        Motor motor = new TalonSrxMotorImpl(name, canId, talonSrx);
        return motor;
    }

    private Motor createCanSparkMax(String name, int canId) {
        return createCanSparkMax(name, canId, false);
    }

    private Motor createCanSparkMax(String name, int canId, boolean isInverted) {
        CANSparkMax canSparkMax = new CANSparkMax(canId, MotorType.kBrushless);
        canSparkMax.setInverted(isInverted);
        canSparkMax.setIdleMode(IdleMode.kBrake);        
        Motor motor = new CanSparkMaxMotorImpl(name, canId, canSparkMax);
        return motor;
    }

    public List<Motor> getMotors() {
        return motors;
    }

}
