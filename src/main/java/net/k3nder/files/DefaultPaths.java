package net.k3nder.files;

import net.k3nder.os.OS;

import java.io.File;
/**
 * @author k3nder
 * */
public class DefaultPaths {
    private DefaultPaths() {}
    private static final OS SYS = OS.getOS();

    public static final String HOME = System.getProperty("user.home");
    public static final String TMP = System.getProperty("java.io.tmpdir");
    public static final String SEPARATOR = File.separator;
    public static final String APP_HOME = (SYS == OS.WINDOWS ? Files.mount(HOME, "appdata", "roaming").toString() : (SYS == OS.LINUX ? Files.mount("/usr", "local").toString() : Files.mount("/Applications").toString()));
}
