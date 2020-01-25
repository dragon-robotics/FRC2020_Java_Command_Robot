/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class NavXIMU_Subsystem extends SubsystemBase {
  private AHRS ahrs;
  private String tabTitle;
  
  /**
   * Creates a new NavXIMU_Subsystem.
   */
  public NavXIMU_Subsystem() { 
    try{
      ahrs = new AHRS(SerialPort.Port.kUSB1);
      ahrs.enableLogging(true);
      ahrs.zeroYaw();

      tabTitle = "NavXTab";

      UpdateNavXData();
    }
    catch (RuntimeException ex ){
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private void UpdateNavXData(){
    /* Display 6-axis Processed Angle Data                                      */
    Shuffleboard.getTab(tabTitle).add("IMU_Connected", ahrs.isConnected());
    Shuffleboard.getTab(tabTitle).add(  "IMU_IsCalibrating",    ahrs.isCalibrating());
    Shuffleboard.getTab(tabTitle).add(   "IMU_Yaw",              ahrs.getYaw());
    Shuffleboard.getTab(tabTitle).add(   "IMU_Pitch",            ahrs.getPitch());
    Shuffleboard.getTab(tabTitle).add(   "IMU_Roll",             ahrs.getRoll());
    
    /* Display tilt-corrected, Magnetometer-based heading (requires             */
    /* magnetometer calibration to be useful)                                   */
    
    Shuffleboard.getTab(tabTitle).add(   "IMU_CompassHeading",   ahrs.getCompassHeading());
    
    /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
    Shuffleboard.getTab(tabTitle).add(   "IMU_FusedHeading",     ahrs.getFusedHeading());

    /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
    /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */
    
    Shuffleboard.getTab(tabTitle).add(   "IMU_TotalYaw",         ahrs.getAngle());
    Shuffleboard.getTab(tabTitle).add(   "IMU_YawRateDPS",       ahrs.getRate());

    /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
    
    Shuffleboard.getTab(tabTitle).add(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
    Shuffleboard.getTab(tabTitle).add(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
    Shuffleboard.getTab(tabTitle).add(  "IMU_IsMoving",         ahrs.isMoving());
    Shuffleboard.getTab(tabTitle).add(  "IMU_IsRotating",       ahrs.isRotating());

    /* Display estimates of velocity/displacement.  Note that these values are  */
    /* not expected to be accurate enough for estimating robot position on a    */
    /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
    /* of these errors due to single (velocity) integration and especially      */
    /* double (displacement) integration.                                       */
    
    Shuffleboard.getTab(tabTitle).add("Velocity_X",           ahrs.getVelocityX());
    Shuffleboard.getTab(tabTitle).add("Velocity_Y",           ahrs.getVelocityY());
    Shuffleboard.getTab(tabTitle).add("Displacement_X",       ahrs.getDisplacementX());
    Shuffleboard.getTab(tabTitle).add("Displacement_Y",       ahrs.getDisplacementY());
    
    /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
    /* NOTE:  These values are not normally necessary, but are made available   */
    /* for advanced users.  Before using this data, please consider whether     */
    /* the processed data (see above) will suit your needs.                     */
    
    Shuffleboard.getTab(tabTitle).add("RawGyro_X",            ahrs.getRawGyroX());
    Shuffleboard.getTab(tabTitle).add("RawGyro_Y",            ahrs.getRawGyroY());
    Shuffleboard.getTab(tabTitle).add("RawGyro_Z",            ahrs.getRawGyroZ());
    Shuffleboard.getTab(tabTitle).add("RawAccel_X",           ahrs.getRawAccelX());
    Shuffleboard.getTab(tabTitle).add("RawAccel_Y",           ahrs.getRawAccelY());
    Shuffleboard.getTab(tabTitle).add("RawAccel_Z",           ahrs.getRawAccelZ());
    Shuffleboard.getTab(tabTitle).add("RawMag_X",             ahrs.getRawMagX());
    Shuffleboard.getTab(tabTitle).add("RawMag_Y",             ahrs.getRawMagY());
    Shuffleboard.getTab(tabTitle).add("RawMag_Z",             ahrs.getRawMagZ());
    Shuffleboard.getTab(tabTitle).add("IMU_Temp_C",           ahrs.getTempC());
    Shuffleboard.getTab(tabTitle).add("IMU_Timestamp",        ahrs.getLastSensorTimestamp());
    
    /* Omnimount Yaw Axis Information                                           */
    /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
    AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
    Shuffleboard.getTab(tabTitle).add("YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
    Shuffleboard.getTab(tabTitle).add("YawAxis",              yaw_axis.board_axis.getValue() );
    
    /* Sensor Board Information                                                 */
    Shuffleboard.getTab(tabTitle).add("FirmwareVersion",      ahrs.getFirmwareVersion());
    
    /* Quaternion Data                                                          */
    /* Quaternions are fascinating, and are the most compact representation of  */
    /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
    /* from the Quaternions.  If interested in motion processing, knowledge of  */
    /* Quaternions is highly recommended.                                       */
    Shuffleboard.getTab(tabTitle).add("QuaternionW",          ahrs.getQuaternionW());
    Shuffleboard.getTab(tabTitle).add("QuaternionX",          ahrs.getQuaternionX());
    Shuffleboard.getTab(tabTitle).add("QuaternionY",          ahrs.getQuaternionY());
    Shuffleboard.getTab(tabTitle).add("QuaternionZ",          ahrs.getQuaternionZ());
    
    /* Connectivity Debugging Support                                           */
    Shuffleboard.getTab(tabTitle).add("IMU_Byte_Count",       ahrs.getByteCount());
    Shuffleboard.getTab(tabTitle).add("IMU_Update_Count",     ahrs.getUpdateCount());
  }
}
