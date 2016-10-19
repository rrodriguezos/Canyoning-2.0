package controllers.organiser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.OrganiserService;
import services.RequestService;
import controllers.AbstractController;
import domain.Activity;
import domain.Customer;
import domain.Request;

@Controller
@RequestMapping("/request/organiser")
public class OrganiserRequestController extends AbstractController {
	// Supporting services ----------------------------------------------------
	@Autowired
	private OrganiserService organiserService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private RequestService requestService;

	// Constructors -----------------------------------------------------------
	public OrganiserRequestController() {
		super();
	}

	// List -------------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam int activityId) {
		ModelAndView result;
		Collection<Request> requests;
		Activity activity;
		Customer customer = null;

		requests = requestService.requestsPendingByActivity(activityId);
		for (Request r : requests) {
			customer = r.getCustomer();
		}
		activity = activityService.findOne(activityId);
		result = new ModelAndView("request/organiser/list");
		result.addObject("requests", requests);
		result.addObject("activity", activity);
		result.addObject("activityId", activityId);
		result.addObject("customer", customer);

		return result;
	}
	
	// Accept Request------------------------------------------------------------------
		@RequestMapping(value = "/accept", method = RequestMethod.GET)
		public ModelAndView accept(@RequestParam int requestId) {

			ModelAndView result;
			Request request;
			boolean accept; 

			request = requestService.findOne(requestId);
			accept = requestService.acceptRequest(request.getId());

			result = new ModelAndView("request/organiser/list");

			result.addObject("request", request);
			result.addObject("accept", accept);
			result.addObject("requestURI", "request/organiser/list.do");

			return result;

		}
		
		// Accept Request------------------------------------------------------------------
				@RequestMapping(value = "/reject", method = RequestMethod.GET)
				public ModelAndView reject(@RequestParam int requestId) {

					ModelAndView result;
					Request request;
					boolean accept; 

					request = requestService.findOne(requestId);
					accept = requestService.rejectRequest(request.getId());

					result = new ModelAndView("request/organiser/list");

					result.addObject("request", request);
					result.addObject("accept", accept);
					result.addObject("requestURI", "request/organiser/list.do");

					return result;

				}
}
