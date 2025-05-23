package com.llm.learn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SingleLinkedList {

    private Node headNode;
    private int size;

    public SingleLinkedList(Object data) {
        this.headNode = new Node(data, null);
    }

    public void insertAtBeginning(Object value) {

        Node currentNode = new Node(value, null);
        currentNode.nextNode = this.headNode;
        this.headNode = currentNode;
    }

    public void insertAtEnd(Object value) {

        Node iterator = this.headNode;
        while(iterator.nextNode!=null) { // If you iterate on just iterator, when you do iterator.nextNode you will get NPE., so iterate on iterator.nextNode
            iterator = iterator.nextNode;
        }
        iterator.nextNode = new Node(value,null);
        this.size++;
    }

    public void insertAtIndex(int index, Object value) {
        if(index < 0 && index > this.size)
            throw new IndexOutOfBoundsException("Index out of bound");

        Node iterator = this.headNode;
        int i = 0;
        while(i < index-1) {
            iterator = iterator.nextNode;
            i++;
        }
        Node currentNode = new Node(value, null);
        Node existingNode = iterator.nextNode;
        iterator.nextNode = currentNode;
        currentNode.nextNode = existingNode;
        this.size++;
    }

    public void deleteAtBeginning() {
        Node head = this.headNode;
        this.headNode = this.headNode.nextNode;
    }

    public void deleteAtEnd() {
        Node iterator = this.headNode;
        while(iterator.nextNode.nextNode!=null) {
            iterator = iterator.nextNode;
        }
        iterator.nextNode = null;
    }

    public void deleteAtIndex(int index) {
        int position  = 0;
        Node iterator = this.headNode;
        while(position < index-1) {
            iterator = iterator.nextNode;
        }
        if(iterator.nextNode.nextNode!= null)
            iterator.nextNode = iterator.nextNode.nextNode;
        else
            iterator.nextNode = null;
    }

    public void deleteAll(Object value) {
        this.headNode = null;
    }

    public Object getFirstElement() {
        return this.headNode.data;
    }

    public Object getLastElement() {
        Node iterator = this.headNode;
        while(iterator.nextNode!=null) {
            iterator = iterator.nextNode;
        }
        return iterator.data;
    }

    public Object getValueAtIndex(int index) {
        int position = 0;
        if(this.headNode.nextNode==null)
            return this.headNode.data;

        Node iterator = this.headNode;
        while(position < index) {
            iterator = iterator.nextNode;
            position++;
        }
        return iterator.data;
    }


    private static class Node {
        private Object data;
        private Node nextNode;

        public Node() {
        }

        public Node(Object data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
            List<String> as = new LinkedList<>();
        }
    }

    @Override
    public String toString() {
        return "SingleLinkedList{" +
                "headNode=" + headNode.data +
                ", size=" + size +
                '}';
    }
    // Function to print the contents of the linked list
    public static void printList(Node head) {
        // Start from the head of the list
        Node curr = head;

        // Traverse the list
        while (curr != null) {
            // Print the current node's data
            System.out.print(" " + curr.data);

            // Move to the next node
            curr = curr.nextNode;
        }

        // Print a newline at the end
        System.out.println();
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList(1);
        printList(singleLinkedList.headNode);
        singleLinkedList.insertAtBeginning(2);
        printList(singleLinkedList.headNode);
        singleLinkedList.insertAtEnd(3);
        printList(singleLinkedList.headNode);
        singleLinkedList.insertAtIndex(1,5);
        printList(singleLinkedList.headNode);
        singleLinkedList.deleteAtBeginning();
        printList(singleLinkedList.headNode);
        singleLinkedList.deleteAtIndex(1);
        printList(singleLinkedList.headNode);
        singleLinkedList.deleteAtEnd();
        printList(singleLinkedList.headNode);

        singleLinkedList.insertAtBeginning(122);
        singleLinkedList.insertAtBeginning(9);
        singleLinkedList.insertAtBeginning(1);
        singleLinkedList.insertAtBeginning(8);
        printList(singleLinkedList.headNode);

        System.out.println(singleLinkedList.getFirstElement());
        System.out.println(singleLinkedList.getLastElement());
        System.out.println(singleLinkedList.getValueAtIndex(2));
    }
    public int maxConsecutiveGoodNums(int[] nums, int[] goodNumbers) {
        int curremtMax = 0;
        int totalMax = 0;
        // Create hashMap for faster look up whenever you need to look again and again in a data structure
        Map<Integer,Integer> lookUpTable = new HashMap<>();

        for(int i=0;i<goodNumbers.length;i++) {
            lookUpTable.put(goodNumbers[i],
                    lookUpTable.getOrDefault(lookUpTable.get(goodNumbers[i]),0)+1);
        }

        // iterate over nums and check in lookup table, if present, increment curremtMax by
        // or else reset curremtMax = 0. Update totalMax using max(totalMax,curremtMax)
        for(int i=0;i<nums.length;i++) {
            if(lookUpTable.containsKey(nums[i])) {
                curremtMax++;
            } else {
                curremtMax = 0;
            }
            totalMax = Math.max(totalMax, curremtMax);
        }
        return totalMax;
    }
}
