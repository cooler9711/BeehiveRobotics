package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.*;

@TeleOp(name = "TeleOpVG", group = "linear OpMode")
public class TeleOpVG2 extends OpMode {

    DcMotor Left;
    DcMotor Left2;
    DcMotor Right;
    DcMotor Right2;

    @Override


    public void init() {
        Left = hardwareMap.dcMotor.get("m1");
        Right = hardwareMap.dcMotor.get("m2");
        Left2 = hardwareMap.dcMotor.get("m3");
        Right2 = hardwareMap.dcMotor.get("m4");
        reverseMotor(Left);
        reverseMotor(Left2);
    }

    @Override
    public void loop() {
        float speed = gamepad1.right_trigger;
        float steering = gamepad1.left_stick_x;
        float middle = speed - Math.abs(steering);
        float right = 0;
        float left = 0;

        if (steering > 0) {
            right = middle - steering;
            left = middle + steering;
        }
        else if (steering < 0) {
            right = middle + steering;
            left = middle - steering;
        }

        else {
            right = speed;
            left = speed;
        }

        Right.setPower(right);
        Right2.setPower(right);
        Left.setPower(left);
        Left2.setPower(left);
    }

    public void reverseMotor(DcMotor motor) {

        motor.setDirection(DcMotor.Direction.REVERSE);
    }
// TODO: Test on robots
}