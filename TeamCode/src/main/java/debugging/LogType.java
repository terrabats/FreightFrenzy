package debugging;

public enum LogType {
    /**
     * hows to screen and logs output
     */
    NORMAL,
    /**
     * Stores an array of all previous values
     */
    MONITOR,
    /**
     * Only shows to screen so you can "watch" it
     */
    WATCH,
    /**
     * Does not output telemetry but saves when the value changed
     */
    SAVE
}
