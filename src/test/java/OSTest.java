import net.k3nder.os.OS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OSTest {
    @Test
    void getSystemNameTest() {
        var osName = OS.getSystemName();
        System.out.println(osName);
        Assertions.assertEquals("Linux", osName);
    }
}
