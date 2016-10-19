package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import controllers.AbstractController;
import domain.Request;

@Controller
@RequestMapping("/request/customer")
public class CustomerRequestController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private RequestService requestService;

	// Constructors -----------------------------------------------------------
	public CustomerRequestController() {
		super();
	}

	// ListAccepted ---------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAccepted() {
		ModelAndView result;
		Boolean accepted = true;
		Collection<Request> requests = requestService
				.requestByCustomer();
		result = new ModelAndView("request/customer/list");
		result.addObject("requests", requests);
		result.addObject("accepted", accepted);
		result.addObject("requestURI", "request/customer/list.do");
		return result;
	}

	
	
}
