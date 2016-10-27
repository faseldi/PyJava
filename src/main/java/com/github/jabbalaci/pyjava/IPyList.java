package com.github.jabbalaci.pyjava;

import java.util.List;

/**
 * 
 * Interface that represents  all methods that need to implement a PyList
 */
public interface IPyList<T> extends List<T>{
    // make a python slice
    public IPyList<T> slice(int begin, int end);
}
