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
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class NavXIMU_Subsystem extends SubsystemBase {
  private AHRS ahrs;
  private String tabTitle;
  private ShuffleboardTab navXTab;
  
  /**
   * Creates a new NavXIMU_Subsystem.
   */
  public NavXIMU_Subsystem() { 
    try{
      ahrs = new AHRS(SerialPort.Port.kUSB1);
      ahrs.enableLogging(true);
      ahrs.zeroYaw();

      tabTitle = "NavXTab";
      navXTab = Shuffleboard.getTab(tabTitle);

      DisplayNavXData();
    }
    catch (RuntimeException ex ){
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private void DisplayNavXData(){
    /* Display 6-axis Processed Angle Data                                      */
    navXTab.add("IMU_Connected", ahrs.isConnected());
    navXTab.add("IMU_IsCalibrating",    ahrs.isCalibrating());
    navXTab.add("IMU_Yaw",              ahrs.getYaw());
    navXTab.add("IMU_Pitch",            ahrs.getPitch());
    navXTab.add("IMU_Roll",             ahrs.getRoll());
    
    /* Display tilt-corrected, Magnetometer-based heading (requires             */
    /* magnetometer calibration to be useful)                                   */
    
    navXTab.add("IMU_CompassHeading",   ahrs.getCompassHeading());
    
    /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
    navXTab.add("IMU_FusedHeading",     ahrs.getFusedHeading());

    /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
    /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */
    
    navXTab.add("IMU_TotalYaw",         ahrs.getAngle());
    navXTab.add("IMU_YawRateDPS",       ahrs.getRate());

    /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
    
    navXTab.add("IMU_Accel_X",          ahrs.getWorldLinearAccelX());
    navXTab.add("IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
    navXTab.add("IMU_IsMoving",         ahrs.isMoving());
    navXTab.add("IMU_IsRotating",       ahrs.isRotating());

    /* Display estimates of velocity/displacement.  Note that these values are  */
    /* not expected to be accurate enough for estimating robot position on a    */
    /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
    /* of these errors due to single (velocity) integration and especially      */
    /* double (displacement) integration.                                       */
    
    navXTab.add("Velocity_X",           ahrs.getVelocityX());
    navXTab.add("Velocity_Y",           ahrs.getVelocityY());
    navXTab.add("Displacement_X",       ahrs.getDisplacementX());
    navXTab.add("Displacement_Y",       ahrs.getDisplacementY());
    
    /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
    /* NOTE:  These values are not normally necessary, but are made available   */
    /* for advanced users.  Before using this data, please consider whether     */
    /* the processed data (see above) will suit your needs.                     */
    
    navXTab.add("RawGyro_X",            ahrs.getRawGyroX());
    navXTab.add("RawGyro_Y",            ahrs.getRawGyroY());
    navXTab.add("RawGyro_Z",            ahrs.getRawGyroZ());
    navXTab.add("RawAccel_X",           ahrs.getRawAccelX());
    navXTab.add("RawAccel_Y",           ahrs.getRawAccelY());
    navXTab.add("RawAccel_Z",           ahrs.getRawAccelZ());
    navXTab.add("RawMag_X",             ahrs.getRawMagX());
    navXTab.add("RawMag_Y",             ahrs.getRawMagY());
    navXTab.add("RawMag_Z",             ahrs.getRawMagZ());
    navXTab.add("IMU_Temp_C",           ahrs.getTempC());
    navXTab.add("IMU_Timestamp",        ahrs.getLastSensorTimestamp());
    
    /* Omnimount Yaw Axis Information                                           */
    /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
    AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
    navXTab.add("YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
    navXTab.add("YawAxis",              yaw_axis.board_axis.getValue() );
    
    /* Sensor Board Information                                                 */
    navXTab.add("FirmwareVersion",      ahrs.getFirmwareVersion());
    
    /* Quaternion Data                                                          */
    /* Quaternions are fascinating, and are the most compact representation of  */
    /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
    /* from the Quaternions.  If interested in motion processing, knowledge of  */
    /* Quaternions is highly recommended.                                       */
    navXTab.add("QuaternionW",          ahrs.getQuaternionW());
    navXTab.add("QuaternionX",          ahrs.getQuaternionX());
    navXTab.add("QuaternionY",          ahrs.getQuaternionY());
    navXTab.add("QuaternionZ",          ahrs.getQuaternionZ());
    
    /* Connectivity Debugging Support                                           */
    navXTab.add("IMU_Byte_Count",       ahrs.getByteCount());
    navXTab.add("IMU_Update_Count",     ahrs.getUpdateCount());
  }
}
