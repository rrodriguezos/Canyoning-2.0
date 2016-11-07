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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.KayakService;
import controllers.AbstractController;
import domain.Kayak;

@Controller
@RequestMapping("/kayak/organiser")
public class OrganiserKayakController extends AbstractController {

	// Supporting services -------------------------------
	@Autowired
	private KayakService kayakService;

	// Constructors --------------------------------------

	public OrganiserKayakController() {
		super();
	}

	// List ----------------------------------------------

	@RequestMapping(value = "/mylist")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Kayak> kayaks;

		kayaks = kayakService.kayaksByOrganiserLogged();

		result = new ModelAndView("kayak/organiser/mylist");
		result.addObject("kayaks", kayaks);
		result.addObject("requestUri", "/kayak/organiser/mylist.do");
		return result;
	}

	// Create --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Kayak kayak;

		kayak = kayakService.create();

		result = createEditModelAndView(kayak);

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int kayakId) {
		ModelAndView result;
		Kayak kayak;
		kayak = kayakService.findOne(kayakId);

		result = createEditModelAndView(kayak);
		result.addObject("kayak", kayak);
		return result;
	}

	// Save --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Kayak kayak, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(kayak);

		} else {
			try {
				kayakService.save(kayak);
				result = new ModelAndView("redirect:/kayak/organiser/mylist.do");
				result.addObject("requestUri", "/kayak/organiser/mylist.do");
				redir.addFlashAttribute("message", "kayak.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(kayak, "kayak.commit.error");
			}
		}

		return result;
	}

	// Delete --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Kayak kayak, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(kayak, binding.toString());
		} else {
			try {
				kayakService.delete(kayak);
				result = new ModelAndView("redirect:/kayak/organiser/mylist.do");
				result.addObject("requestUri", "/kayak/organiser/mylist.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(kayak, "kayak.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods
	// --------------------------------------------------------

	protected ModelAndView createEditModelAndView(Kayak kayak) {
		ModelAndView result;

		result = createEditModelAndView(kayak, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Kayak kayak, String message) {
		ModelAndView result;

		result = new ModelAndView("kayak/organiser/edit");
		result.addObject("kayak", kayak);
		result.addObject("message", message);

		return result;
	}

}
