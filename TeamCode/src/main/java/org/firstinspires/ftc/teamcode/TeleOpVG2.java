package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.*;

//the one that controls like a racing game
@TeleOp(name = "TeleOpVG", group = "linear OpMode")
public class TeleOpVG2 extends OpMode {

    DcMotor FrontLeft;
    DcMotor RearLeft;
    DcMotor FrontRight;
    DcMotor RearRight;

    @Override


    public void init() {
        FrontLeft = hardwareMap.dcMotor.get("m1");
        FrontRight = hardwareMap.dcMotor.get("m2");
        RearLeft = hardwareMap.dcMotor.get("m3");
        RearRight = hardwareMap.dcMotor.get("m4");
        reverseMotor(FrontRight);
        reverseMotor(RearRight);
    }

    @Override
    public void loop() {
        float speed = 0;
        float steering = 0;
        float middle = 0;
        if (gamepad1.right_trigger > gamepad1.left_trigger) {
            speed = gamepad1.right_trigger;
            steering = gamepad1.left_stick_x * speed;
            middle = speed - Math.abs(steering);
        }
        else {
            speed = -gamepad1.left_trigger;
            steering = -gamepad1.left_stick_x * speed;
            middle = speed + Math.abs(steering);
        }

        float right = middle - steering;
        float left = middle + steering;

        FrontRight.setPower(right);
        RearRight.setPower(right);
        FrontLeft.setPower(left);
        RearLeft.setPower(left);
    }

    public void reverseMotor(DcMotor motor) {

        motor.setDirection(DcMotor.Direction.REVERSE);
    }
// TODO: Test on robots to make sure it turns both directions and also goes backwards
}