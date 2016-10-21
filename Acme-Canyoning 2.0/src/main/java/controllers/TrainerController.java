package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CourseService;
import services.TrainerService;
import domain.Course;
import domain.Trainer;

@Controller
@RequestMapping("/trainer")
public class TrainerController extends AbstractController {
	// Services ---------------------------------------------------------------
	@Autowired
	private TrainerService trainerService;
	@Autowired
	private CourseService courseService;

	// Constructors -----------------------------------------------------------

	public TrainerController() {
		super();
	}

	// List -----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Trainer> trainers;

		trainers = trainerService.findAll();

		result = new ModelAndView("trainer/list");
		result.addObject("trainers", trainers);

		result.addObject("requestURI", "trainer/list.do");

		return result;
	}

	// Display -----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int trainerId) {
		ModelAndView result;
		Trainer trainer;
		Collection<Course> courses;

		trainer = trainerService.findOne(trainerId);
		courses = courseService.coursesByTrainer(trainerId);

		result = new ModelAndView("trainer/display");
		result.addObject("trainer", trainer);
		result.addObject("courses", courses);

		return result;
	}
	
	// Listing by navigate from Trainer
			// ---------------------------------------------------
			@RequestMapping(value = "/listByCourse", method = RequestMethod.GET)
			public ModelAndView navigateByCourse(@RequestParam int courseId) {
				ModelAndView result;
				Trainer trainer;
				trainer = trainerService.findTrainerByCourse(courseId);

				result = new ModelAndView("trainer/list");
				result.addObject("trainers", trainer);
				result.addObject("requestURI", "trainer/listByCourse.do");
				return result;
			}

}
