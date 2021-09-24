package kz.ferius_057.ruminebot.database.tool;

/**
 * @author Charles_Grozny
 */
public class UptimeTool {
    private static String timeStart;
    private static long timeStartMs;

    public static String getTimeStart() {
        return timeStart;
    }

    public static long getTimeStartMs() {
        return timeStartMs;
    }

    public void setTimeStart(String timeStart) {
        UptimeTool.timeStart = timeStart;
    }

    public void setTimeStartMs(long timeStartMs) {
        UptimeTool.timeStartMs = timeStartMs;
    }
}