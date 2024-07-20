package net.k3nder.os.events;

import net.k3nder.os.exceptions.IncompatibleDeviceException;

@FunctionalInterface
public interface OnIncompatibility {
    void run(IncompatibleDeviceException e);
}
