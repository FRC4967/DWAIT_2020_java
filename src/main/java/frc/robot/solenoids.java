/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Solenoid;
public class solenoids{
    Solenoid open;
    boolean active = false;
    public void init() {

        open = new Solenoid(7);

        open.set(false);

    }
    public void on(boolean val){
        if(val == true){
            open.set(true);
        }
        else{
            open.set(false);
        }
    }
    public void off(){

       open.set(false); 

    }
    
}