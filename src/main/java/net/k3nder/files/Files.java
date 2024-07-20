package net.k3nder.files;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Files {
    private Files() {}
    public static String getUserName() {
        return System.getProperty("user.name");
    }
    public static Path mount(String... paths) {
        return Paths.get(String.join(DefaultPaths.SEPARATOR, paths));
    }
}
