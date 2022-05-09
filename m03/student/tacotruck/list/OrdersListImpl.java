package com.olympic.cis143.m03.student.tacotruck.list;

import java.util.ArrayList;

import com.olympic.cis143.m03.student.tacotruck.Orders;
import com.olympic.cis143.m03.student.tacotruck.TacoImpl;

public class OrdersListImpl implements Orders
{
	private ArrayList<TacoImpl> tacoQueue = new ArrayList<>();
	int orderNumber = 0;
	public void addOrder(TacoImpl tacoOrder)
	{
		tacoQueue.add(tacoOrder);
		orderNumber++;
	}

	public boolean hasNext()
	{
		return !tacoQueue.isEmpty();
	}

	public TacoImpl closeNextOrder()
	{
		TacoImpl temp = tacoQueue.get(0);
		tacoQueue.remove(0);
		return temp;
	}

	public int howManyOrders()
	{
		return tacoQueue.size();
	}

}
