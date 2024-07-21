package net.k3nder.os;

import net.k3nder.os.events.OnIncompatibility;
import net.k3nder.os.exceptions.IncompatibleSystemException;
import net.k3nder.os.exceptions.IncopatibleArchitectureException;
import net.k3nder.os.exceptions.NotEnoughRamException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class Compatibility {
    public void verify(Specicications specicications, OnIncompatibility event) throws IOException {

        // verify architecture
        var compatibleArchitectures = specicications.architecture;
        var isCompatibleArchitecture = false;
        for (Architecture compatibleArchitecture : compatibleArchitectures) {
            if (Architecture.UNKNOWN.equals(compatibleArchitecture)) {
                isCompatibleArchitecture = true;
                break;
            }
            if (isArchitecture(compatibleArchitecture)) { isCompatibleArchitecture = true; break; }
        }
        if (!isCompatibleArchitecture) event.run(new IncopatibleArchitectureException());

        // verify ram
        var min = specicications.ram;
        if (!isRam(min)) event.run(new NotEnoughRamException(min));

        // OS
        var compatibleSystems = specicications.systems;
        var isCompatibleSystem = false;
        for (OS compatibleSystem : compatibleSystems) {
            if (OS.OTHER.equals(compatibleSystem)) {
                isCompatibleSystem = true;
                break;
            }
            if (isSystem(compatibleSystem)) { isCompatibleSystem = true; break; }
        }
        if (!isCompatibleSystem) event.run(new IncompatibleSystemException());
    }
    public void verify(InputStream file, OnIncompatibility event) throws IOException {
        Properties properties = new Properties();
        properties.load(file);
        var specicications = new Specicications(
                Integer.parseInt(properties.getProperty("RAM")),
                Arrays.stream(properties.getProperty("ARCHITECTURE").split(";")).map(Architecture::ofString).toList().toArray(new Architecture[] {}),
                Arrays.stream(properties.getProperty("SYSTEM").split(";")).map(OS::ofString).toList().toArray(new OS[] {}));
        verify(specicications, event);
    }
    public void verify(OnIncompatibility event) throws IOException {
        verify(getClass().getResourceAsStream("/specifications.properties"), event);
    }
    public boolean isRam(int min) {
        var ram = RamUtils.getMaxMemory();
        return ram >= min;
    }
    public boolean isSystem(OS system) {
        var thisOS = OS.getOS();
        return thisOS == system;
    }
    public boolean isArchitecture(Architecture otherArchitecture) {
        var thisArchitecture = Architecture.getArchitecture();
        if (thisArchitecture == Architecture.UNKNOWN || otherArchitecture == Architecture.UNKNOWN) {
            return false;
        }
        if (thisArchitecture == otherArchitecture) {
            return true;
        }
        if ((thisArchitecture == Architecture.X86 && otherArchitecture == Architecture.X86_64) || (thisArchitecture == Architecture.X86_64 && otherArchitecture == Architecture.X86)) {
            return true;
        }
        if ((thisArchitecture == Architecture.ARM && otherArchitecture == Architecture.ARM64) || (thisArchitecture == Architecture.ARM64 && otherArchitecture == Architecture.ARM)) {
            return true;
        }
        return false;
    }
    public record Specicications(int ram, Architecture[] architecture, OS[] systems) {

    }
}
