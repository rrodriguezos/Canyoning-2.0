package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CordService;
import services.OrganiserService;
import domain.Cord;
import domain.Organiser;

@Controller
@RequestMapping("/cord")
public class CordController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private CordService cordService;

	@Autowired
	private OrganiserService organiserService;

	// Constructors -----------------------------------------------------------
	public CordController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Cord> cords;

		cords = cordService.findAll();

		result = new ModelAndView("cord/list");
		result.addObject("cords", cords);
		result.addObject("requestUri", "/cord/list.do");

		return result;
	}

	// Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int cordId) {
		ModelAndView result;
		Cord cord;
		Organiser organiser;
		Boolean mycord;
		Boolean logeado;
		cord = cordService.findOne(cordId);
		mycord = false;
		logeado = false;

		try {
			organiser = organiserService.findByPrincipal();
			if (organiser != null) {
				logeado = true;
			}
			if (organiser.equals(cord.getOrganiser())) {
				mycord = true;
			}
		} catch (Throwable oops) {
			mycord = false;
			logeado = false;
		}

		cord = cordService.findOne(cordId);

		result = new ModelAndView("cord/display");
		result.addObject("cord", cord);

		result.addObject("mycord", mycord);
		result.addObject("logeado", logeado);

		return result;

	}

}
