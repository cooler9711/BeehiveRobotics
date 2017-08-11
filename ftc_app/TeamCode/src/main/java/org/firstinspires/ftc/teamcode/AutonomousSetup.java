package org.firstinspires.ftc.teamcode;
//All imports go here, anything you will use, like motors or servos.

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import android.util.Log;
//This makes the OpMode available in the Autonomous group under the name 'Autonomous', in the Driver Station
@Autonomous(name = "Autonomous", group = "Autonomous")

//This is the basic class
public class AutonomousSetup extends LinearOpMode {
    //Declare all of your motors, servos, sensors, etc.
    DcMotor Motor;

    // Now declare any universal value you will need more then once, like encoder CPR(Clicks per rotation)
    int CPR = 1120; //Rough estimate of the encoders CPR
    int Tm = 16; // Number of teeth in the gear attatched to the motor
    int Tw = 16; //Number of teeth in the gear attatched to the wheels
    double D = 2.5; //Diameter of wheels
    double C = D * Math.PI;//One rotation of tank gear/wheel
    public void runOpMode() throws InterruptedException {
        //Start with the basic declaration of variable strings that the phones will read
        Motor = hardwareMap.dcMotor.get("m1");

        // Now do anything else you need to do in the initilazation phase, like calibrating the gyros, setting a color sensors lights off, etc.
        Motor.setDirection(DcMotor.Direction.FORWARD);
        telemetry.addData("Anything you need to know before starting", 1);
        telemetry.update();

        // This line just says that anything after this point runs after you hit start, which is kind of important to make sure the robot doesn't run during the initilization phase
        waitForStart();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //The Best Way to run autonomous is to make several functions outside this box, and just run those functions here, with different values to change how far to go or for how long
        inches(1, 0);


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    //Everything Past this point should ONLY be functions. It is outside the run function, so it can only be called as a function.

    void inches(double ForwardInches, double power) {
        boolean isNegative = false;
        if (ForwardInches < 0) {
            isNegative = true;
        }
        double start, now, goal;
        int distancePerClicks = (int) (((Tw / Tm) * (ForwardInches * CPR) / C)) / 2;


        telemetry.addData("inches", "inches  " + ForwardInches);
        telemetry.addData("inches", "inches  " + distancePerClicks);
        start = encodervalue();
        now = start;
        goal = start + distancePerClicks;
        if (isNegative == true) {
            forward(-power);
        } else {
            forward(power);
        }
        telemetry.update();
        while (now < goal) {


            now = encodervalue();
        }


        stopmoving();

    }

    void forward(double power) {
        Motor.setPower(power);
    }

    void stopmoving() {
        Motor.setPower(0.0);
    }

    int encodervalue() {
        int m1;
        m1 = Motor.getCurrentPosition();
        telemetry.addData("Encoder", m1);
        telemetry.update();
        return (m1) / 1;

    }
}