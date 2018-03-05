package sci.modules;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.reflections.Reflections;

import sci.SCI;

/**
 * Collection of tests for the exit module
 * 
 * @author rlee287
 */
public class HelpTest {
    /**
     * Test module "help" has name including "Help".
     */
    @Test
    public void helpModuleName() {
        Module helpModule = new HelpModule();
        assertTrue(helpModule.getName().contains("Help"));
    }

    /**
     * Test module "help" to ensure that invoker is "help".
     */
    @Test
    public void helpModuleInvoker() {
        Module helpModule = new HelpModule();
        String[] invokers = helpModule.getInvokers();
        assertTrue(Arrays.asList(invokers).contains("help"));
    }

    /**
     * Test module "help" to ensure that it returns help items.
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     */
    @Test
    public void helpModuleProcess() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        ArrayList<String> listStr = new ArrayList<>();
        Method method = SCI.class.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(null);
        
        Reflections reflect = new Reflections("sci.modules");
        for( Class<?> c : reflect.getSubTypesOf( sci.modules.Module.class ) ) {
            Module m = null;
            m = (Module) c.newInstance();
            for (String inv:m.getInvokers()) {
                listStr.add(inv);
            }
        }
        
        Module helpModule = new HelpModule();
        for (String mod:listStr) {
            assertTrue(helpModule.process("No NullLens for The Room", new ArrayList<String>()).contains(mod));
        }
        Method methodc = SCI.class.getDeclaredMethod("shutdown");
        methodc.setAccessible(true);
        method.invoke(null);
    }
    
    /**
     * Test module "help" to ensure that it returns specific help items.
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    @Test
    public void helpModuleProcessSpecific() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        ArrayList<String> listStr = new ArrayList<>();
        Method method = SCI.class.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(null);
        
        Reflections reflect = new Reflections("sci.modules");
        for( Class<?> c : reflect.getSubTypesOf( sci.modules.Module.class ) ) {
            Module m = null;
            m = (Module) c.newInstance();
            for (String inv:m.getInvokers()) {
                listStr.add(inv);
            }
        }
        
        Module helpModule = new HelpModule();
        for (String mod:listStr) {
            ArrayList<String> modinput = new ArrayList<>();
            modinput.add(mod);
            assertTrue(helpModule.process("No NullLens for The Room", modinput).contains(mod));
        }
        Method methodc = SCI.class.getDeclaredMethod("shutdown");
        methodc.setAccessible(true);
        method.invoke(null);
    }
    
    /**
     * Test module "help" to ensure that it handles nonexistent help items.
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    @Test
    public void helpModuleProcessNonExistent() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method method = SCI.class.getDeclaredMethod("init");
        method.setAccessible(true);
        method.invoke(null);
        
        String mod = "I exist unthinking because what does this test matter in the universe's billions of years";
        ArrayList<String> modinput = new ArrayList<>();
        modinput.add(mod);
        Module helpModule = new HelpModule();
        assertTrue(helpModule.process("No NullLens for The Room", modinput).contains("does not exist"));
        Method methodc = SCI.class.getDeclaredMethod("shutdown");
        methodc.setAccessible(true);
        method.invoke(null);
    }
}
