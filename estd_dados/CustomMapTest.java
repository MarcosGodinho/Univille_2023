import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CustomMapTest {

    private CustomMap<String, Integer> map;

    @Before
    public void setUp() {
        map = new CustomMap<>(10);
        map.put("um", 1);
        map.put("dois", 2);
        map.put("três", 3);
    }

    @Test
    public void testGetExistingKey() {
        assertEquals(Integer.valueOf(1), map.get("um"));
        assertEquals(Integer.valueOf(3), map.get("três"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetNonExistingKey() {
        map.get("quatro");
    }

    @Test
    public void testContainsKey() {
        assertTrue(map.containsKey("um"));
        assertFalse(map.containsKey("quatro"));
    }

    @Test
    public void testRemoveKey() {
        map.remove("dois");
        assertFalse(map.containsKey("dois"));
        assertEquals(2, map.size());
    }
}
