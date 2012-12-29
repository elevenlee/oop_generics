oop_generics
============

Java collections are supposed to be "fail fast". If you iterate over the elements of a collection and, while iterating, also modify the collection through another method (i.e., not the iterator's remove() method), the next iteration should signal a java.util.ConcurrentModificationException.


1. The code was written under the Ubuntu Linux System (Version 11.10)
2. The Compiler version is GCC 4.6.1
3. The format of compiling source code is as below:

    javac Stack.java
    javac AltStack.java

4. The format of running source code is as below:

    java Stack
    java AltStack
