package net.k3nder.os.exceptions;

public class NotEnoughRamException extends IncompatibleDeviceException {
    public NotEnoughRamException(int min) {
        super("not enough ram, min: " + min);
    }
}
