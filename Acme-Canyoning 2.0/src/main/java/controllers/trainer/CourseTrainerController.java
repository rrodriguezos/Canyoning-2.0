package controllers.trainer;

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

import services.CourseService;
import controllers.AbstractController;
import domain.Course;

@Controller
@RequestMapping("/course/trainer")
public class CourseTrainerController extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private CourseService courseService;

	// Constructors --------------------------------------

	public CourseTrainerController() {
		super();
	}

	// List ----------------------------------------------

	@RequestMapping(value = "/mylist")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Course> courses;

		courses = courseService.coursesByTrainerLogged();

		result = new ModelAndView("course/trainer/mylist");
		result.addObject("courses", courses);
		result.addObject("requestUri", "/course/trainer/mylist.do");

		return result;
	}

	// Create --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Course course;

		course = courseService.create();

		result = createEditModelAndView(course);

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int courseId) {
		ModelAndView result;
		Course course;
		course = courseService.findOne(courseId);

		result = createEditModelAndView(course);
		result.addObject("course", course);
		return result;
	}

	// Save --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Course course, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(course,"course.commit.error");

			result.addObject("message", "course.commit.error");

		} else {
			try {
				courseService.save(course);
				result = new ModelAndView("redirect:/course/trainer/mylist.do");
				result.addObject("requestUri", "/course/trainer/mylist.do");
				redir.addFlashAttribute("message", "course.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(course, "course.commit.error");
			}
		}

		return result;
	}

	// Delete --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Course course, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(course, binding.toString());
		} else {
			try {
				courseService.delete(course);
				result = new ModelAndView("redirect:/course/trainer/mylist.do");
				result.addObject("requestUri", "/course/trainer/mylist.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(course, "course.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods
	// --------------------------------------------------------

	protected ModelAndView createEditModelAndView(Course course) {
		ModelAndView result;

		result = createEditModelAndView(course, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Course course, String message) {
		ModelAndView result;

		result = new ModelAndView("course/trainer/edit");
		result.addObject("course", course);
		result.addObject("message", message);

		return result;
	}

}
