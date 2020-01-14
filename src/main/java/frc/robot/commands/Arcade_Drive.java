/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain_Subsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class Arcade_Drive extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveTrain_Subsystem m_subsystem;

    /**
     * Creates a new Arcade_Drive.
     *
     * @param subsystem The subsystem used by this command.
     */
    public Arcade_Drive(DriveTrain_Subsystem subsystem) {
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
        double rightJoyX = RobotContainer.j_stick_driver.getRawAxis(Constants.AXIS_RIGHT_X);

        leftJoyY = LB_Pressed ? leftJoyY / 2 : leftJoyY;    // Apply Y
        rightJoyX = LB_Pressed ? rightJoyX / 2 : rightJoyX; // Apply X

        m_subsystem.arcadeDrive(leftJoyY, rightJoyX); // Run the subsystem method
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
