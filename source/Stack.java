///////////////////////////////////////////////////////////
/*
 * File Name:       Stack.java
 * Instructor:      Prof. Benjamin Goldberg
 *                  & Prof. Robert Grimm
 * Author:          Shen Li
 * UID:             N14361265
 * Department:      Computer Science
 * Note:            This file implements Stack class.
 */
///////////////////////////////////////////////////////////

//////////Import Packet//////////
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

//////////Class Definition//////////
/*Public Class Stack<E>*/
public class Stack<E> implements Iterable<E>{
    //Inner class Element<E>
    private static class Element<E>{
        E   value;          //value
        Element<E>  next;   //next element

        //Constructor
        Element(E value, Element<E> next){
            this.value = value;
            this.next =next;
        }
    }

    //Private class member
    private Element<E> top;     //top element
    private int modCount;       //modified count

    //Constructor
    public Stack(){
        top = null;
        modCount = 0;
    }
    //Push member function
    public Stack<E> push(E value){
        //Create new element
        top = new Element<E>(value, top);
        //Update modCount
        modCount++;
        return this;
    }
    //Peek member function
    public E peek(){
        if (null == top){
            throw new NoSuchElementException();
        }
        return top.value;
    }
    //Pop member function
    public E pop(){
        if (null == top){
            throw new NoSuchElementException();
        }

        E   result = top.value;     //top element
        //Update top element
        top = top.next;
        //Update modCount
        modCount++;
        return result;
    }

    //Inner Iterator<E>
    public Iterator<E> iterator(){
        return new Iterator<E>(){
            Element<E>  previous = null;        //previous element
            Element<E>  current = null;         //current element
            Element<E>  next = top;             //next element
            int exptectedModCount = modCount;   //Initialize exptected modified count

            //Check concurrent modification function
            final void checkCoModification(){
                if (modCount != exptectedModCount){
                    //throw Concurrent Modification Exception
                    throw new ConcurrentModificationException();
                }
            }
            //Override hasNext() function
            public boolean hasNext(){
                return null != next;
            }
            //Override next() function
            public E next(){
                //Check Concurrent Modification
                checkCoModification();
                //No next element
                if (null == next){
                    //Check Concurrent Modification
                    checkCoModification();
                    //throw No Such Element Exception
                    throw new NoSuchElementException();
                }
                //Set previous element
                if (null != current){
                    previous = current;
                }
                //Update current element
                current = next;
                //Update next element
                next = next.next;
                return current.value;
            }
            //Override remove() function
            public void remove(){
                //Empty
                if (null == current){
                    //throw Illegal State Execption
                    throw new IllegalStateException();
                }
                //Check Concurrent Modification
                checkCoModification();
                try {
                    //Update top element
                    if (null == previous){
                        top = next;
                    }
                    else{
                        previous.next = next;
                    }
                    //Update current element
                    current = null;
                    //Update exptectModCount
                    exptectedModCount = modCount;
                }catch (IndexOutOfBoundsException e){
                    //throw Concurrent Modification Exception
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    //Main Function
    public static void main(String[] args){
        Stack<Integer>  s = new Stack<Integer>();   //Stack

        //Push element into Stack
        s.push(3).push(2).push(1);
        //Output element in Stack
        for (Integer i : s){
            System.out.println(i - 1);
        }
        System.out.println();

        Iterator<Integer>   iter = s.iterator();    //Iterator
        //Find next element
        iter.next();
        System.out.println("iter.next() Success!");
        iter.next();
        System.out.println("iter.next() Success!");
        //Remove element
        iter.remove();
        System.out.println("iter.remove() Success!");
        //Output element in Iterator
        for (Integer i : s){
            System.out.println("i.next() Success!");
            //Check Concurrent Modification Exception
            s.push(4);
            System.out.println(i);
        }
    }
}
