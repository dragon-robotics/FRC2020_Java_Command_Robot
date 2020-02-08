/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain_Subsystem extends SubsystemBase {
    /**
     * Creates a new DriveTrain_Subsystem.
     */

    /* Left Motors */
    private final TalonFX m_leftFront = new TalonFX(Constants.MOTOR_LEFTFRONT);     // Left Front Motor using Talon FX
    private final TalonFX m_leftRear = new TalonFX(Constants.MOTOR_LEFTREAR);       // Left Rear Motor using Talon FX

    /* Right Motors */
    private final TalonFX m_rightFront = new TalonFX(Constants.MOTOR_RIGHTFRONT);   // Right Front Motor using Talon FX
    private final TalonFX m_rightRear = new TalonFX(Constants.MOTOR_RIGHTREAR);     // Right Rear Motor using Talon FX

    AHRS gyro = new AHRS(SPI.Port.kMXP);

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(21.5));
    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

    public DriveTrain_Subsystem() {
        // DriveTrain Subsystem Constructor //
        m_leftRear.follow(m_leftFront);
        m_rightRear.follow(m_rightFront);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public Rotation2d getHeading(){
        return Rotation2d.fromDegrees(-gyro.getAngle());
    }

    public void arcadeDrive(double drive_speed, double steer_speed){
        // Read joystick input and translate into arcade drive //
        // m_robotDrive.arcadeDrive(drive_speed, steer_speed);
    }

    public void tankDrive(double left_speed, double right_speed){
        // Read joystick input and translate into tank drive //
        m_leftFront.set(ControlMode.PercentOutput, left_speed);
        m_rightFront.set(ControlMode.PercentOutput, right_speed);
    }
}
