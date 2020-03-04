/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber_Subsystem extends SubsystemBase {
  
    private final TalonSRX winch_left = new TalonSRX(Constants.TALONSRX_WINCH_LEFT);
    private final TalonSRX winch_right = new TalonSRX(Constants.TALONSRX_WINCH_RIGHT);
    private final TalonSRX hook_Deployer_left = new TalonSRX(Constants.TALONSRX_HOOK_DEPLOYER_LEFT);
    private final TalonSRX hook_Deployer_right = new TalonSRX(Constants.TALONSRX_HOOK_DEPLOYER_RIGHT);

    /**
     * Creates a new Climber_Subsystem.
     */
    public Climber_Subsystem() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    public void Move_Hook(double motor_speed) {
        hook_Deployer_left.set(ControlMode.PercentOutput, -motor_speed);
        hook_Deployer_right.set(ControlMode.PercentOutput, motor_speed);

    }
    public void Move_Winch(double motor_speed) {
        winch_left.set(ControlMode.PercentOutput, -motor_speed);
        winch_right.set(ControlMode.PercentOutput, motor_speed);
    }
    public void Move_Climbers(double hook_motors, double winch_motors) {
        Move_Hook(hook_motors);
        Move_Winch(winch_motors);
    }
    public void Climb_Up(){
        winch_left.set(ControlMode.PercentOutput, 1.0);

    }

    public void Climb_Down(){
        winch_left.set(ControlMode.PercentOutput, -1.0);
    }

    public void Stop_Climb(){
        winch_left.set(ControlMode.PercentOutput, 0.0);
        winch_right.set(ControlMode.PercentOutput, 0.0);
    }
}
