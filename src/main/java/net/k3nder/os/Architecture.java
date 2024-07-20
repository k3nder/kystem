package net.k3nder.os;

public enum Architecture {
    X86("x86"),
    X86_64("amd64", "x86_64"),
    ARM("arm"),
    ARM64("aarch64"),
    UNKNOWN("");

    private final String[] aliases;

    Architecture(String... aliases) {
        this.aliases = aliases;
    }

    public static Architecture getArchitecture() {
        String osArch = System.getProperty("os.arch").toLowerCase();
        return ofString(osArch);
    }
    public static Architecture ofString(String string) {
        for (Architecture arq : values()) {
            for (String alias : arq.aliases) {
                if (string.contains(alias)) {
                    return arq;
                }
            }
        }
        return UNKNOWN;
    }
}
