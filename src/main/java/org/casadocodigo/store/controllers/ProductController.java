package org.casadocodigo.store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.casadocodigo.store.daos.ProductDAO;
import org.casadocodigo.store.infra.FileSaver;
import org.casadocodigo.store.models.Product;
import org.casadocodigo.store.validators.ProductValidation;
import org.casadocodigo.store.models.PriceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProductController {
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProductValidation());
		
	}

	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("types", PriceType.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


		if(bindingResult.hasErrors()){
			return form(product);
		}
		
		String path = fileSaver.write("summary-archives", summary);
		product.setSummaryPath(path);

		productDAO.save(product);
		redirectAttributes.addFlashAttribute("success","product registered with success");

		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listing() {
		List<Product> productList = productDAO.listing();
		ModelAndView modelAndView = new ModelAndView("produtos/list");
		modelAndView.addObject("products",productList);
		return modelAndView;
	}
	
	@RequestMapping("/info/{id}")
	public ModelAndView info(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("produtos/info");
		
		Product product = productDAO.findById(id);
		modelAndView.addObject("product", product);
		
		return modelAndView;
		
	}

}
