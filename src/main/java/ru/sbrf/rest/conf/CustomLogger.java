package ru.sbrf.rest.conf;

import org.slf4j.Logger;

public class CustomLogger {
    Logger logger;

    public CustomLogger(Logger logger) {
        this.logger = logger;
    }


    public void info(String message){
        if (logger.isInfoEnabled()){
            logger.info(message);
        }
    }
    public void info(String message, Object o){
        if (logger.isInfoEnabled()){
            logger.info(message, o);
        }
    }
    public void info(String message, Object ... o){
        if (logger.isInfoEnabled()){
            logger.info(message, o);
        }
    }

    public void debag(String message){
        if (logger.isDebugEnabled()){
            logger.debug(message);
        }
    }
    public void debag(String message, Object o){
        if (logger.isDebugEnabled()){
            logger.debug(message, o);
        }
    }
    public void debag(String message, Object ... o){
        if (logger.isDebugEnabled()){
            logger.debug(message, o);
        }
    }
    public void trace(String message){
        if (logger.isTraceEnabled()){
            logger.trace(message);
        }
    }
    public void trace(String message, Object o){
        if (logger.isTraceEnabled()){
            logger.trace(message);
        }
    }
    public void trace(String message, Object ... o){
        if (logger.isTraceEnabled()){
            logger.trace(message);
        }
    }
}
