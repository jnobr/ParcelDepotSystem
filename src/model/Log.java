package model;

import java.io.Serializable;

public class Log implements Serializable {

    private static Log instance;
    private final StringBuffer log = new StringBuffer();
    private final StringBuilder builder = new StringBuilder();

    public Log() {
        initialiseBuffer();
    }
    public String getLog() {
        return log.toString();
    }

    public void append(String msg) {
        log.append(msg);

    }

    public void initialiseBuffer() {
        log.setLength(0);

    }
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }
}
