package com.github.jabbalaci.pyjava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SlicingTest {
    @Test
    public void normalSliceTest() {
        IPyList<Integer> list = getPyList(0, 100);
        list = list.slice(5,10);
        List<Integer> neededList = new ArrayList<>();
        IntStream.range(6, 11)
                 .forEach(neededList::add);
        assertTrue( list.equals(neededList));
    }
    @Test
    public void negativeStartTest() {
        IPyList<Integer> list = getPyList(1, 13);
        // need empty array
        
        assertTrue(list.slice(-3, 9).equals(new PyList<>()));
        // need 8,9
        IPyList<Integer> neededList = new PyList<>();
        neededList.add(8);
        neededList.add(9);
        //list.slice(-3, 9).forEach(System.out::println);
        assertTrue(list.slice(-5, 9).equals(neededList));
        assertTrue(list.slice(-1000, 0).equals( new PyList<>() ));
    }
    @Test 
    public void negativeEndTest() {
        IPyList<Integer> list = getPyList(2, 10);
        assertTrue(list.slice(5, -10).equals( new PyList<>() ));
        
        IPyList<Integer> neededList = new PyList<>();
        neededList.add(7);
        assertTrue(list.slice(5, -2).equals(neededList));
        
        assertTrue(list.slice(500, -1000).equals( new PyList<>() ));
    }
    
    @Test
    public void bothNegativeTest() {
        IPyList<Integer> list = getPyList(5, 12);
        //list.slice(-7, -2).forEach(System.out::println);
        assertTrue(list.slice(-7, -2).equals( getPyList(5, 10) ));
        
    }
    
    private static IPyList getPyList(int b, int e) {
        PyList<Integer> list = new PyList<>();
        IntStream.range(b,e)
                .forEach(list::add);
        return list;
    }
}
