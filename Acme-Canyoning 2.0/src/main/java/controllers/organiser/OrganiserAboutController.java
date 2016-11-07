package controllers.organiser;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AboutService;
import controllers.AbstractController;
import domain.About;

@Controller
@RequestMapping("/about/organiser")
public class OrganiserAboutController extends AbstractController {
	// Supporting services ----------------------------------------------------
	@Autowired
	private AboutService aboutService;

	// Constructors -----------------------------------------------------------
	public OrganiserAboutController() {
		super();
	}

	// List -------------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		About about;
		boolean yaTengo;
		yaTengo = true;

		about = aboutService.findAboutByOrganiser();
		try {
			if (about == null) {
				yaTengo = false;
			}
		} catch (Throwable oops) {
			yaTengo = true;
		}
		result = new ModelAndView("about/organiser/list");
		result.addObject("requestUri", "/about/organiser/list.do");
		result.addObject("about", about);
		result.addObject("yaTengo", yaTengo);

		return result;
	}

	// Create---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		About about;

		about = aboutService.create();

		result = new ModelAndView("about/organiser/create");
		result.addObject("about", about);

		return result;
	}

	// Edit
	// -------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int aboutId) {
		ModelAndView result;
		About about;

		about = aboutService.findOne(aboutId);

		result = new ModelAndView("about/organiser/edit");
		result.addObject("about", about);

		return result;
	}

	// Save -------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid About about, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.print(binding.getFieldError());
			System.out.print(binding.getAllErrors());

			result = new ModelAndView("about/organiser/edit");
			result.addObject("about", about);
		} else {
			try {
				aboutService.save(about);
				result = new ModelAndView("redirect:/about/organiser/list.do");
			} catch (Throwable oops) {

				result = new ModelAndView("about/organiser/edit");
				result.addObject("about", about);
				result.addObject("message", "about.commit.error");
			}
		}
		return result;
	}

	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int aboutId) {
		ModelAndView result;
		About about;
		about = aboutService.findOne(aboutId);

		result = new ModelAndView("about/organiser/display");
		result.addObject("about", about);

		return result;
	}

}
