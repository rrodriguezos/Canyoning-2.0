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
import services.PieceEquipmentService;
import controllers.AbstractController;
import domain.Activity;
import domain.Canyon;
import domain.Organiser;
import domain.PieceEquipment;
import forms.ActivityForm;

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
	@Autowired
	private PieceEquipmentService pieceEquipmentService;

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

		ActivityForm af = new ActivityForm();
		ModelAndView result = createEditModelAndViewForm(af);
		Collection<Canyon> canyons;
		Collection<PieceEquipment> pieceEquipments;

		activityService.create();

		canyons = canyonService.findAll();
		pieceEquipments = pieceEquipmentService.findAll();

		//
		// result = new ModelAndView("activity/create");
		// result.addObject("activityForm", result);
		result.addObject("canyons", canyons);
		result.addObject("pieceEquipments", pieceEquipments);

		return result;
	}

	// Edit
	// -------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int activityId) {
		ModelAndView result;
		Activity activity;
		Collection<Canyon> canyons;

		activity = activityService.findOne(activityId);
		canyons = canyonService.findAll();

		result = new ModelAndView("activity/edit");
		result.addObject("activity", activity);
		result.addObject("canyons", canyons);

		return result;
	}

	// Save -------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ActivityForm af, BindingResult binding) {
		ModelAndView result;
		Collection<Canyon> canyons;
		Collection<PieceEquipment> pieceEquipments;
		Activity activity;
		canyons = canyonService.findAll();
		pieceEquipments = pieceEquipmentService.findAll();
		if (binding.hasErrors()) {
			canyons = canyonService.findAll();

			result = createEditModelAndViewForm(af);
			result.addObject("canyons", canyons);
		} else {
			try {
				activity = activityService.recontruct(af);
				activityService.save(activity);
				result = new ModelAndView(
						"redirect:/activity/organiser/mylist.do");
			} catch (Throwable oops) {
				canyons = canyonService.findAll();
				result = new ModelAndView("activity/edit");
				result.addObject("canyons", canyons);
				result.addObject("pieceEquipments", pieceEquipments);
				result.addObject("message", "activity.commit.error");
			}
		}
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
				activityService
						.reinstantiateMomentActivity(activity, organiser);
				result = new ModelAndView(
						"redirect:/activity/organiser/mylist.do");
			} catch (Throwable oops) {
				canyons = canyonService.findAll();

				result = new ModelAndView("activity/organiser/reinstantiate");
				result.addObject("activity", activity);
				result.addObject("canyons", canyons);
				result.addObject("message", "activity.commit.error");
			}
		}
		return result;
	}

	// reinstantiate
	// Activity---------------------------------------------------------------------
	// @RequestMapping(value = "/reinstantiate", method = RequestMethod.GET)
	// public ModelAndView reinstantiate(@RequestParam int activityId) {
	// ModelAndView result;
	// Activity activity;
	// Organiser organiser;
	//
	// activity = activityService.findOne(activityId);
	// organiser = organiserService.findByPrincipal();
	//
	// activityService.reinstantiateMomentActivity(activity, organiser);
	//
	// result = new ModelAndView("redirect:/activity/organiser/mylist.do");
	// result.addObject("requestUri", "/activity/organiser/mylist.do");
	//
	// return result;
	// }

	// Ancillary methods
	// --------------------------------------------------------

	protected ModelAndView createEditModelAndView(Activity activity) {
		ModelAndView result;

		result = createEditModelAndView(activity, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Activity activity,
			String message) {
		ModelAndView result;

		result = new ModelAndView("activity/organiser/reinstantiate");
		result.addObject("activity", activity);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndViewForm(ActivityForm af) {
		ModelAndView res = createEditModelAndViewForm(af, null);
		return res;
	}

	protected ModelAndView createEditModelAndViewForm(ActivityForm af,
			String message) {
		ModelAndView res = new ModelAndView("activity/create");
		Collection<PieceEquipment> pieceEquipments = pieceEquipmentService
				.findAll();
		res.addObject("activityForm", af);
		res.addObject("pieceEquipments", pieceEquipments);

		res.addObject("message", message);
		return res;
	}
}
