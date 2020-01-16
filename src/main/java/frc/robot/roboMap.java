package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;




public class roboMap{
    VictorSPX MMRight = new VictorSPX(3);
    VictorSPX MMLeft = new VictorSPX(6);
    TalonSRX LMRight = new TalonSRX(2);
    TalonSRX RMRight= new TalonSRX(1);
    TalonSRX RMLeft = new TalonSRX(5);
    TalonSRX LMLeft = new TalonSRX(4);
    public void init(){
        
        LMLeft.follow(MMLeft);
        LMRight.follow(MMRight);
        RMLeft.follow(MMLeft);
        RMRight.follow(MMRight);


    }
    







    


}