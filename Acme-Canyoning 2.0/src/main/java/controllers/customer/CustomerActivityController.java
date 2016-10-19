package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.AdministratorService;
import services.CommentService;
import services.CustomerService;
import services.RequestService;
import controllers.AbstractController;
import domain.Activity;
import domain.Customer;
import domain.Request;

@Controller
@RequestMapping("/activity/customer")
public class CustomerActivityController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private CustomerService customerSService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private RequestService requestService;

	// Constructors -----------------------------------------------------------
	public CustomerActivityController() {
		super();
	}

	// MyList ---------------------------------------
	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Boolean mylist = true;
		int customerId = customerSService.findByPrincipal().getId();
		Collection<Activity> activities = activityService
				.allActivitiesByCustomerRequest(customerId);

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("mylist", mylist);
		result.addObject("requestURI", "activity/customer/mylist.do");
		return result;
	}

	// AllListing ---------------------------------------
	@RequestMapping(value = "/mylistAccepted", method = RequestMethod.GET)
	public ModelAndView listAllAccepted() {
		ModelAndView result;
		Boolean accepted = true;
		int customerId = customerSService.findByPrincipal().getId();
		Collection<Activity> activities = activityService
				.activitiesAcceptedByRequestCustomer(customerId);
		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("accepted", accepted);
		result.addObject("requestURI", "activity/customer/mylistAccepted.do");
		return result;
	}

	// AllListing ---------------------------------------
	@RequestMapping(value = "/mylistReject", method = RequestMethod.GET)
	public ModelAndView listAllReject() {
		ModelAndView result;
		Boolean reject = true;
		int customerId = customerSService.findByPrincipal().getId();
		Collection<Activity> activities = activityService
				.activitiesRejectByRequestCustomer(customerId);
		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("reject", reject);
		result.addObject("requestURI", "activity/customer/mylistReject.do");
		return result;
	}

	// AllListing -----------------------------------------------------------------
	@RequestMapping(value = "/mylistPending", method = RequestMethod.GET)
	public ModelAndView listAllPending() {
		ModelAndView result;
		Boolean pending = true;
		int customerId = customerSService.findByPrincipal().getId();
		Collection<Activity> activities = activityService
				.activitiesPendingByRequestCustomer(customerId);
		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("pending", pending);
		result.addObject("requestURI", "activity/customer/mylistPending.do");
		return result;
	}
	
	
	// Request an
		// Activity------------------------------------------------------------------
		@RequestMapping(value = "/requestActivity", method = RequestMethod.GET)
		public ModelAndView requestActivity(@RequestParam int activityId) {

			ModelAndView result;
			Customer customer;
			Request request;
			customer = customerSService.findByPrincipal();
			Collection<Activity> activities;	
			
			request = requestService.requestActivityByCustomer(activityId,customer);	
			requestService.save(request);
			activities = activityService.activitiesPendingByRequestCustomer(customer.getId());

			result = new ModelAndView("redirect:/activity/customer/mylistPending.do");
			result.addObject("activities", activities);
			result.addObject("requestURI", "activity/customer/mylist.do");

			return result;

		}

	

}
