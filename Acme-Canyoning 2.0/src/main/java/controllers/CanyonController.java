package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CanyonService;
import services.CommentService;
import domain.Canyon;
import domain.Comment;

@Controller
@RequestMapping("/canyon")
public class CanyonController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private CanyonService canyonService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors -----------------------------------------------------------
	public CanyonController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Canyon> canyons;

		canyons = canyonService.findAll();

		result = new ModelAndView("canyon/list");
		result.addObject("canyons", canyons);
		result.addObject("requestUri", "/canyon/list.do");

		return result;
	}

	// Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int canyonId) {
		ModelAndView result;
		Canyon canyon;
		Collection<Comment> comments;

		comments = commentService.findCommentsByCommentableId(canyonId);

		canyon = canyonService.findOne(canyonId);
		comments = commentService.findCommentsByCommentableId(canyonId);

		result = new ModelAndView("canyon/display");
		result.addObject("canyon", canyon);

		result.addObject("comments", comments);
		return result;

	}

	// Listing by navigate from Activity
	// ---------------------------------------------------
	@RequestMapping(value = "/listByActivity", method = RequestMethod.GET)
	public ModelAndView navigateByActivity(@RequestParam int activityId) {
		ModelAndView result;
		Canyon canyon = canyonService.canyonByActivity(activityId);

		result = new ModelAndView("canyon/listAll");
		result.addObject("canyon", canyon);
		result.addObject("requestURI", "canyon/listByActivity.do");
		return result;
	}
}
