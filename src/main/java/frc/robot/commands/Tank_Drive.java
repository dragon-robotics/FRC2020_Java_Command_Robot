/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain_Subsystem;

public class Tank_Drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain_Subsystem m_subsystem;

  /**
   * Creates a new Tank_Drive.
   */
  public Tank_Drive(DriveTrain_Subsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Check if LB has been pressed //
    boolean LB_Pressed = RobotContainer.j_stick_driver_LB.get();

    // Grab left stick value for Y //
    double leftJoyY = RobotContainer.j_stick_driver.getRawAxis(Constants.AXIS_LEFT_Y);

    // Grab right stick value for X //
    double rightJoyY = RobotContainer.j_stick_driver.getRawAxis(Constants.AXIS_RIGHT_Y);

    leftJoyY = LB_Pressed ? leftJoyY / 2 : leftJoyY;    // Apply Y
    rightJoyY = LB_Pressed ? rightJoyY / 2 : rightJoyY; // Apply X

    m_subsystem.tankDrive(leftJoyY, rightJoyY); // Run the subsystem method
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
