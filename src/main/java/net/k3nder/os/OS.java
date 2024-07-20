package net.k3nder.os;

public enum OS {
    WINDOWS,
    LINUX,
    DARWIN,
    OTHER;
    public static String getSystemName() {
        return System.getProperty("os.name");
    }
    public static OS getOS() {
        return ofString(getSystemName());
    }
    public static OS ofString(String s) {
        if (s.isEmpty()) return OTHER;
        return OS.valueOf(s.toUpperCase());
    }
    public static boolean isWindows() {
        return getOS() == WINDOWS;
    }
    public static boolean isLinux() {
        return getOS() == LINUX;
    }
    public static boolean isMac() {
        return getOS() == DARWIN;
    }
    public static boolean isOther() {
        return getOS() == OTHER;
    }
}
