package debugging;

public enum LogType {
    NORMAL, // Shows to screen and logs output (only logs when changed)
    MONITOR, // Stores an array of all previous values
    WATCH, // Only shows to screen so you can "watch" it
    SAVE // Does not output telemetry but saves when the value changed
}
