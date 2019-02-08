package org.usfirst.frc.team2854.robot.subsystems;


import org.usfirst.frc.team2854.robot.PID;
import org.usfirst.frc.team2854.robot.RobotMap;
import org.usfirst.frc.team2854.robot.commands.JoystickCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem{

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private TalonSRX leftTalon1;
	private TalonSRX leftTalon2;
	private TalonSRX leftTalon3;
	private TalonSRX rightTalon1;
	private TalonSRX rightTalon2;
	private TalonSRX rightTalon3;
	
	//private final AHRS ahrs;

		
	private static Value SLOW, FAST, UNKNOWN;
	private static Value gear;

	
	public DriveTrain() {
		leftTalon1 = new TalonSRX(RobotMap.leftTalon1);
		leftTalon2 = new TalonSRX(RobotMap.leftTalon2);
		leftTalon3 = new TalonSRX(RobotMap.leftTalon3);
		rightTalon1 = new TalonSRX(RobotMap.rightTalon1);
		rightTalon2 = new TalonSRX(RobotMap.rightTalon2);
		rightTalon3 = new TalonSRX(RobotMap.rightTalon3);
		
		
		leftTalon1.setInverted(true);
		leftTalon2.setInverted(true);
		leftTalon3.setInverted(true);
		
		leftTalon2.follow(leftTalon1);
		leftTalon3.follow(leftTalon1);
		//leftTalon2.set(ControlMode.Follower, leftTalon1.getDeviceID());
		//leftTalon3.set(ControlMode.Follower, leftTalon1.getDeviceID());
		
		rightTalon2.follow(rightTalon1);
		rightTalon3.follow(rightTalon1);
		
		
		leftTalon1.setSelectedSensorPosition(0, 0, 10); 
		rightTalon1.setSelectedSensorPosition(0, 0, 10);
		
		
		
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new JoystickCommand());
	}

	
	
	
	public void drive(ControlMode mode, double left, double right) {
		leftTalon1.set(mode, left);
		
		rightTalon1.set(mode, right);
		
		SmartDashboard.putNumber("left encoder", leftTalon1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("rightEncoder", rightTalon1.getSelectedSensorPosition(0));
	}

	

	
		
		
	
	
	
}
