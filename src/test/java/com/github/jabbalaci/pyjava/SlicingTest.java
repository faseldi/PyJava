package com.github.jabbalaci.pyjava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Assert;
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
        assertTrue(list.slice(-7, -2).equals( getPyList(5, 10) ));
        
    }
    @Test
    public void bothOutOfBoundUp() {
        IPyList<Integer> list = getPyList(5, 12);
        assertTrue(list.slice(7, 100).equals(new PyList<>()));
        assertTrue(list.slice(100000, 200000).equals(new PyList<>()));
    }
    @Test
    public void StringTest() {
        Assert.assertEquals("ab", PyStr.slice("abcd", 0, 2));
        Assert.assertEquals("ab", PyStr.slice("abcd", -100, 2));
        Assert.assertEquals("abcd", PyStr.slice("abcd", 0, 200));
        Assert.assertEquals("", PyStr.slice("abcd", 0, 0));
        Assert.assertEquals("", PyStr.slice("abcd", 4, 4));
    }
    
    @Test
    public void stepTest() {
        IPyList<Integer> list = getPyList(1, 9);
        IPyList  needed = new PyList<>();
        needed.add(3);
        needed.add(6);
        assertTrue(list.slice(2,7,3).equals(needed));
        list = getPyList(1, 20);

        needed.clear();
        needed.add(10);
        needed.add(7);
        needed.add(4);
        
        assertTrue(list.slice(-10,2,-3).equals(needed));
        
        needed = getPyList(4, 11);
        PyList.reverse(needed);        
        assertTrue(list.slice(-10,2,-1).equals(needed));

    }
    
    private static IPyList getPyList(int b, int e) {
        PyList<Integer> list = new PyList<>();
        IntStream.range(b,e)
                .forEach(list::add);
        return list;
    }
}
