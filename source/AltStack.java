///////////////////////////////////////////////////////////
/*
 * File Name:       AltStack.java
 * Instructor:      Prof. Benjamin Goldberg
 *                  & Prof. Robert Grimm
 * Author:          Shen Li
 * UID:             N14361265
 * Department:      Computer Science
 * Note:            This file implements AltStack class.
 */
///////////////////////////////////////////////////////////

//////////Import Packet//////////
import java.util.Iterator;
import java.util.NoSuchElementException;

//////////Class Definition//////////
/*Public Class AltStack*/
public class AltStack{
    Element top;    //top element

    //Constructor
    public AltStack(){
        top = null;
    }
    //Push member function
    public AltStack push(Object value){
        top = new Element(value, top);
        return this;
    }
    //Peek member function
    public Object peek(){
        if (null == top){
            throw new NoSuchElementException();
        }
        return top.value;
    }
    //Pop member function
    public Object pop(){
        if (null == top){
            throw new NoSuchElementException();
        }
        Object  result = top.value;
        top = top.next;
        return result;
    }
    //Iterator member function
    public StackIterator iterator(){
        return new StackIterator(this);
    }

    //Main Function
    public static void main(String[] args){
        AltStack    s = new AltStack();     //AltStack

        //Push element into AltStack
        s.push(3).push(2).push(1);
        //Output element in Stack
        for (StackIterator i = s.iterator(); i.hasNext();){
            int element = (Integer)i.next();
            System.out.println(element);
        }
        System.out.println();

        StackIterator   iter = s.iterator();    //Iterator
        //Find next element
        iter.next();
        iter.next();
        //Remove element
        iter.remove();
        //Output element in StackIterator
        for (StackIterator i = s.iterator(); i.hasNext();){
            int element = (Integer)i.next();
            System.out.println(element);
        }
    }
}

/*Class Element*/
class Element{
    Object  value;      //object value
    Element next;       //next element

    //Constructor
    Element(Object value, Element next){
        this.value = value;
        this.next = next;
    }
}

/*Class Stack Iterator*/
class StackIterator implements Iterator{
    private AltStack    stack;      //stack
    private Element     previous;   //previous element
    private Element     current;    //current element
    private Element     next;       //next element

    //Constructor
    public StackIterator(){
        previous = null;
        current = null;
        next = null;
        stack = null;
    }
    public StackIterator(AltStack stack){
        previous = null;
        current = null;
        next = stack.top;
        this.stack = stack;
    }

    //Override hasNext() function
    public boolean hasNext(){
        return null != next;
    }
    //Override next() function
    public Object next(){
        //No next element
        if (null == next){
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
    //Override remove() element
    public void remove(){
        //Empty
        if (null == current){
            //throw Illegal State Exception
            throw new IllegalStateException();
        }
        //Update top element
        if (null == previous){
            stack.top = next;
        }
        else{
            previous.next = next;
        }
        //Update current element
        current = null;
    }
}
