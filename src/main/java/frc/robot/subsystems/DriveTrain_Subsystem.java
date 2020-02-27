/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
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
    private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftFront, m_leftRear);

    /* Right Motors */
    private final WPI_TalonFX m_rightFront = new WPI_TalonFX(Constants.MOTOR_RIGHTFRONT);   // Right Front Motor using PWM Victor
    private final WPI_TalonFX m_rightRear = new WPI_TalonFX(Constants.MOTOR_RIGHTREAR);     // Right Rear Motor using PWM Victor
    private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightFront, m_rightRear);

    /* The robot's drive */
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    /* The left-side encoder */
    private final TalonFXSensorCollection m_leftEncoder = new TalonFXSensorCollection(m_leftFront);

    /* The right-side encoder */
    private final TalonFXSensorCollection m_rightEncoder = new TalonFXSensorCollection(m_rightFront);

    /* The gyro sensor */
    AHRS m_gyro = new AHRS(SPI.Port.kMXP);

    private final DifferentialDriveOdometry m_odometry;

    public DriveTrain_Subsystem() {
        // DriveTrain Subsystem Constructor //
        
        // Disable all motors //
        m_leftFront.set(ControlMode.PercentOutput, 0);
        m_leftRear.set(ControlMode.PercentOutput, 0);
        m_rightFront.set(ControlMode.PercentOutput, 0);
        m_rightRear.set(ControlMode.PercentOutput, 0);

        // Factory default configurations for all motors //
        m_leftFront.configFactoryDefault();
        m_leftRear.configFactoryDefault();
        m_rightFront.configFactoryDefault();
        m_rightRear.configFactoryDefault();

        // Set neutral mode to brake on all motors //
        m_leftFront.setNeutralMode(NeutralMode.Brake);
        m_leftRear.setNeutralMode(NeutralMode.Brake);
        m_rightFront.setNeutralMode(NeutralMode.Brake);
        m_rightRear.setNeutralMode(NeutralMode.Brake);

        // Reset encoders to 0 //
        resetEncoders();

        // Calibrate Gyro //
        m_gyro.calibrate();

        // Reset Gyro //
        m_gyro.zeroYaw();

        m_odometry = new DifferentialDriveOdometry(getHeading());
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        m_odometry.update(getHeading(), m_leftEncoder.getIntegratedSensorPosition(), m_rightEncoder.getIntegratedSensorPosition());
    }

    /**
     * Returns the current heading of the robot from the gyroscope
     * 
     * @return The heading in degrees
     */
    public Rotation2d getHeading(){
        return Rotation2d.fromDegrees(-m_gyro.getAngle());
    }

    /**
     * Returns the currently-estimated pose of the robot.
     * 
     * @return The pose
     */
    public Pose2d getPose(){
        return m_odometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot
     * 
     * @return The current wheel speed
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds(){
        double gearRatio = 10.71;
        double wheelCircumference = 2 * Math.PI * Units.inchesToMeters(3.0);
        return new DifferentialDriveWheelSpeeds(
            m_leftEncoder.getIntegratedSensorVelocity() / gearRatio * wheelCircumference / 60,
            m_rightEncoder.getIntegratedSensorVelocity() / gearRatio * wheelCircumference / 60
        );
    }

    /**
     * Resets the odometry to the specified pose
     * 
     * @param pose The pose to which to set the odometry
     */
    public void resetOdometry(Pose2d pose){
        resetEncoders();
        m_odometry.resetPosition(pose, getHeading());
    }

    /**
     * Resets the drive encoders to read a position of 0
     */
    public void resetEncoders(){
        m_leftEncoder.setIntegratedSensorPosition(0.0, 100);
        m_rightEncoder.setIntegratedSensorPosition(0.0, 100);
    }

    /**
     * Gets the average distance of the two encoder
     * 
     * @return the average of the two encoder readings
     */
    public double getAverageEncoderDistance(){
        return (m_leftEncoder.getIntegratedSensorPosition() + m_rightEncoder.getIntegratedSensorPosition()) / 2;
    }

    /**
     * Gets the left drive encoder
     * 
     * @return The left encoder
     */
    public TalonFXSensorCollection getLeftEncoder(){
        return m_leftEncoder;
    }

    /**
     * Gets the right drive encoder
     * 
     * @return The right encoder
     */
    public TalonFXSensorCollection getRightEncoder() {
        return m_rightEncoder;
    }

    /**
     * Sets the max output of the drive. Useful for scaling the drive to drive more slowly
     * 
     * @param maxOutput The max output to which the drive will be constrained
     */
    public void setMaxOutput(double maxOutput){
        m_robotDrive.setMaxOutput(maxOutput);
    }

    /**
     * Zeroes the heading of the robot
     */
    public void zeroHeading(){
        m_gyro.reset();
    }

    public double getTurnRate(){
        return m_gyro.getRate();
    }

    /**
     * Drives the robot directly with voltages 
     * @param leftVolt The voltage for the left side
     * @param rightVolt The voltage for the right side
     */
    public void tankDriveVolts(double leftVolt, double rightVolt){
        m_leftMotors.setVoltage(leftVolt);
        m_rightMotors.setVoltage(rightVolt);
        m_robotDrive.feed();
    }
    
    /**
     * Drives the robot using arcade drive controls
     * 
     * @param drive_speed The forward/backward drive speed
     * @param steer_speed The steering speed
     */
    public void arcadeDrive(double drive_speed, double steer_speed){
        // Read joystick input and translate into arcade drive //
        m_robotDrive.arcadeDrive(drive_speed, steer_speed);
    }

    /**
     * Drives the robot using tank drive controls
     * 
     * @param left_speed The left speed
     * @param right_speed The right speed
     */
    public void tankDrive(double left_speed, double right_speed){
        // Read joystick input and translate into tank drive //
        m_robotDrive.tankDrive(left_speed, right_speed);
    }
}
