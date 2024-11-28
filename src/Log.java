public class Log {

    private final StringBuffer log = new StringBuffer();
    private final StringBuilder builder = new StringBuilder();


    public String getLog() {
        return log.toString();
    }

    public void append(String msg) {
        log.append(msg);

    }

    public void initialiseBuffer() {
        log.setLength(0);

    }
}
