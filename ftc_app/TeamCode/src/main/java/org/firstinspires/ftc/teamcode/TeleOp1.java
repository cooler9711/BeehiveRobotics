package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "2MotorTeleOp", group = "linear OpMode")
public class TeleOp1 extends OpMode {

    DcMotor Left;
    DcMotor Right;

    @Override


    public void init() {
        Left = hardwareMap.dcMotor.get("m1");
        Right = hardwareMap.dcMotor.get("m2");

        reverseMotor(Left);

    }

    @Override
    public void loop() {
        /*
        float right = gamepad1.right_stick_y;
        float left = gamepad1.left_stick_y;
        */
        Right.setPower(gamepad1.right_stick_y);
        Left.setPower(gamepad1.left_stick_y);
    }

    public void reverseMotor(DcMotor motor) {
        motor.setDirection(DcMotor.Direction.REVERSE);
    }
}