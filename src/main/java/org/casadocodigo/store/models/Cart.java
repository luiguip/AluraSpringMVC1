package org.casadocodigo.store.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION,
			proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable{

	private static final long serialVersionUID = 8712975602208801952L;
	
	private Map<CartItem, Integer> itens = new LinkedHashMap<>();
	
	public Collection<CartItem> getItens() {
		return itens.keySet();
	}

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
	
	public BigDecimal getTotal(CartItem cartItem) {
		return cartItem.getTotal(getQuantity());
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartItem cartItem : itens.keySet()) {
			total = total.add(getTotal(cartItem));
		}
		return total;
	}

	public void remove(Integer productId, PriceType priceType) {
		Product product = new Product();
		product.setId(productId);
		
		itens.remove(new CartItem(product, priceType));
	}
}
