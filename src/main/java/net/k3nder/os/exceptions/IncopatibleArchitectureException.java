package net.k3nder.os.exceptions;

import net.k3nder.os.Architecture;

public class IncopatibleArchitectureException extends IncompatibleDeviceException {
    public IncopatibleArchitectureException() {
        super("architecture '" + Architecture.getArchitecture() + "' is incompatible");
    }
}
