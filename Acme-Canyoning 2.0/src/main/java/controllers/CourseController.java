package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.CourseService;
import services.TrainerService;
import domain.Comment;
import domain.Course;
import domain.Trainer;

@Controller
@RequestMapping("/course")
public class CourseController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private CourseService courseService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private TrainerService trainerService;

	// Constructors -----------------------------------------------------------
	public CourseController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Course> courses;

		courses = courseService.findAll();

		result = new ModelAndView("course/list");
		result.addObject("courses", courses);
		result.addObject("requestUri", "/course/list.do");

		return result;
	}

	// Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int courseId) {
		ModelAndView result;
		Course course;
		Collection<Comment> comments;
		course = courseService.findOne(courseId);
		Trainer trainer;
		Boolean mycourse;
		mycourse = false;

		try {
			trainer = trainerService.findByPrincipal();
			if (trainer.equals(course.getTrainer())) {
				mycourse = true;
			}
		} catch (Throwable oops) {
			mycourse = false;

		}

		comments = commentService.findCommentsByCommentableId(courseId);

		course = courseService.findOne(courseId);
		comments = commentService.findCommentsByCommentableId(courseId);

		result = new ModelAndView("course/display");
		result.addObject("course", course);
		result.addObject("mycourse", mycourse);
		result.addObject("comments", comments);
		return result;

	}

	// Listing by navigate from Trainer
	// ---------------------------------------------------
	@RequestMapping(value = "/listByTrainer", method = RequestMethod.GET)
	public ModelAndView navigateByActivity(@RequestParam int trainerId) {
		ModelAndView result;
		Collection<Course> courses = courseService.coursesByTrainer(trainerId);

		result = new ModelAndView("course/listAll");
		result.addObject("course", courses);
		result.addObject("requestURI", "course/listByTrainer.do");
		return result;
	}

}
