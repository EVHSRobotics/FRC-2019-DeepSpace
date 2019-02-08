package org.usfirst.frc.team2854.robot.commands;

import org.usfirst.frc.team2854.robot.OI;
import org.usfirst.frc.team2854.robot.Robot;
import org.usfirst.frc.team2854.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2854.robot.subsystems.SubsystemNames;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class JoystickCommand extends Command {

	DriveTrain drive;
	double throttle;

	public JoystickCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		drive = (DriveTrain) (Robot.getSubsystem(SubsystemNames.DRIVE_TRAIN));

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		double turn = -1 * OI.joystick2.getRawAxis(0);
		turn = Math.abs(turn) < .05 ? 0 : turn;
		System.out.println("turn: " + turn);
		

		//throttle = OI.joystick3.getRawAxis(2) - OI.joystick3.getRawAxis(3);
		throttle = -1 * OI.joystick.getRawAxis(1);
		throttle = Math.abs(throttle) < .1 ? 0 : throttle;
		
		drive.drive(ControlMode.PercentOutput, sig(throttle - cubeRoot(turn)), sig(throttle + cubeRoot(turn)));
		
		SmartDashboard.putNumber("left throttle", sig(throttle - cubeRoot(turn)));
		SmartDashboard.putNumber("right throttle", sig(throttle + cubeRoot(turn)));

	}
	

	public double cubeRoot(double val) {
		if (val >= 0) {
			return Math.pow(val, 3 / 2d);
		} else {
			return -Math.pow(-val, 3 / 2d);
		}
	}

	public double sig(double val) {
		return 2 / (1 + Math.pow(Math.E, -7 * Math.pow(val, 3))) - 1;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		drive.drive(ControlMode.PercentOutput, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
