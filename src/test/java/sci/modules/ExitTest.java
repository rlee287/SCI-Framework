package sci.modules;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * Collection of tests for the exit module
 * @author rlee287
 */
public class ExitTest {
    /**
     * Test module "exit" has name including "Exit".
     */
    @Test
    public void exitModuleName() {
        Module exitModule = new ExitModule();
        assertTrue(exitModule.getName().contains("Exit")
                || exitModule.getName().contains("Quit")
                || exitModule.getName().contains("Close"));
    }
    /**
     * Test module "exit" to ensure that invoker is "exit".
     */
    @Test
    public void exitModuleInvoker() {
        Module exitModule = new ExitModule();
        String[] invokers = exitModule.getInvokers();
        assertTrue(Arrays.asList(invokers).contains("exit"));
        assertTrue(Arrays.asList(invokers).contains("quit"));
        assertTrue(Arrays.asList(invokers).contains("close"));
    }

    /**
     * Test module "exit" to ensure that it returns null.
     */
    @Test
    public void exitModuleProcess() {
        Module exitModule = new ExitModule();
        assertEquals(exitModule.process("No NullLens for The Room", null),null);
    }
}