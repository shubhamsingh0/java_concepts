package com.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsLearn {

/*
    1. How would you collect all customer emails into a set, ensuring no duplicates?
    2. Given a list of transactions, how can you group them by account type and calculate the total transaction amount for each type?
    3. How would you find the account with the minimum balance using streams?
    4. How can you partition accounts into two groups: those that are active and those that are inactive?
    5. How would you concatenate all branch names into a single string, separated by semicolons?
    6. How can you use a custom comparator to sort customers by last name and then by first name?
    7. How would you use the reduce method to calculate the product of all loan amounts in a list?
    8. How can you collect all unique cities where customers live into a list?
    9. How would you find the average balance of all savings accounts using streams?
   10. How can you use streams to find the second highest transaction amount for a given customer?

*/


    public static void main(String[] args) {
        // 1.
        List<Integer> ele = List.of(1,2,3,4,5,6,7,8,9);
        List<Integer> ele2 = List.of(11,12,13,14,15,16,17,18,19,9,11);
        // find the kth smallest element in a list of integers
        int k=5;
        System.out.println(ele2.stream().sorted(Comparator.comparing(Integer::intValue, Comparator.naturalOrder())).skip(k-1).findFirst().get());
        // find the intersection of two lists of strings
        System.out.println(ele.stream().filter(ele2::contains).toList());
        System.out.println(Stream.concat(ele.stream(),ele2.stream()).distinct().toList()); // union of 2 lists
        System.out.println(ele.indexOf(5)); // print the index of the first occurrence of a specific number
        System.out.println(ele.stream().reduce(Integer::sum));
        System.out.println(ele.stream().collect(Collectors.averagingInt(x->x)));
        System.out.println(ele.stream()
                .mapToDouble(Integer::intValue)
                .average());
        System.out.println(ele.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());
        System.out.println(ele.stream().reduce((a,b)->a*b));
        System.out.println(ele.stream().max(Comparator.naturalOrder()));
//        find the frequency of each element in a list of integers
        System.out.println(ele2.stream().collect(Collectors.groupingBy(Integer::intValue,Collectors.counting())));

        List<String> e = Arrays.asList("a","as","fg","ds","as","ass","dsf","fgf  poj hjgj");
//        check if all elements in a list of strings are of the same length
        System.out.println(e.stream().map(String::length).distinct().count()==1); // if the string have same length then there will be only 1 distinct value.
        System.out.println(e.stream().map(str->str.replaceAll("\\s","")).toList()); // remove all whitespace from a list of strings
        System.out.println(e.stream().filter(CollectorsLearn::containsDuplicateCharacters).toList()); //  print the strings containing duplicate characters
        System.out.println(e.stream().filter(x->x.chars().distinct().count()!=x.length()).toList()); // better than above,  print the strings containing duplicate characters
        System.out.println(e.stream().collect(Collectors.joining(",")));
        System.out.println(e.stream().sorted(Comparator.naturalOrder()).toList());
        System.out.println(e.stream().reduce("",String::concat));
        System.out.println(e.stream().reduce((a,b)->a.length()>b.length()?a:b)); // find the string with the maximum length
        System.out.println(e.stream().filter(x->x.contains("a")).count());
        System.out.println(e.stream().filter(str->str.matches("AEIOUaeiou")).toList());// print the strings containing only vowels
        System.out.println(e.stream().distinct().toList()); //  remove all the duplicate elements
        System.out.println(e.stream().map(String::toUpperCase).toList());
        System.out.println(e.stream().reduce((a,b)->a.length()>b.length()?b:a)); // find the string with the minimum length
        Collections.reverse(e);
        System.out.println(e); // reverse a list
        // Given a list of strings, write a program to find and print the strings with the maximum number of vowels
        Map<String, Long> StringToVowelCountMap = e.stream().collect(
                Collectors.toMap(
                        str->str, // KeyMapper
                        str->str.chars().filter(x->"AEIOUaeiou".indexOf(x)!=-1).count(), // ValueMapper
                        (val1,val2)->val1 // Combiner -> use this to avoid duplicates
                )); // If you don't add combiner then yuo will get, Duplicate key as (attempted merging values 1 and 1) when you have duplicate elements in list

        System.out.println(StringToVowelCountMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey());
        // write a program to find and print the strings containing a specific character at least twice
        Map<String, Boolean> stringToBooleanMap = e.stream().collect(Collectors.toMap(x->x,x->x.chars().distinct().count()!=x.length(),(v1,v2)->v1));
        System.out.println(stringToBooleanMap.entrySet().stream().filter(entry-> !entry.getValue()).map(Map.Entry::getKey).toList());
        // write a program to find and print the strings with the maximum number of consonants

        // if a list of strings is palindrome
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "banana", "apple");
        boolean isPalindrome = strings.stream()
                .skip(strings.size() / 2)
                .allMatch(s -> s.equals(strings.get(strings.size() - 1 - strings.indexOf(s))));
        System.out.println("Is the list a palindrome? " + isPalindrome);
        // remove all non-numeric characters from a list
        // write a program to find and print the strings containing only digits


        Map<String, Map<Integer, List<String>>> ans1 = e.stream().sorted(Comparator.naturalOrder()).collect(Collectors.groupingBy(i -> i, Collectors.groupingBy(i -> i.length())));
        System.out.println(e);

    }

    private static boolean containsDuplicateCharacters(String x) {
        Map<Character, Integer> hash = new HashMap<>();
        for(int i=0;i<x.length();i++) {
            if(hash.getOrDefault(x.charAt(i),0)+1>1)
                return false;
            hash.put(x.charAt(i),hash.getOrDefault(x.charAt(i),0)+1);
        }
        return true;
    }


}
