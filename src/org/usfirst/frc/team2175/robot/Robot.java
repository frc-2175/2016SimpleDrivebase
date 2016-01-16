
package org.usfirst.frc.team2175.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;

    RobotDrive drivetrain;
    Joystick leftStick;
    Joystick rightStick;

    double moveValue;
    double turnValue;

    double speedScale;

    double deadbandValue;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);

        drivetrain = new RobotDrive(0, 1);
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);

        speedScale = 1;
        deadbandValue = .1;

    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        autoSelected = (String) chooser.getSelected();
        // autoSelected = SmartDashboard.getString("Auto Selector",
        // defaultAuto);
        System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        switch (autoSelected) {
        case customAuto:
            // Put custom auto code here
            break;
        case defaultAuto:
        default:
            // Put default auto code here
            break;
        }
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {

        while (isOperatorControl() && isEnabled()) {
            moveValue = deadbandInput(leftStick.getY(), deadbandValue) * speedScale;
            turnValue = deadbandInput(rightStick.getX(), deadbandValue) * speedScale;

            drivetrain.arcadeDrive(moveValue, turnValue);

        }
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {

    }

    public double deadbandInput(double input, double deadbandSize) {
        double output;

        if (isAboveThreshold(input, deadbandSize)) {
            double sign = Math.abs(input) / input;
            output = input * (1 / (1 - deadbandValue)) - (sign * deadbandValue);
        } else {
            output = 0;
        }

        return output;
    }

    public boolean isAboveThreshold(double input, double threshold) {
        return Math.abs(input) >= threshold;
    }
}
