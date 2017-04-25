package org.casadocodigo.store.controllers;

import org.casadocodigo.store.daos.ProdutoDAO;
import org.casadocodigo.store.models.Produto;
import org.casadocodigo.store.models.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutosController {
	
	@Autowired
	ProdutoDAO produtoDAO;

	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@RequestMapping("/produtos")
	public String grava(Produto produto) {
		produtoDAO.grava(produto);
		
		return "produtos/ok";
	}
	

}
