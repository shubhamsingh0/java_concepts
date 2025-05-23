package com.llm.learn;

import java.util.Arrays;

public class ArrayListImpl<T> {
    private Object[] arr;
    private int size;
    private int capacity;

    public ArrayListImpl() {
        this.capacity = 4;
        this.arr = new Object[this.capacity];
        this.size = 0;
    }

    public void add(T element) {

        if (this.size >= this.capacity) {
            reOrganizeArray(this.arr);
        }
        this.arr[this.size] = element;
        this.size++;
    }

    public void delete(int index) {
        if(index < 0 || index > this.size) throw new ArrayIndexOutOfBoundsException("Array is empty");
        for (int i=index;i<size-1;i++) {
            this.arr[i] = this.arr[i+1];
        }
        reSizeArray(this.arr);
    }

    public T get(int index) {
        if(index < 0 || index > this.size) throw new ArrayIndexOutOfBoundsException("Array is empty");
        return (T) this.arr[index];
    }

    private void reSizeArray(Object[] arr) {
        this.capacity--;
        this.size--;
        Object[] reOrganizedArray = new Object[this.capacity];
        int count = 0;
        for(int i=0;i<this.size;i++) {
            reOrganizedArray[count++] = this.arr[i];
        }
        this.arr = reOrganizedArray;
    }

    private void reOrganizeArray(Object[] arr) {
        int newCapacity = 2 * this.capacity;
        Object[] reOrganizedArray = new Object[newCapacity];
        int count = 0;
        for(Object element : arr) {
            reOrganizedArray[count++] = element;
        }
        this.arr = reOrganizedArray;
        this.capacity = newCapacity;
    }

    @Override
    public String toString() {
        for(Object a: this.arr) {
            System.out.print(a+" ");
        }
        return "ArrayListImpl{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }

    public static void main(String[] args) {
        ArrayListImpl<Integer> a = new ArrayListImpl<>();
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(1);
        a.add(9);
//        System.out.println(a);
        a.delete(1);
        a.delete(1);
        a.add(9);
        a.add(9);a.add(9);a.add(9);a.add(9);a.add(9);a.add(9);a.add(9);
//        System.out.println(a);
        a.delete(1);
        a.delete(1);a.delete(1);
        a.delete(1);a.delete(1);
        a.delete(1);a.delete(1);
        a.delete(1);
        System.out.println(a);
        System.out.println(a.get(1));
    }


}
