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
  
    private final TalonSRX winch_1 = new TalonSRX(Constants.TALONSRX_WINCH);
    private final TalonSRX winch_2 = new TalonSRX(Constants.TALONSRX_HOOK);

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
        winch_2.set(ControlMode.PercentOutput, motor_speed);

    }
    public void Climb_Up(){
        winch_1.set(ControlMode.PercentOutput, 1.0);

    }

    public void Climb_Down(){
        winch_1.set(ControlMode.PercentOutput, -1.0);
    }

    public void Stop_Climb(){
        winch_1.set(ControlMode.PercentOutput, 0.0);
       // winch_2.set(ControlMode.PercentOutput, 0.0);
    }
}
