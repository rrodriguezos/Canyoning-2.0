package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Course;
import domain.Module;
import domain.Trainer;

import services.CourseService;
import services.ModuleService;
import services.TrainerService;

@Controller
@RequestMapping("/module")
public class ModuleController extends AbstractController {
	// Supporting services ----------------------------------------------------
		@Autowired
		private ModuleService moduleService;
		@Autowired
		private CourseService courseService;
		@Autowired
		private TrainerService trainerService;


		// Constructors -----------------------------------------------------------
		public ModuleController() {
			super();
		}

		// List
		// ---------------------------------------------------------------------------
		@RequestMapping(value = "/list")
		public ModelAndView list(@RequestParam int courseId) {

			ModelAndView result;
			Collection<Module> modules;
			Course course;
			Trainer trainer;
			Boolean mycourse;
			modules = moduleService.modulesByCourse(courseId);
			result = new ModelAndView("module/list");
			result.addObject("modules", modules);
			result.addObject("courseId", courseId);
			mycourse = false;
			try {
				trainer = trainerService.findByPrincipal();
				course = courseService.findOne(courseId);

				mycourse = trainer.equals(course.getTrainer());

			} catch (Throwable oops) {

			}
			result.addObject("mycourse", mycourse);

			return result;
		}

		

		// Display --------------------------------------------------------
		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView display(int moduleId) {
			ModelAndView result;
			Module module;

			module = moduleService.findOne(moduleId);

			result = new ModelAndView("module/display");
			result.addObject("module", module);

			return result;
		}
}
