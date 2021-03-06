/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.xml.namespace.QName;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public final class Limelight {
    public static final double kP = 0.08;
    public static final double kI = 0;
    public static final double kD = 0.03;
    public static final double kS = 1;
    public static final double kA = 1;
    public static final double setPoint = 0;
    public static final double setGoal = 0;
    public static final double setPercentTolerance = 0.01;
    public static final double setPositionTolerance = 0.015;
    public static final double setVelocityTolerance = 0.05;
  }

  public final class Shooter {
    public static final double kP = 0.05;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kS = 0;
    public static final double kA = 0;
    public static final double setPoint = 0;
  }
  public final class Drivetrain {

  }

  /* Left Motor */
  public static final int MOTOR_LEFTFRONT = 3;
  public static final int MOTOR_LEFTREAR = 1;

  /* Right Motor */
  public static final int MOTOR_RIGHTFRONT = 2;
  public static final int MOTOR_RIGHTREAR = 0;

  /* SparkMax */
  public static final int SPARKMAX_SHOOT_1 = 1;
  public static final int SPARKMAX_SHOOT_2 = 2;
  public static final int SPARKMAX_INDEXER_1 = 10;
  public static final int SPARKMAX_INDEXER_2 = 11;
  public static final int SPARKMAX_INDEXER_3 = 12;

  /* TalonSRX */
  public static final int TALONSRX_WINCH_LEFT = 1;
  public static final int TALONSRX_WINCH_RIGHT = 2;
  public static final int TALONSRX_INTAKE = 3;
  public static final int TALONSRX_INTAKE_2 = 4;
  public static final int TALONSRX_HOOK_DEPLOYER_LEFT = 5;
  public static final int TALONSRX_HOOK_DEPLOYER_RIGHT = 6;

  /* Double Solenoid */
  public static final int DOUBLESOLENOID_INTAKE_1 = 0;
  public static final int DOUBLESOLENOID_INTAKE_2 = 1;

  /* Joystick port */
  public static final int J_STICK_DRIVER = 0;
  public static final int J_STICK_CONTROL = 1;

  /* Joystick */
  public static final int BUTTON_RIGHT = 5; 
  public static final int BUTTON_LEFT = 6;
  public static final int BUTTON_X = 3; 
  public static final int BUTTON_Y = 4;
  public static final int BUTTON_A = 1; 
  public static final int BUTTON_B = 2;
  public static final int BUTTON_BACK = 7;
  public static final int BUTTON_START = 8;
  public static final int BUTTON_LEFTSTICK = 9;
  public static final int BUTTON_RIGHTSTICK = 10;

  public static final int AXIS_LEFT_X = 0;
  public static final int AXIS_LEFT_Y = 1;
  public static final int AXIS_LEFT_TRIGGER = 2;
  public static final int AXIS_RIGHT_TRIGGER = 3;
  public static final int AXIS_RIGHT_X = 4;
  public static final int AXIS_RIGHT_Y = 5;

  /* Pneumatics */
  public static final int CAN_COMPRESSOR = 0;
  public static final int CAN_DOUBLESOLENOID_1_LEFT = 0;
  public static final int CAN_DOUBLESOLENOID_1_RIGHT = 1;
  public static final int CAN_DOUBLESOLENOID_2_LEFT = 2;
  public static final int CAN_DOUBLESOLENOID_2_RIGHT = 3;
  
}

