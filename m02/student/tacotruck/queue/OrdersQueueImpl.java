package com.olympic.cis143.m02.student.tacotruck.queue;

import com.olympic.cis143.m02.student.tacotruck.TacoImpl;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Reference the following when working on the queue implementation:
 * https://www.baeldung.com/java-array-deque#2-arraydeque-as-a-queue
 *
 * Note that the orders are a queue, so its FIFO approach.
 */
public class OrdersQueueImpl implements Orders{

    /**
     * This is the queue that will hold and manage the TacoImpl
     */
    private Deque<TacoImpl> tacoQueue = new ArrayDeque<>();

    /**
     *
     * @param tacoOrder
     */
    public void addOrder(TacoImpl tacoOrder) {
    	tacoQueue.addLast(tacoOrder);
    }
    	
    /**
     * True if more tacos remain, else false.
     * @return
     */
    public boolean hasNext() {
    	if(tacoQueue.isEmpty()) {
    		return false;
    	}
    	else{
    		return true;
    	}
    }

    /**
     * Note that this method will remove the first (in terms of FIFO)taco from the tacoQueue and return it.
     *
     * Once returned it should be permanently removed.
     *
     * Outcome
     * ========
     * It will remove and return the FIFO record. This is tested in the test file.
     *
     * @return The taco.
     */
    public TacoImpl closeNextOrder() {
    	TacoImpl removedTaco = tacoQueue.getFirst();
    	tacoQueue.removeFirst();
    	return removedTaco;
    }

    /**
     * How many orders remain in the tacoQueue
     *
     * @return How many orders are in the queue
     */
    public int howManyOrders() {
      return tacoQueue.size();
    }
}
