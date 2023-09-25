/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ias.sftp.sftp.test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Level;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author METIN
 */
public class JSCHLogger implements com.jcraft.jsch.Logger {
    private Map<Integer, Level> levels = new HashMap<Integer, Level>();
   
    private final Logger LOGGER;


    public JSCHLogger() {
        // Mapping between JSch levels and our own levels
        levels.put(DEBUG, Level.DEBUG);
        levels.put(INFO, Level.INFO);
        levels.put(WARN, Level.WARN);
        levels.put(ERROR, Level.ERROR);
        levels.put(FATAL, Level.FATAL);

        LOGGER = Logger.getLogger(SFTPFrm.class); // Anything you want here, depending on your logging framework
        PropertyConfigurator.configure("log4j.properties");
    }

    @Override
    public boolean isEnabled(int pLevel) {
        return true; // here, all levels enabled 
    }

    @Override
    public void log(int pLevel, String pMessage) {
        Level level = levels.get(pLevel);
        if (level == null) {
            level = Level.FATAL;
        }
        LOGGER.log(level, pMessage); // logging-framework dependent...
    }
}