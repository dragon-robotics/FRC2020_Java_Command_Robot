/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain_Subsystem;
import frc.robot.subsystems.Limelight_Subsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Align_Profiled_PID extends ProfiledPIDCommand {
  /**
   * Creates a new Align_Profiled_PID.
   * @param DriveTrain_Subsystem
   */
  public Align_Profiled_PID(DriveTrain_Subsystem drive, Limelight_Subsystem limelight) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(
            // The PID gains
            Constants.Limelight.kP, Constants.Limelight.kI, Constants.Limelight.kD,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(Constants.Limelight.kS, Constants.Limelight.kA)),
        // This should return the measurement
        () -> limelight.Get_X(),
        // This should return the goal (can also be a constant)
        () -> Constants.Limelight.setGoal,
        // This uses the output
        (output, setpoint) -> drive.arcadeDrive(0, -output),
          // Use the output (and setpoint, if desired) here
        drive);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements();
    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(Constants.Limelight.setPositionTolerance, Constants.Limelight.setVelocityTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atGoal();
  }
}
