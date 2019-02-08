package org.usfirst.frc.team2854.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class PID {
	public static void updatePID(TalonSRX talon, double P, double I, double D, double F, double targetSpeed) {
		final int timeoutConstant = 10;
		final int PIDIndex = 0; //parameter slot for the constant
		final int error = 2;
		
		//set the P I D F gain values
		talon.config_kF(0,  F,  timeoutConstant);
		talon.config_kP(0,  P, timeoutConstant);
		talon.config_kI(0,  I,  timeoutConstant); 	
		talon.config_kD(0,  D,  timeoutConstant);
		
		talon.configMotionCruiseVelocity((int)targetSpeed, timeoutConstant); //set cruise velocity
		talon.configMotionAcceleration((int) targetSpeed, timeoutConstant);  //set acceleration
		talon.configAllowableClosedloopError(PIDIndex, error, timeoutConstant); //(PIDIndex, error, timeout) PID is disabled once the error is within set value
	}
	
	/* configure the voltage output of the talon*/
	public static void configTalon(TalonSRX talon, boolean side) {

		final int timeOutConstant = 10;
		final int PIDIndex = 0;
		
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PIDIndex, timeOutConstant); //choose the sensor
		talon.setSensorPhase(side); //keeps sensor and motor in same phase
		
		/*set peak and nominal outputs*/
		talon.configNominalOutputForward(0,  timeOutConstant);
		talon.configNominalOutputReverse(0, timeOutConstant);
		talon.configPeakOutputForward(1,  timeOutConstant);
		talon.configPeakOutputReverse(-1,  timeOutConstant);
		
		//set sensor position to 0
		talon.setSelectedSensorPosition(0,  PIDIndex, timeOutConstant);
		//scales full output voltage of battery to 12 volts
		talon.configVoltageCompSaturation(12.0, 10);
	}

}
