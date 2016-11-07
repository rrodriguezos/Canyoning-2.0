package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OrganiserService;
import services.WetsuitService;
import domain.Organiser;
import domain.Wetsuit;

@Controller
@RequestMapping("/wetsuit")
public class WetsuitController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private WetsuitService wetsuitService;

	@Autowired
	private OrganiserService organiserService;

	// Constructors -----------------------------------------------------------
	public WetsuitController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Wetsuit> wetsuits;

		wetsuits = wetsuitService.findAll();

		result = new ModelAndView("wetsuit/list");
		result.addObject("wetsuits", wetsuits);
		result.addObject("requestUri", "/wetsuit/list.do");

		return result;
	}

	// Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int wetsuitId) {
		ModelAndView result;
		Wetsuit wetsuit;
		Organiser organiser;
		Boolean mywetsuit;
		Boolean logeado;
		Boolean trousersLong;
		wetsuit = wetsuitService.findOne(wetsuitId);
		mywetsuit = false;
		logeado = false;
		trousersLong = false;

		try {
			
			organiser = organiserService.findByPrincipal();
			if (organiser != null) {
				logeado = true;
			}
			if (wetsuit.getSizeSleeves() == "LONG") {
				trousersLong = true;
			}
			if (organiser.equals(wetsuit.getOrganiser())) {
				mywetsuit = true;
			}
		} catch (Throwable oops) {
			mywetsuit = false;
			logeado = false;
			trousersLong= false;
		}

		wetsuit = wetsuitService.findOne(wetsuitId);

		result = new ModelAndView("wetsuit/display");
		result.addObject("wetsuit", wetsuit);

		result.addObject("mywetsuit", mywetsuit);
		result.addObject("logeado", logeado);
		result.addObject("trousersLong", trousersLong);
		

		return result;

	}

}
