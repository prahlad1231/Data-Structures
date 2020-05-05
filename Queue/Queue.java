/*
   May 2, 2020
   Author: Er. Prahlad Panthi
*/

package datastructures;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue <T> implements Iterable <T> {

    private LinkedList <T> queue = new LinkedList<>();

    public Queue() {}

    public Queue(T firstElement) {
        if (firstElement == null)
            throw new RuntimeException("Cannot add null");
        offer(firstElement);
    }

    // returns size of the queue
    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // returns the first element from the queue
    public T peek() {
        if (isEmpty())
            throw new RuntimeException("Empty Queue");
        return queue.getFirst();
    }

    // removes and returns the first element from the queue
    public T poll() {
        if (isEmpty())
            throw new RuntimeException("Empty Queue");
        return queue.removeFirst();
    }

    public void offer(T element) {
        if (element == null)
            throw new RuntimeException("Cannot add null");
        queue.addLast(element);
    }

    // returns the iterator for the queue
    @Override
    public Iterator<T> iterator() {
        return queue.iterator();
    }
}
