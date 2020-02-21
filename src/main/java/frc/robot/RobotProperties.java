/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Add your docs here.
 */
public class RobotProperties { 
    private static Properties properties = null;
    private static final String fileName = "/home/lvuser/deploy/robot.properties";

    private RobotProperties(){
     //intentionally left blank
    }

    public static void load () {
        loadProperties();
    }

    
    public static void setProperty(String key, int intValue){
        setProperty(key, Integer.toString(intValue));
    }

    public static void setProperty(String key, double doubleValue){
        setProperty(key, Double.toString(doubleValue));
        
    }

    public static void setProperty(String key,String value){
        getPropertiesInstant().setProperty(key, value); 
        try(OutputStream output = new FileOutputStream(fileName)) {
            getPropertiesInstant().store(output, null);
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    } 

    public static String getPropertyAString(String key){
        return getPropertiesInstant().getProperty(key); 
    }

    public static int getPropertyAsInt(String key){
        return Integer.parseInt(getPropertiesInstant().getProperty(key));
    }

    public static double getPropertyAsDouble(String key){
        return Double.parseDouble(getPropertiesInstant().getProperty(key));
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
