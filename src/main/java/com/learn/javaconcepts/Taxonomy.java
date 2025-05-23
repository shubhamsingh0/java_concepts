//package com.learn.javaconcepts;
//
//import java.util.List;
//
//
//// External class trying to use Person class fields directly
//public class Taxonomy {
//
//    public static void main(String[] args) {
//        Singer parentRefToItsOwnObject = new Singer();
//        parentRefToItsOwnObject.perform(); // allowed. O/P: called Parent Class: Sing like a bird
//
//        // upcasting
//        Singer parentRefToChildObject = new Artist();
//        parentRefToChildObject.perform(); // allowed. O/P: called subclass: Will sing as per my wish
//        List<String> requestedSongs = List.of("song1", "song2");
//        parentRefToChildObject.perform(requestedSongs); // not allowed. O/P: Compile-time error. Parent cannot call
//                                                      // child class method. The method perform(List<String>) is
//                                                      // not defined in the parent class (Singer), so it cannot be
//                                                      // called on a reference of type Singer.
//
//        Artist childRefToItsOwnObject = new Artist();
//        childRefToItsOwnObject.perform(); // allowed. O/P: called subclass: Will sing as per my wish
//
//        Artist childRefToParentObject = new Singer();
//        childRefToParentObject.perform(); // not allowed. O/P: Compile-time error. Incompatible types.
//                                          // The error occurs because Java does not allow assigning a
//                                          // parent class object (Singer) to a reference of its child
//                                          // class (Artist). This violates the "is-a" relationship in
//                                          // inheritance. A Singer might not have the additional
//                                          // properties or methods of an Artist, so the assignment is incompatible.
//
//    }
//}
