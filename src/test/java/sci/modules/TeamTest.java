package sci.modules;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

import sci.SCI;

public class TeamTest {
	
    @Test
    public void teamModuleName() {
        Module teamModule = new TeamModule();
        assertTrue(teamModule.getName().contains("Team"));
    }
    /**
     * Test module "team" to ensure that invoker is "team".
     */
    @Test
    public void teamModuleInvoker() {
        Module teamModule = new TeamModule();
        String[] invokers = teamModule.getInvokers();
        assertTrue(Arrays.asList(invokers).contains("team"));
    }

    // TODO: decouple from sample.txt
	@Test
	public void testProcessList() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = SCI.class.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(null);
		String out = new TeamModule().process("circle", new ArrayList<String>());
		assertTrue(out.contains("list of teams"));
        Method methodc = SCI.class.getDeclaredMethod("shutdown");
        methodc.setAccessible(true);
        methodc.invoke(null);
	}
	
	// TODO: decouple from sample.txt
	@Test
	public void testProcessSpecific() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = SCI.class.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(null);
		ArrayList<String> t = new ArrayList<>();
		t.add("a");
		String out = new TeamModule().process("circle", t);
		assertTrue(out.contains("Team"));
		assertFalse(out.contains("Error"));
        Method methodc = SCI.class.getDeclaredMethod("shutdown");
        methodc.setAccessible(true);
        methodc.invoke(null);
	}
	
	// TODO: decouple from sample.txt
	@Test
	public void testProcessSpecificNonExist() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = SCI.class.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(null);
		ArrayList<String> t = new ArrayList<>();
		t.add("icenyfg87er");
		String out = new TeamModule().process("circle", t);
		assertTrue(out.contains("Error:"));
        Method methodc = SCI.class.getDeclaredMethod("shutdown");
        methodc.setAccessible(true);
        methodc.invoke(null);
	}

}
