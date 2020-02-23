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
  
    private final TalonSRX winchLeft = new TalonSRX(Constants.TALONSRX_WINCH_LEFT);
    private final TalonSRX winchRight = new TalonSRX(Constants.TALONSRX_WINCH_RIGHT);

    private final TalonSRX hook = new TalonSRX(Constants.TALONSRX_HOOK);

    /**
     * Creates a new Climber_Subsystem.
     */
    public Climber_Subsystem() {
        // Set left winch to follow the right winch //
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void Move_Hook(double motor_speed) {
        winchRight.set(ControlMode.PercentOutput, motor_speed);
    }
    
    public void Climb_Up(){
        winchLeft.set(ControlMode.PercentOutput, 1.0);
    }

    public void Climb_Down(){
        winchLeft.set(ControlMode.PercentOutput, -1.0);
    }

    public void Stop_Climb(){
        winchLeft.set(ControlMode.PercentOutput, 0.0);
       winchRight.set(ControlMode.PercentOutput, 0.0);
    }
}
