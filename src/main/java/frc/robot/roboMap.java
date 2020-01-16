package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Robot;




public class roboMap{
    TalonSRX LMRight = new TalonSRX(2);
    TalonSRX RMRight = new TalonSRX(1);
    TalonSRX RMLeft = new TalonSRX(5);
    TalonSRX LMLeft = new TalonSRX(4);
    Robot whoops = new Robot();
    public void follow(){
        
        LMLeft.follow(whoops.MMLeft);
        LMRight.follow(whoops.MMRight);
        RMLeft.follow(whoops.MMLeft);
        RMRight.follow(whoops.MMRight);


    }
    







    


}