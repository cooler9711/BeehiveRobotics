package org.firstinspires.ftc.teamcode;
//All imports go here, anything you will use, like motors or servos.

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import android.util.Log;
//This makes the OpMode available in the Autonomous group under the name 'Autonomous', in the Driver Station
@Autonomous(name = "Autonomous", group = "Autonomous")

//This is the basic class
public class AutonomousSetup extends LinearOpMode {
    //Declare all of your motors, servos, sensors, etc.
    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor BackLeftMotor;
    DcMotor BackRightMotor;

    // Now declare any universal value you will need more then once, like encoder CPR(Clicks per rotation)
    int CPR = 1120; //Encoder CPR
    int Tm = 20; // The part of the gear ratio attatched to the motor
    int Tw = 1; //The part of the gear ratio attatched to the wheel
    double D = 2.5; //Diameter of wheels
    double C = D * Math.PI;//One rotation of tank gear/wheel
    public void runOpMode() throws InterruptedException {
        //Start with the basic declaration of variable strings that the phones will read
        FrontLeftMotor = hardwareMap.dcMotor.get("m1");
        FrontRightMotor = hardwareMap.dcMotor.get("m2");
        BackLeftMotor = hardwareMap.dcMotor.get("m3");
        BackRightMotor = hardwareMap.dcMotor.get("m4");

        // Now do anything else you need to do in the initilazation phase, like calibrating the gyros, setting a color sensors lights off, etc.
        FrontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        BackRightMotor.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Anything you need to know before starting", 1);
        telemetry.update();

        // This line just says that anything after this point runs after you hit start, which is kind of important to make sure the robot doesn't run during the initilization phase
        waitForStart();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //The Best Way to run autonomous is to make several functions outside this box, and just run those functions here, with different values to change how far to go or for how long
        rotations(3, .5);


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    //Everything Past this point should ONLY be functions. It is outside the run function, so it can only be called as a function.

    void rotations(double numberOfRotations, double power) {
        boolean isNegative = false;
        if (numberOfRotations < 0) {
            isNegative = true;
        }
        double start, now, goal;
        double distancePerClicks = numberOfRotations * CPR;


        telemetry.addData("Total number of rotations: ", + numberOfRotations);
        telemetry.addData("Total number of clicks: ", + distancePerClicks);
        start = encodervalue();
        now = start;
        goal = start + distancePerClicks;
        if (isNegative == true) {
            forward(-power);
        } else {
            forward(power);
        }

        while (now < goal) {
            telemetry.addData("Current clicks: ", now);
            now = encodervalue();
            telemetry.update();
        }

        stopmoving();

    }

    void forward(double power) {

        FrontLeftMotor.setPower(power);
        FrontRightMotor.setPower(power);
        BackLeftMotor.setPower(power);
        BackRightMotor.setPower(power);
    }

    void stopmoving() {

        FrontLeftMotor.setPower(0.0);
        FrontRightMotor.setPower(0.0);
        BackLeftMotor.setPower(0.0);
        BackRightMotor.setPower(0.0);
    }

    int encodervalue() {
        int m1;
        m1 = FrontLeftMotor.getCurrentPosition();
        int m2;
        m2 = FrontRightMotor.getCurrentPosition();
        int m3;
        m3 = BackLeftMotor.getCurrentPosition();
        int m4;
        m4 = BackRightMotor.getCurrentPosition();
        telemetry.addData("FLM: ", m1);
        telemetry.addData("FRM: ", m2);
        telemetry.addData("BLM: ", m3);
        telemetry.addData("BRM:", m4);
        telemetry.addData("Encoder average", (m1 + m2 + m3 + m4) / 4);
        telemetry.update();
        return (m1 + m2 + m3 + m4) / 4;

    }
}