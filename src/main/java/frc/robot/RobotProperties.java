/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Add your docs here.
 */
public class RobotProperties {
    private static Properties properties = null;
    private static final String fileName = "/home/lvuser/deploy/robot.properties";

    public static void setProperty(String key,String value){
        getPropertiesInstant().setProperty(key, value); 
    } 

    public static String getProperty(String key){
        return getPropertiesInstant().getProperty(key); 
    }
    
    private static Properties getPropertiesInstant(){
        if (properties == null){
            loadProperties();
        }
        return properties;
    }

    private static void loadProperties() {
        try(InputStream input = new FileInputStream(fileName)){
            properties = new Properties();  
            properties.load(input);
        }
      catch(IOException ex){
          System.err.println(ex.getMessage());
      }
    }
} 
