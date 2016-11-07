package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.KayakService;
import services.OrganiserService;
import domain.Kayak;
import domain.Organiser;

@Controller
@RequestMapping("/kayak")
public class KayakController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private KayakService kayakService;

	@Autowired
	private OrganiserService organiserService;

	// Constructors -----------------------------------------------------------
	public KayakController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Kayak> kayaks;

		kayaks = kayakService.findAll();

		result = new ModelAndView("kayak/list");
		result.addObject("kayaks", kayaks);
		result.addObject("requestUri", "/kayak/list.do");

		return result;
	}

	// Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int kayakId) {
		ModelAndView result;
		Kayak kayak;
		Organiser organiser;
		Boolean mykayak;
		Boolean logeado;
		kayak = kayakService.findOne(kayakId);
		mykayak = false;
		logeado = false;

		try {
			organiser = organiserService.findByPrincipal();
			if (organiser != null) {
				logeado = true;
			}
			if (organiser.equals(kayak.getOrganiser())) {
				mykayak = true;
			}
		} catch (Throwable oops) {
			mykayak = false;
			logeado = false;
		}

		kayak = kayakService.findOne(kayakId);

		result = new ModelAndView("kayak/display");
		result.addObject("kayak", kayak);

		result.addObject("mykayak", mykayak);
		result.addObject("logeado", logeado);

		return result;

	}

}
