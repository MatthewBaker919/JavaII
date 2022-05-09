package com.olympic.cis143.m04.student.tacotruck.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.olympic.cis143.m04.student.tacotruck.Orders;
import com.olympic.cis143.m04.student.tacotruck.TacoImpl;

public class OrdersSetImpl  implements Orders {
	Set<TacoImpl> set = new HashSet<>();
	
    public void addOrder(TacoImpl tacoOrder) {
    	set.add(tacoOrder);
    }

    public boolean hasNext() {
        if(!set.isEmpty()) return true;
        return false;
    }

    public TacoImpl closeNextOrder() {    	
		if(hasNext()) {
			Object[] temp = set.toArray();
			Object taco = temp[temp.length-1];
			set.remove(taco);
			return (TacoImpl) taco; //The cast reminds the taco that it is indeed, a taco and therefore delicious
		}
		else {
			throw new RuntimeException("No such element");
		}
    }

    public int howManyOrders() {
        return set.size();
    }
}