package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CanyonService;
import services.StoryService;
import controllers.AbstractController;
import domain.Canyon;
import domain.Story;

@Controller
@RequestMapping("/story/administrator")
public class AdministratorStoryController extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private StoryService storyService;
	@Autowired
	private CanyonService canyonService;

	// Constructors
	// ----------------------------------------------------------------

	public AdministratorStoryController() {
		super();
	}

	// Create-----------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int canyonId) {
		ModelAndView result;
		Story story;
		Canyon canyon;

		story = storyService.create();
		canyon = canyonService.findOne(canyonId);
		story.setCanyon(canyon);

		result = createEditModelAndView(story);

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int storyId) {
		ModelAndView result;
		Story story;
		story = storyService.findOne(storyId);

		result = createEditModelAndView(story);
		result.addObject("story", story);
		return result;
	}

	// Save-------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Story story, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(story, "story.commit.error");
		} else {
			try {
				storyService.save(story);
				result = new ModelAndView("redirect:/story/listByCanyon.do?canyonId="
						+ story.getCanyon().getId());
				redir.addFlashAttribute("message", "story.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(story);

				result.addObject("message", "story.commit.error");
			}
		}

		return result;
	}

	// Delete --------------------------------------------------------------
			@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
			public ModelAndView delete(@Valid Story story, BindingResult binding) {
				ModelAndView result;

				if (binding.hasErrors()) {
					result = createEditModelAndView(story, binding.toString());
				} else {
					try {
						storyService.delete(story);
						result = new ModelAndView("redirect:/canyon/administrator/mylist.do");
						result.addObject("requestUri", "/canyon/administrator/mylist.do");
					} catch (Throwable oops) {
						result = createEditModelAndView(story, "canyon.commit.error");
					}
				}
				return result;
			}

	// Ancillary methods--------------------------------------------------------

	protected ModelAndView createEditModelAndView(Story story) {
		ModelAndView result;

		result = createEditModelAndView(story, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Story story, String message) {
		ModelAndView result;

		result = new ModelAndView("story/administrator/edit");

		result.addObject("story", story);
		result.addObject("message", message);

		return result;
	}
}