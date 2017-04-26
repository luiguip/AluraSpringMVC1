package org.casadocodigo.store.controllers;

import org.casadocodigo.store.daos.ProductDAO;
import org.casadocodigo.store.models.Product;
import org.casadocodigo.store.models.PriceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	
	@Autowired
	ProductDAO productDAO;

	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("types", PriceType.values());
		return modelAndView;
	}
	
	@RequestMapping("/produtos")
	public String save(Product product) {
		productDAO.save(product);
		
		return "produtos/ok";
	}
	

}
