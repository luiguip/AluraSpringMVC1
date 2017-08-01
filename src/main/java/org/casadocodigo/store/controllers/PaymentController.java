package org.casadocodigo.store.controllers;

import java.util.concurrent.Callable;

import org.casadocodigo.store.models.Cart;
import org.casadocodigo.store.models.PaymentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private Cart cart;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/tofinalize", method=RequestMethod.POST)
	public Callable<ModelAndView> toFinalize(RedirectAttributes redirectAttributes) {
		System.out.println(cart.getTotal());
		return() -> {
			try {
				String uri = "http://book-payment.herokuapp.com/payment";
				String response = restTemplate.postForObject(uri, new PaymentData(cart.getTotal()), String.class);
				redirectAttributes.addFlashAttribute("success", response);
				return new ModelAndView("redirect:/produtos");
			} catch( HttpClientErrorException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("fail", "Value exceeds");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}
}
