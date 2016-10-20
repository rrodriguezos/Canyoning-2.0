package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CustomerService;
import services.OrganiserService;
import services.RequestService;

import com.mchange.v1.cachedstore.CachedStore.Manager;

import controllers.AbstractController;
import domain.Activity;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {
	
	// Services -----------------------
		@Autowired
		private CustomerService customerService;
		@Autowired
		private OrganiserService organiserService;
		@Autowired
		private ActivityService activityService;
		@Autowired
		private RequestService requestService;


		// Constructor --------------------
		public DashboardAdministratorController() {
			super();
		}

		// Listing ------------------------
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;

			// The average number of activities per organiser.
			Double averageActivitiesPerOrganiser = activityService.averageNumberOfActivitiesByOrganisers();
			// The average number of customers in the waiting lists.
//			Double averageCustomersInWaitingList = customerService.averageCustomersInWaitingList();

		


			// 10% more than the average.
			Collection<Activity> activity10MoreAverage = activityService.findWithMoreTenPercentOfSeatsAvg();

 
			// 10% less than the average.
			Collection<Activity> activity10LessAverage = activityService.findWithLessTenPercentOfSeatsAvg();

			result = new ModelAndView("administrator/dashboard");
			result.addObject("averageActivitiesPerOrganiser", averageActivitiesPerOrganiser);
//			result.addObject("averageCustomersInWaitingList", averageCustomersInWaitingList);


			result.addObject("activity10MoreAverage", activity10MoreAverage);
			result.addObject("activity10LessAverage", activity10LessAverage);

			return result;
		}

}
