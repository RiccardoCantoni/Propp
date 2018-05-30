/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import propp.SystemState;

/**
 *
 * @author Riccardo
 */
public class LogManager {
    
    private static String logfile = "log.txt";
    
    public static void createLog(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try{
        PrintWriter writer = new PrintWriter(logfile, "UTF-8");
        writer.println("Log file initialized at: "+timestamp);
        writer.close();
        }catch(Exception e){}
    }
    
    public static void addEntry(String entry){
        if (!SystemState.getInstance().loggingMode) return;
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(logfile, true));
            writer.append("\r\n"+entry);
            writer.close();
        }catch (IOException e) {}
    }
    
}
