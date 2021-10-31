package util.condition;

public enum Status {
    IDLE, // Waiting for commands and is ready to be used
    ACTIVE, // Being used
    DISABLED, // Not allowed to be used
    INACTIVE // Is not being used or off
}
