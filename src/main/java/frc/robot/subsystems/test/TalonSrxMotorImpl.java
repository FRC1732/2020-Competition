/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.test;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class TalonSrxMotorImpl extends AbstractMotor<TalonSRX> implements Motor {

    public TalonSrxMotorImpl(String name, Integer canId, TalonSRX motorController) {
        super(name, canId, motorController);
    }

    @Override
    public void setPower(double power) {
        motorController.set(ControlMode.PercentOutput, power);
    }

    @Override
    protected String getMotorType() {
        return "Talon SRX";
    }
}
