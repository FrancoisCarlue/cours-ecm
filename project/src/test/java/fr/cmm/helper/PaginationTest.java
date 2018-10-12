package fr.cmm.helper;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Francois on 12/10/2018.
 */
public class PaginationTest {

    @Test
    public void pagination(){
        Pagination pagination = new Pagination();
        pagination.setCount(100);
        pagination.setPageSize(20);
        assertEquals(5,pagination.getPageCount());
        pagination.setCount(110);
        pagination.setPageSize(20);
        assertEquals(6,pagination.getPageCount());
        pagination.setCount(0);
        assertEquals(1,pagination.getPageCount());
    }
}
