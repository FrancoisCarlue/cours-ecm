package fr.cmm.tags;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Francois on 11/10/2018.
 */
public class FunctionsTest {

    @Test
    public void testNothing() {
        assertEquals("a", Functions.text("a"));
    }

    @Test
    public void testReturn() {
        assertEquals("a<br>", Functions.text("a\n"));
    }

    @Test
    public void testAnd() {
        assertEquals("&amp;a", Functions.text("&a"));
    }



}
