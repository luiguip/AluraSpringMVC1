package org.casadocodigo.store.controllers;

import java.util.List;

import org.casadocodigo.store.daos.ProductDAO;
import org.casadocodigo.store.models.Product;
import org.casadocodigo.store.models.PriceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produtos")
public class ProductController {
	
	@Autowired
	ProductDAO productDAO;

	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("types", PriceType.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(Product product) {
		productDAO.save(product);
		
		return "produtos/ok";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listing() {
		List<Product> productList = productDAO.listing();
		ModelAndView modelAndView = new ModelAndView("produtos/list");
		modelAndView.addObject("products",productList);
		return modelAndView;
	}

}
