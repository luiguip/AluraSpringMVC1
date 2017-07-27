package org.casadocodigo.store.controllers;

import org.casadocodigo.store.daos.ProductDAO;
import org.casadocodigo.store.models.Cart;
import org.casadocodigo.store.models.CartItem;
import org.casadocodigo.store.models.PriceType;
import org.casadocodigo.store.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private Cart cart;

	@RequestMapping("/add")
	public ModelAndView add(Integer productId, PriceType priceType) {
		ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
		CartItem cartItem = createItem(productId,priceType);
		cart.add(cartItem);
		return modelAndView;
	}
	
	private CartItem createItem(Integer productId, PriceType priceType) {
		Product product = productDAO.findById(productId);
		CartItem cartItem = new CartItem(product, priceType);
		return cartItem;
	}
	
}
