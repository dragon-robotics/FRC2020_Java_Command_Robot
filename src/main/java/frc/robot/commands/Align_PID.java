/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain_Subsystem;
import frc.robot.subsystems.Limelight_Subsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Align_PID extends PIDCommand {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  /**
   * Creates a new Find_Target_PID.
   * @param DriveTrain_Subsystem
   */
  public Align_PID(DriveTrain_Subsystem DriveTrain_Subsystem, Limelight_Subsystem limelight_Subsystem) {
    super(
        // The controller that the command will use

        new PIDController(Constants.Limelight.kP , Constants.Limelight.kI, Constants.Limelight.kD),
        // This should return the measurement
        () -> limelight_Subsystem.Get_X(),
        // This should return the setpoint (can also be a constant)
        () -> Constants.Limelight.setPoint,
        // This uses the output
        output -> DriveTrain_Subsystem.arcadeDrive(0, -output),
          // Use the output here
        DriveTrain_Subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements();
    // Configure additional PID options by calling `getController` here.
    getController()
      .setTolerance(Constants.Limelight.setPercentTolerance);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
