package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.*;


//peter's d-pad controlling program
@TeleOp(name = "TeleOpVG", group = "linear OpMode")
public class TeleOpPeterDP extends OpMode {

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
        reverseMotor(FrontLeft);
        reverseMotor(RearLeft);
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

        FrontRight.setPower(right);
        RearRight.setPower(right);
        FrontLeft.setPower(left);
        RearLeft.setPower(left);
    }

    public void reverseMotor(DcMotor motor) {

        motor.setDirection(DcMotor.Direction.REVERSE);
    }
// TODO: Test on robots
}