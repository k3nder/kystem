package net.k3nder.os;

public class RamUtils {
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }
    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }
}
