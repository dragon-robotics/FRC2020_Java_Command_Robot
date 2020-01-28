/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class NavXIMU_Subsystem extends SubsystemBase {
  private AHRS ahrs;
  private String tabTitle;
  private ShuffleboardTab navXTab;

  /* NetworkTableEntry for each IMU data value */
  private NetworkTableEntry imuConnected;
  private NetworkTableEntry imuIsCalibrating;
  private NetworkTableEntry imuYaw;
  private NetworkTableEntry imuPitch;
  private NetworkTableEntry imuRoll;

  private NetworkTableEntry imuCompassHeader;
  private NetworkTableEntry imuFusedHeading;
  
  private NetworkTableEntry imuTotalYaw;
  private NetworkTableEntry imuYawRateDPS;
  
  private NetworkTableEntry imuAccelX;
  private NetworkTableEntry imuAccelY;
  private NetworkTableEntry imuIsMoving;
  private NetworkTableEntry imuIsRotating;
  
  private NetworkTableEntry velocityX;
  private NetworkTableEntry velocityY;
  private NetworkTableEntry displacementX;
  private NetworkTableEntry displacementY;
  
  private NetworkTableEntry rawGyroX;
  private NetworkTableEntry rawGyroY;
  private NetworkTableEntry rawGyroZ;
  private NetworkTableEntry rawAccelX;
  private NetworkTableEntry rawAccelY;
  private NetworkTableEntry rawAccelZ;
  private NetworkTableEntry rawMagX;
  private NetworkTableEntry rawMagY;
  private NetworkTableEntry rawMagZ;
  private NetworkTableEntry imuTempC;
  private NetworkTableEntry imuTimestamp;
  
  private NetworkTableEntry yawAxisDirection;
  private NetworkTableEntry yawAxis;
  
  private NetworkTableEntry firmwareVersion;
  
  private NetworkTableEntry quarternionW;
  private NetworkTableEntry quarternionX;
  private NetworkTableEntry quarternionY;
  private NetworkTableEntry quarternionZ;
  
  private NetworkTableEntry imuByteCount;
  private NetworkTableEntry imuUpdateCount;

  /**
   * Creates a new NavXIMU_Subsystem.
   */
  public NavXIMU_Subsystem() { 
    try{
      ahrs = new AHRS(SPI.Port.kMXP);
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
    UpdateNavXData();
  }

  private void DisplayNavXData(){
    /* Display 6-axis Processed Angle Data                                      */
    imuConnected = navXTab.add("IMU_Connected",      ahrs.isConnected()).getEntry();
    imuIsCalibrating = navXTab.add("IMU_IsCalibrating",  ahrs.isCalibrating()).getEntry();
    imuYaw = navXTab.add("IMU_Yaw",            ahrs.getYaw()).getEntry();
    imuPitch = navXTab.add("IMU_Pitch",          ahrs.getPitch()).getEntry();
    imuRoll = navXTab.add("IMU_Roll",           ahrs.getRoll()).getEntry();
    
    /* Display tilt-corrected, Magnetometer-based heading (requires             */
    /* magnetometer calibration to be useful)                                   */
    
    imuCompassHeader = navXTab.add("IMU_CompassHeading",   ahrs.getCompassHeading()).getEntry();
    
    /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
    imuFusedHeading = navXTab.add("IMU_FusedHeading",     ahrs.getFusedHeading()).getEntry();

    /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
    /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */
    
    imuTotalYaw = navXTab.add("IMU_TotalYaw",         ahrs.getAngle()).getEntry();
    imuYawRateDPS = navXTab.add("IMU_YawRateDPS",       ahrs.getRate()).getEntry();

    /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
    
    imuAccelX = navXTab.add("IMU_Accel_X",          ahrs.getWorldLinearAccelX()).getEntry();
    imuAccelY = navXTab.add("IMU_Accel_Y",          ahrs.getWorldLinearAccelY()).getEntry();
    imuIsMoving = navXTab.add("IMU_IsMoving",         ahrs.isMoving()).getEntry();
    imuIsRotating = navXTab.add("IMU_IsRotating",       ahrs.isRotating()).getEntry();

    /* Display estimates of velocity/displacement.  Note that these values are  */
    /* not expected to be accurate enough for estimating robot position on a    */
    /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
    /* of these errors due to single (velocity) integration and especially      */
    /* double (displacement) integration.                                       */
    
    velocityX = navXTab.add("Velocity_X",           ahrs.getVelocityX()).getEntry();
    velocityY = navXTab.add("Velocity_Y",           ahrs.getVelocityY()).getEntry();
    displacementX = navXTab.add("Displacement_X",       ahrs.getDisplacementX()).getEntry();
    displacementY = navXTab.add("Displacement_Y",       ahrs.getDisplacementY()).getEntry();
    
    /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
    /* NOTE:  These values are not normally necessary, but are made available   */
    /* for advanced users.  Before using this data, please consider whether     */
    /* the processed data (see above) will suit your needs.                     */
    
    rawGyroX = navXTab.add("RawGyro_X",            ahrs.getRawGyroX()).getEntry();
    rawGyroY = navXTab.add("RawGyro_Y",            ahrs.getRawGyroY()).getEntry();
    rawGyroZ = navXTab.add("RawGyro_Z",            ahrs.getRawGyroZ()).getEntry();
    rawAccelX = navXTab.add("RawAccel_X",           ahrs.getRawAccelX()).getEntry();
    rawAccelY = navXTab.add("RawAccel_Y",           ahrs.getRawAccelY()).getEntry();
    rawAccelZ = navXTab.add("RawAccel_Z",           ahrs.getRawAccelZ()).getEntry();
    rawMagX = navXTab.add("RawMag_X",             ahrs.getRawMagX()).getEntry();
    rawMagY = navXTab.add("RawMag_Y",             ahrs.getRawMagY()).getEntry();
    rawMagZ = navXTab.add("RawMag_Z",             ahrs.getRawMagZ()).getEntry();
    imuTempC = navXTab.add("IMU_Temp_C",           ahrs.getTempC()).getEntry();
    imuTimestamp = navXTab.add("IMU_Timestamp",        ahrs.getLastSensorTimestamp()).getEntry();
    
    /* Omnimount Yaw Axis Information                                           */
    /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
    AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
    yawAxisDirection = navXTab.add("YawAxisDirection",     yaw_axis.up ? "Up" : "Down" ).getEntry();
    yawAxis = navXTab.add("YawAxis",              yaw_axis.board_axis.getValue() ).getEntry();
    
    /* Sensor Board Information                                                 */
    firmwareVersion = navXTab.add("FirmwareVersion",      ahrs.getFirmwareVersion()).getEntry();
    
    /* Quaternion Data                                                          */
    /* Quaternions are fascinating, and are the most compact representation of  */
    /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
    /* from the Quaternions.  If interested in motion processing, knowledge of  */
    /* Quaternions is highly recommended.                                       */
    quarternionW = navXTab.add("QuaternionW",          ahrs.getQuaternionW()).getEntry();
    quarternionX = navXTab.add("QuaternionX",          ahrs.getQuaternionX()).getEntry();
    quarternionY = navXTab.add("QuaternionY",          ahrs.getQuaternionY()).getEntry();
    quarternionZ = navXTab.add("QuaternionZ",          ahrs.getQuaternionZ()).getEntry();
    
    /* Connectivity Debugging Support                                           */
    imuByteCount = navXTab.add("IMU_Byte_Count",       ahrs.getByteCount()).getEntry();
    imuUpdateCount = navXTab.add("IMU_Update_Count",     ahrs.getUpdateCount()).getEntry();
  }

  private void UpdateNavXData(){
    /* Display 6-axis Processed Angle Data                                      */
    imuConnected.setBoolean(ahrs.isConnected());
    imuIsCalibrating.setBoolean(ahrs.isCalibrating());
    imuYaw.setDouble(ahrs.getYaw());
    imuPitch.setDouble(ahrs.getPitch());
    imuRoll.setDouble(ahrs.getRoll());
    
    /* Display tilt-corrected, Magnetometer-based heading (requires             */
    /* magnetometer calibration to be useful)                                   */
    
    imuCompassHeader.setDouble(ahrs.getCompassHeading());
    
    /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
    imuFusedHeading.setDouble(ahrs.getFusedHeading());

    /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
    /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */
    
    imuTotalYaw.setDouble(ahrs.getAngle());
    imuYawRateDPS.setDouble(ahrs.getRate());

    /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
    
    imuAccelX.setDouble(ahrs.getWorldLinearAccelX());
    imuAccelY.setDouble(ahrs.getWorldLinearAccelY());
    imuIsMoving.setBoolean(ahrs.isMoving());
    imuIsRotating.setBoolean(ahrs.isRotating());

    /* Display estimates of velocity/displacement.  Note that these values are  */
    /* not expected to be accurate enough for estimating robot position on a    */
    /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
    /* of these errors due to single (velocity) integration and especially      */
    /* double (displacement) integration.                                       */
    
    velocityX.setDouble(ahrs.getVelocityX());
    velocityY.setDouble(ahrs.getVelocityY());
    displacementX.setDouble(ahrs.getDisplacementX());
    displacementY.setDouble(ahrs.getDisplacementY());
    
    /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
    /* NOTE:  These values are not normally necessary, but are made available   */
    /* for advanced users.  Before using this data, please consider whether     */
    /* the processed data (see above) will suit your needs.                     */
    
    rawGyroX.setDouble(ahrs.getRawGyroX());
    rawGyroY.setDouble(ahrs.getRawGyroY());
    rawGyroZ.setDouble(ahrs.getRawGyroZ());
    rawAccelX.setDouble(ahrs.getRawAccelX());
    rawAccelY.setDouble(ahrs.getRawAccelY());
    rawAccelZ.setDouble(ahrs.getRawAccelZ());
    rawMagX.setDouble(ahrs.getRawMagX());
    rawMagY.setDouble(ahrs.getRawMagY());
    rawMagZ.setDouble(ahrs.getRawMagZ());
    imuTempC.setDouble(ahrs.getTempC());
    imuTimestamp.setDouble(ahrs.getLastSensorTimestamp());
    
    /* Omnimount Yaw Axis Information                                           */
    /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
    AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
    yawAxisDirection.setString(yaw_axis.up ? "Up" : "Down");
    yawAxis.setDouble(yaw_axis.board_axis.getValue());
    
    /* Sensor Board Information                                                 */
    firmwareVersion.setString(ahrs.getFirmwareVersion());
    
    /* Quaternion Data                                                          */
    /* Quaternions are fascinating, and are the most compact representation of  */
    /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
    /* from the Quaternions.  If interested in motion processing, knowledge of  */
    /* Quaternions is highly recommended.                                       */
    quarternionW.setDouble(ahrs.getQuaternionW());
    quarternionX.setDouble(ahrs.getQuaternionX());
    quarternionY.setDouble(ahrs.getQuaternionY());
    quarternionZ.setDouble(ahrs.getQuaternionZ());
    
    /* Connectivity Debugging Support                                           */
    imuByteCount.setDouble(ahrs.getByteCount());
    imuUpdateCount.setDouble(ahrs.getUpdateCount());
  }
}
