oop_generics
============

Java collections are supposed to be "fail fast". If you iterate over the elements of a collection and, while iterating, also modify the collection through another method (i.e., not the iterator's remove() method), the next iteration should signal a java.util.ConcurrentModificationException.