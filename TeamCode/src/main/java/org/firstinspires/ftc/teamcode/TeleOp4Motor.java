package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//teleop mode to use if you have 4 motors 

@TeleOp(name = "4MotorTeleOp", group = "linear OpMode")
public class TeleOp4Motor extends OpMode {

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor RearLeft;
    DcMotor RearRight;
    DcMotor OptionalMotor;
    boolean OptionalMotorExists = false;
    @Override


    public void init() {
        FrontLeft = hardwareMap.dcMotor.get("m1");
        FrontRight = hardwareMap.dcMotor.get("m2");
        RearLeft = hardwareMap.dcMotor.get("m3");
        RearRight = hardwareMap.dcMotor.get("m4");
        try {
            OptionalMotor = hardwareMap.dcMotor.get("m5");
            OptionalMotorExists = true;
        } catch (IllegalArgumentException e){
            OptionalMotorExists = false;
        }



        reverseMotor(FrontLeft);
        reverseMotor(RearLeft);


    }

    @Override
    public void loop() {

        double right = gamepad1.right_stick_y;
        double left = gamepad1.left_stick_y;
        double OptionalMotorSpeed = gamepad1.right_trigger;
        FrontRight.setPower(right);
        FrontLeft.setPower(left);
        RearRight.setPower(right);
        RearLeft.setPower(left);
        if (OptionalMotorExists){
            OptionalMotor.setPower(OptionalMotorSpeed);
        }


        telemetry.addData("Left power: ", left);
        telemetry.addData("Right power: ", right);
        telemetry.update();
    }

    public void reverseMotor(DcMotor motor) {

        motor.setDirection(DcMotor.Direction.REVERSE);
    }
// TODO: Test on robots to make sure the left wheels are the ones we want to reverse; might be the right wheels.
}