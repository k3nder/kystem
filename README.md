## What is kystem
Kystem is a library for system management that provides mainly compatibility utilities.

### Compatibility
To use the compatibility function, you utilize the `Compatibility` class and a `specicications.properties` file (although using the file is not necessary). The file must contain the following values:
```properties
ARCHITECTURE=
RAM=1000
SYSTEM=linux
```

#### Architecture properties
The `ARCHITECTURE` property is a list separated by `;`. For example, `X32;X64`. In this case, leaving it empty indicates that all architectures are supported. The possible values are:

| name            | description          |
|-----------------|----------------------|
| x86             | for x32 systems      |
| amd64 or x86_64 | for x64 systems      |
| arm             | for ARM systems      |
| aarch64         | for ARM64 systems    |
|                 | for all systems      |

#### RAM properties
The `RAM` property sets the minimum RAM required to run the application, in MB.

#### System properties
The `SYSTEM` property is a list separated by `;`, establishing all the operating systems that can use the app. The valid values are:

| name    |
|---------|
| windows |
| linux   |
| darwin  |

Leaving it blank indicates that all operating systems are supported.

#### Starting the verification
With the `specicications.properties` file in the default package within resources, invoke the following method in the main:
```java
new Compatibility().verify((e) -> {
            e.printStackTrace();
        });
```

This verifies the file and the device for compatibility, and if not compatible, triggers the error event.

##### Starting verification with another file
To verify specifications from another file, you can use:
```java
new Compatibility().verify(getClass().getResourceAsStream("/spec.properties"), (e) -> {
            e.printStackTrace();
        });
```
The first argument is an `InputStream` with the file to be verified.

##### Starting verification without files
To start verification without files, you can pass a `Compatibility.Specifications` record. The first argument is the RAM, the second the compatible architectures, and the third the systems. Example:
```java
new Compatibility().verify(new Compatibility.Specifications(1000, new Architecture[]{ Architecture.X86_64 }, new OS[] { OS.LINUX }), (e) -> {
            e.printStackTrace();
        });
```
#### Possible errors
Within `net.k3nder.os.exceptions`, there are compatibility errors, all extending from `IncompatibleDeviceException`. These include:
* `IncompatibleSystemException`: thrown when the OS is incompatible with the application
* `IncompatibleArchitectureException`: thrown when the architecture is incompatible
* `NotEnoughRamException`: thrown when there is not enough memory

### Detecting the operating system
You can use `OS.getOS()` which returns an enum of type `OS`.

### Paths
Kystem provides utilities for managing file locations:
* `DefaultPaths`: a class with path constants, such as:
    * Temporary Directory (`TMP`)
    * Applications Directory (`APP_HOME`)
    * User Home Directory (`HOME`)
* `Files`: contains utilities such as:
    * `getUserName()`: to get the username
    * `mount(...)`: takes a list of strings and mounts a Path from them, using the system's separators