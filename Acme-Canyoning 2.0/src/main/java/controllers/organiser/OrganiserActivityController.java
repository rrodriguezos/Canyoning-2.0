package controllers.organiser;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CanyonService;
import services.OrganiserService;
import services.RequestService;
import controllers.AbstractController;
import domain.Activity;
import domain.Canyon;
import domain.Organiser;

@Controller
@RequestMapping("/activity/organiser")
public class OrganiserActivityController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private OrganiserService organiserService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CanyonService canyonService;

	// Constructors -----------------------------------------------------------
	public OrganiserActivityController() {
		super();
	}

	// List -------------------------------------------------------------------
	@RequestMapping("/mylist")
	public ModelAndView list() {
		ModelAndView result;
		Collection<Activity> activities;
		Boolean myActivityOrganiser;

		activities = activityService.findActivitiesByOrganiser();
		myActivityOrganiser = true;
		result = new ModelAndView("activity/list");
		result.addObject("requestUri", "/activity/mylist.do");
		result.addObject("activities", activities);
		result.addObject("myActivityOrganiser", myActivityOrganiser);

		return result;
	}

	// Create---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Activity activity;
		Collection<Canyon> canyons;

		activity = activityService.create();
		canyons = canyonService.findAll();

		result = new ModelAndView("activity/create");
		result.addObject("activity", activity);
		result.addObject("canyons", canyons);

		return result;
	}

	// Edit
	// -------------------------------------------------------------------------
	@RequestMapping(value = "/reinstantiate", method = RequestMethod.GET)
	public ModelAndView reinstantiate(@RequestParam int activityId) {
		ModelAndView result;
		Activity activity;

		activity = activityService.findOne(activityId);

		result = new ModelAndView("activity/organiser/reinstantiate");
		result.addObject("activity", activity);

		return result;
	}

	// Save -------------------------------------------------------------------
	@RequestMapping(value = "/reinstantiate", method = RequestMethod.POST, params = "reinstantiate")
	public ModelAndView edit(@Valid Activity activity, BindingResult binding) {
		ModelAndView result;
		Collection<Canyon> canyons;
		Organiser organiser;
		canyons = canyonService.findAll();
		if (binding.hasErrors()) {
			System.out.print(binding.getFieldError());
			System.out.print(binding.getAllErrors());
			canyons = canyonService.findAll();

			result = new ModelAndView("activity/organiser/reinstantiate");
			result.addObject("activity", activity);
			result.addObject("canyons", canyons);
		} else {
			try {
				organiser = organiserService.findByPrincipal();
				activityService.reinstantiateMomentActivity(activity, organiser);
				result = new ModelAndView("redirect:/activity/organiser/mylist.do");
			} catch (Throwable oops) {
				canyons = canyonService.findAll();

				result = new ModelAndView("activity/organiser/reinstantiate");
				result.addObject("activity", activity);
				result.addObject("canyons", canyons);
				result.addObject("message2", "activity.commit.error");
			}
		}
		return result;
	}

	// reinstantiate
	// Activity---------------------------------------------------------------------
//	@RequestMapping(value = "/reinstantiate", method = RequestMethod.GET)
//	public ModelAndView reinstantiate(@RequestParam int activityId) {
//		ModelAndView result;
//		Activity activity;
//		Organiser organiser;
//		
//		activity = activityService.findOne(activityId);
//		organiser = organiserService.findByPrincipal();
//
//		activityService.reinstantiateMomentActivity(activity, organiser);
//
//		result = new ModelAndView("redirect:/activity/organiser/mylist.do");
//		result.addObject("requestUri", "/activity/organiser/mylist.do");
//
//		return result;
//	}

	// Ancillary methods
	// --------------------------------------------------------

	protected ModelAndView createEditModelAndView(Activity activity) {
		ModelAndView result;

		result = createEditModelAndView(activity, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Activity activity, String message) {
		ModelAndView result;

		result = new ModelAndView("activity/organiser/reinstantiate");
		result.addObject("activity", activity);
		result.addObject("message2", message);

		return result;
	}
}
