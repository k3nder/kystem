package net.k3nder.os.exceptions;

import net.k3nder.os.OS;

public class IncompatibleSystemException extends IncompatibleDeviceException {
    public IncompatibleSystemException() {
        super("not compatible system " + OS.getSystemName());
    }
}
