package com.github.jabbalaci.pyjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


public class PyList<T> extends ArrayList<T> implements IPyList<T>{
    
    public PyList(){
        super();
    }
    
    public PyList(int size){
        super(size);
    }
    /**
     * This method works like [begin:end] python do
     * @param begin The begining index
     * @param end The starting index
     * @return A new IPyList sliced
     */
    @Override
    public IPyList<T> slice(int begin, int end) {
        int size = size();
        // adapt begin and end to work like python
        if(begin < 0){
            begin = size + begin;
        }else{
            // if begin > 0 && end > 0
            if(end > 0){
                begin ++;
                end ++;
            }
        }
        if(end < 0){
            end = size+ end;
        }
        
        PyList<T> slice = new PyList<>();
        // to avoid out of bounds and useless iterate
        if(begin < 0 ) {
            begin = 0;
        }
        if(end < 0 ) {
            end = 0;
        }
        // check if need to iterate
        if(begin != end){
            IntStream.range(begin, end)
                    .forEach( i -> {
                        slice.add(this.get(i));
                    });
        }
        return slice;
    }
    /**
     * Sort the list in place. It has no return value.
     *
     * @param li A list to be sorted.
     */
    public static <T extends Object & Comparable<? super T>> void sort(List<T> li) {
        Collections.sort(li);
    }

    /**
     * Sort the list in place. It has no return value.
     *
     * @param li The list to be sorted.
     * @param reverse If set to true, then the list is sorted in
     * descending order.
     */
    public static <T extends Object & Comparable<? super T>> void sort(List<T> li,  boolean reverse) {
        if (reverse == false) {
            sort(li);
            return;
        }
        // else, if reverse == true
        Collections.sort(li, Collections.reverseOrder());
    }

    /**
     * Reverse the list in place. It has no return value.
     *
     * @param li A list to be reversed.
     */
    public static <T extends Object & Comparable<? super T>> void reverse(List<T> li) {
        Collections.reverse(li);
    }

}
