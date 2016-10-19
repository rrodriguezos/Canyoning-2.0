package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.OrganiserService;
import domain.Activity;
import domain.Organiser;

@Controller
@RequestMapping("/organiser")
public class OrganiserController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OrganiserService organiserService;
	@Autowired
	private ActivityService activityService;

	// Constructors -----------------------------------------------------------

	public OrganiserController() {
		super();
	}

	// List -----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Organiser> organisers;


		organisers = organiserService.findAll();

		

		result = new ModelAndView("organiser/list");
		result.addObject("organisers", organisers);

		result.addObject("requestURI", "organiser/list.do");

		return result;
	}
	
	// Display -----------------------------------------------------------------
		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView display(int organiserId) {
			ModelAndView result;
			Organiser organiser;
			Collection<Activity> activities;

			organiser = organiserService.findOne(organiserId);
			activities = activityService.findActivitiesByOrganiser(organiserId);

			result = new ModelAndView("organiser/display");
			result.addObject("organiser", organiser);
			result.addObject("activities", activities);

			return result;
		}


}
