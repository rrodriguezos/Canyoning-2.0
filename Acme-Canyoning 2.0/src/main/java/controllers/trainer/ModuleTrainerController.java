package controllers.trainer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CourseService;
import services.ModuleService;
import controllers.AbstractController;
import domain.Course;
import domain.Module;

@Controller
@RequestMapping("/module/trainer")
public class ModuleTrainerController extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private CourseService courseService;

	// Constructors
	// ----------------------------------------------------------------

	public ModuleTrainerController() {
		super();
	}

	// Create-----------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int courseId) {
		ModelAndView result;
		Module module;
		Course course;

		module = moduleService.create();
		course = courseService.findOne(courseId);
		module.setCourse(course);

		result = createEditModelAndView(module);

		return result;
	}

	// Save-------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Module module, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(module);

		} else {
			try {
				moduleService.save(module);
				result = new ModelAndView("redirect:/module/list.do?courseId="
						+ module.getCourse().getId());
				redir.addFlashAttribute("message", "module.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(module);

				result.addObject("message", "module.commit.error");
			}
		}

		return result;
	}

	// Delete-----------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam int moduleId) {
		ModelAndView result;

		Module module = moduleService.findOne(moduleId);

		moduleService.delete(module);

		result = new ModelAndView("redirect:/course/trainer/mylist.do");
		result.addObject("requestUri", "/course/trainer/mylist.do");

		return result;
	}

	// Ancillary methods--------------------------------------------------------

	protected ModelAndView createEditModelAndView(Module module) {
		ModelAndView result;

		result = createEditModelAndView(module, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Module module, String message) {
		ModelAndView result;

		result = new ModelAndView("module/create");

		result.addObject("module", module);
		result.addObject("message", message);

		return result;
	}

}
