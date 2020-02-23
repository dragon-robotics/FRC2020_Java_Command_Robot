/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber_Subsystem extends SubsystemBase {
  
    private final WPI_TalonSRX winchLeft = new WPI_TalonSRX(Constants.TALONSRX_WINCH_LEFT);
    private final WPI_TalonSRX winchRight = new WPI_TalonSRX(Constants.TALONSRX_WINCH_RIGHT);
    
    private final WPI_TalonSRX hook = new WPI_TalonSRX(Constants.TALONSRX_HOOK);

    /**
     * Creates a new Climber_Subsystem.
     */
    public Climber_Subsystem() {
        // Set right winch to follow the left winch //
        winchRight.follow(winchLeft);
        winchRight.setInverted(InvertType.OpposeMaster);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void Move_Hook(double motor_speed) {
        hook.set(ControlMode.PercentOutput, motor_speed);
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
