package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CanyonService;
import controllers.AbstractController;
import domain.Canyon;

@Controller
@RequestMapping("/canyon/administrator")
public class AdministratorCanyonController extends AbstractController {

	// Services -----------------------
	@Autowired
	private CanyonService canyonService;

	// Constructor --------------------
	public AdministratorCanyonController() {
		super();
	}

	// List ------------------------------------------------------------------

	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Canyon> canyons;

		canyons = canyonService.findCanyonsByAdministrator();

		result = new ModelAndView("canyon/administrator/mylist");
		result.addObject("canyons", canyons);
		result.addObject("requestUri", "/canyon/administrator/mylist.do");

		return result;
	}

	// Create---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Canyon canyon;

		canyon = canyonService.create();

		result = new ModelAndView("canyon/edit");
		result.addObject("canyon", canyon);

		return result;
	}

	// Edit
	// -------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int canyonId) {
		ModelAndView result;
		Canyon canyon;

		canyon = canyonService.findOne(canyonId);

		result = new ModelAndView("canyon/edit");
		result.addObject("canyon", canyon);

		return result;
	}

	// Save -------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Canyon canyon, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.print(binding.getFieldError());
			System.out.print(binding.getAllErrors());

			result = new ModelAndView("canyon/edit");
			result.addObject("canyon", canyon);
		} else {
			try {
				canyonService.save(canyon);
				result = new ModelAndView(
						"redirect:/canyon/administrator/mylist.do");
			} catch (Throwable oops) {

				result = new ModelAndView("canyon/edit");
				result.addObject("canyon", canyon);
				result.addObject("message2", "canyon.commit.error");
			}
		}
		return result;
	}
	
	// Delete --------------------------------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid Canyon canyon, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(canyon, binding.toString());
			} else {
				try {
					canyonService.delete(canyon);
					result = new ModelAndView("redirect:/canyon/administrator/mylist.do");
					result.addObject("requestUri", "/canyon/administrator/mylist.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(canyon, "canyon.commit.error");
				}
			}
			return result;
		}
		
		// Ancillary methods
		// --------------------------------------------------------

		protected ModelAndView createEditModelAndView(Canyon canyon) {
			ModelAndView result;

			result = createEditModelAndView(canyon, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Canyon canyon, String message) {
			ModelAndView result;

			result = new ModelAndView("canyon/edit");
			result.addObject("canyon", canyon);
			result.addObject("message2", message);

			return result;
		}

}
