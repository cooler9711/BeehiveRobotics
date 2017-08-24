package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by BeehiveRobotics-3648 on 8/22/2017.
 */
    @TeleOp(name = "balllaunchtest" , group = "linear OpMode")
    public class ballThrowerTest extends OpMode {

    DcMotor ballThrowerMotor1;
    DcMotor ballThrowerMotor2;

    @Override
    public void init() {
        ballThrowerMotor1 = hardwareMap.dcMotor.get("m5");
        ballThrowerMotor2 = hardwareMap.dcMotor.get("m6");
        ballThrowerMotor2.setDirection(DcMotor.Direction.REVERSE);


    }
        @Override
        public void loop() {
            double power = gamepad1.left_stick_y;
            ballThrowerMotor1.setPower(power);
            ballThrowerMotor2.setPower(power);

            telemetry.addData("current power: ", power);
            telemetry.update();
        }
    }


