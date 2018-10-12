package fr.cmm.helper;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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

    @Test
    public void getPage(){
        Pagination pagination = new Pagination();
        List<Integer> listeTest = Arrays.asList(1,2,3,4,5);
        pagination.setCount(100);
        pagination.setPageSize(20);
        Assert.assertEquals(listeTest,pagination.getPages());
    }
}
