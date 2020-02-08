/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain_Subsystem extends SubsystemBase {
    /**
     * Creates a new DriveTrain_Subsystem.
     */

    /* Left Motors */
    private final PWMVictorSPX m_leftFront = new PWMVictorSPX(Constants.MOTOR_LEFTFRONT);                         // Left Front Motor using PWM Victor
    private final PWMVictorSPX m_leftRear = new PWMVictorSPX(Constants.MOTOR_LEFTREAR);                          // Left Rear Motor using PWM Victor
    SpeedControllerGroup m_left = new SpeedControllerGroup(m_leftFront, m_leftRear);      // Left Front + Left Rear synchronized control

    /* Right Motors */
    private final PWMVictorSPX m_rightFront = new PWMVictorSPX(Constants.MOTOR_RIGHTFRONT);                        // Right Front Motor using PWM Victor
    private final PWMVictorSPX m_rightRear = new PWMVictorSPX(Constants.MOTOR_RIGHTREAR);                         // Right Rear Motor using PWM Victor
    SpeedControllerGroup m_right = new SpeedControllerGroup(m_rightFront, m_rightRear);   // Right Front + Right Rear synchronized control

    private DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);

    public DriveTrain_Subsystem() {
        // DriveTrain Subsystem Constructor //
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void arcadeDrive(double drive_speed, double steer_speed){
        // Read joystick input and translate into arcade drive //
        m_robotDrive.arcadeDrive(drive_speed, steer_speed);
    }

    public void tankDrive(double left_speed, double right_speed){
        // Read joystick input and translate into tank drive //
        m_robotDrive.tankDrive(left_speed, right_speed);
    }
}
