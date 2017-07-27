package org.casadocodigo.store.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Cart {

	private Map<CartItem, Integer> itens = new LinkedHashMap<>();
	
	public void add(CartItem cartItem) {
		itens.put(cartItem, getQuantity(cartItem)+1);
	}
	
	private int getQuantity(CartItem cartItem) {
		if(!itens.containsKey(cartItem)) {
			itens.put(cartItem,0);
		}
			return itens.get(cartItem);
	}
	
	public int getQuantity() {
		return itens.values().stream().reduce(0,
				(next, acumulator)->(next + acumulator));
	}
	
}
