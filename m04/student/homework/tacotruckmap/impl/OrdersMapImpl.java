package com.olympic.cis143.m04.student.homework.tacotruckmap.impl;

import com.olympic.cis143.m04.student.homework.tacotruckmap.OrderDoesNotExistException;
import com.olympic.cis143.m04.student.homework.tacotruckmap.Orders;
import com.olympic.cis143.m04.student.homework.tacotruckmap.TacoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersMapImpl implements Orders {
	HashMap<String , ArrayList<TacoImpl>> map = new HashMap<String , ArrayList<TacoImpl>>();

    public void createOrder(final String orderid) {
    	ArrayList<TacoImpl> order = new ArrayList<TacoImpl>();
    	map.put(orderid, order);
    }

    @Override
    public void addTacoToOrder(final String orderid, final TacoImpl taco) throws OrderDoesNotExistException {
    	if(map.containsKey(orderid)) {
    		map.get(orderid).add(taco);
    	}
    	else throw new OrderDoesNotExistException("There is no order for " + orderid);
    }

    @Override
    public boolean hasNext() {
        return !map.isEmpty();
    }

    @Override
    public List<TacoImpl> closeOrder(final String orderid) throws OrderDoesNotExistException {
        if(map.containsKey(orderid)) {
	    	ArrayList<TacoImpl> taco = map.get(orderid);
	    	map.remove(orderid);
	    	return taco;
        }
        else throw new OrderDoesNotExistException("There is no order for " + orderid);
    }

    @Override
    public int howManyOrders() {
        return map.size();
    }

    @Override
    public List<TacoImpl> getListOfOrders(final String orderid) throws OrderDoesNotExistException {
        if(map.containsKey(orderid)) {
        	return map.get(orderid);
        }
        else throw new OrderDoesNotExistException("There is no order for " + orderid);
    }
}