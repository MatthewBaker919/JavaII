package com.olympic.cis143.m02.student.tacotruck.queue;

import com.olympic.cis143.m02.student.tacotruck.TacoImpl;

public interface Orders
{
	 public void addOrder(TacoImpl tacoOrder);
	 public boolean hasNext();
	 public TacoImpl closeNextOrder();
	 public int howManyOrders();
}
