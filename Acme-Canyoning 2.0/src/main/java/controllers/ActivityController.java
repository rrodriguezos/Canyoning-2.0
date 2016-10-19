package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.AdministratorService;
import services.CanyonService;
import services.CommentService;
import services.CustomerService;
import services.OrganiserService;
import services.RequestService;
import domain.Activity;
import domain.Administrator;
import domain.Canyon;
import domain.Comment;
import domain.Customer;
import domain.Organiser;
import domain.Request;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CanyonService canyonService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private RequestService requestService;
	@Autowired
	private OrganiserService organiserService;

	// Constructors -----------------------------------------------------------
	public ActivityController() {
		super();
	}

	// List
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Activity> activities;
		activities = activityService.findAll();

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("requestUri", "/activity/list.do");

		return result;
	}

	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int activityId) {
		ModelAndView result;
		Customer customer;
		Organiser organiser;
		Activity activity;
		Boolean myActivity;
		Boolean myActivityOrganiser;
		Boolean logeado;
		activity = activityService.findOne(activityId);
		myActivityOrganiser = false;
		myActivity = false;
		logeado = false;
		Collection<Comment> comments;
		Collection<Request> requestCustomer;
		try {
			organiser = organiserService.findByPrincipal();
			if (activity.getOrganiser().getId() == organiser.getId()) {
				myActivityOrganiser = true;
			}
		} catch (Throwable oops) {
			myActivityOrganiser = false;
		}
		try {
			activity = activityService.findOne(activityId);
			requestCustomer = requestService.requestByCustomer();
			customer = customerService.findByPrincipal();
			if (customer != null) {
				logeado = true;
			}

			for (Request r : requestCustomer) {
				Activity activityRequest = r.getActivity();

				if (activityRequest.equals(activity)) {
					myActivity = true;
				}
			}
		} catch (Throwable oops) {
			myActivity = false;
			logeado = false;
		}

		comments = commentService.findCommentsByCommentableId(activityId);

		activity = activityService.findOne(activityId);
		comments = commentService.findCommentsByCommentableId(activityId);

		result = new ModelAndView("activity/display");
		result.addObject("activity", activity);
		result.addObject("myActivity", myActivity);
		result.addObject("myActivityOrganiser", myActivityOrganiser);
		result.addObject("logeado", logeado);
		result.addObject("comments", comments);
		return result;
	}

	// ListByCanyon
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/listByCanyon")
	public ModelAndView listByCanyon(@RequestParam int canyonId) {

		ModelAndView result;
		Collection<Activity> activities;
		Canyon canyon;
		Administrator administrator;
		Boolean mycanyon;
		activities = activityService.activitiesByCanyon(canyonId);
		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("canyonId", canyonId);
		mycanyon = false;
		try {
			administrator = administratorService.findByPrincipal();
			canyon = canyonService.findOne(canyonId);

			mycanyon = administrator.equals(canyon.getAdministrator());

		} catch (Throwable oops) {

		}
		result.addObject("myactivity", mycanyon);

		return result;
	}

	// ListByRequest
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/listByRequest", method = RequestMethod.GET)
	public ModelAndView navigateByRequest(@RequestParam int requestId) {
		ModelAndView result;
		Activity activity = activityService.activityByRequest(requestId);

		result = new ModelAndView("activity/list");
		result.addObject("activities", activity);
		result.addObject("requestURI", "activity/listByRequest.do");
		return result;
	}

}
