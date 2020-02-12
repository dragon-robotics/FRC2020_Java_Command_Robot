/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain_Subsystem extends SubsystemBase {
    /**
     * Creates a new DriveTrain_Subsystem.
     */

    /* Left Motors */
    private final WPI_TalonFX m_leftFront = new WPI_TalonFX(Constants.MOTOR_LEFTFRONT);     // Left Front Motor using PWM Victor
    private final WPI_TalonFX m_leftRear = new WPI_TalonFX(Constants.MOTOR_LEFTREAR);       // Left Rear Motor using PWM Victor
    private final SpeedControllerGroup m_Left = new SpeedControllerGroup(m_leftFront, m_leftRear);

    /* Right Motors */
    private final WPI_TalonFX m_rightFront = new WPI_TalonFX(Constants.MOTOR_RIGHTFRONT);   // Right Front Motor using PWM Victor
    private final WPI_TalonFX m_rightRear = new WPI_TalonFX(Constants.MOTOR_RIGHTREAR);     // Right Rear Motor using PWM Victor
    private final SpeedControllerGroup m_Right = new SpeedControllerGroup(m_rightFront, m_rightRear);

    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_Left, m_Right);

    AHRS gyro = new AHRS(SPI.Port.kMXP);

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(21.5));
    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

    public DriveTrain_Subsystem() {
        // DriveTrain Subsystem Constructor //
        
        // Follow the motors //
        // m_leftRear.follow(m_leftFront, FollowerType.PercentOutput);
        // m_rightRear.follow(m_rightFront, FollowerType.PercentOutput);
        
        // Invert the left from the right motor //
        // m_rightFront.setInverted(true);
        // m_leftFront.setInverted(false);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        getHeading();
    }

    public Rotation2d getHeading(){
        return Rotation2d.fromDegrees(-gyro.getAngle());
    }

    public DifferentialDriveWheelSpeeds getSpeeds(){
        double gearRatio = 10.71;
        double wheelCircumference = 2 * Math.PI * Units.inchesToMeters(3.0);
        return new DifferentialDriveWheelSpeeds(
            m_leftFront.getSensorCollection().getIntegratedSensorVelocity() / gearRatio * wheelCircumference / 60,
            m_rightFront.getSensorCollection().getIntegratedSensorVelocity() / gearRatio * wheelCircumference / 60
        );
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
