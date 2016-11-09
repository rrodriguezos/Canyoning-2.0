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

import services.CurriculumService;
import services.SectionService;
import controllers.AbstractController;
import domain.Curriculum;
import domain.Section;

@Controller
@RequestMapping("/section/trainer")
public class SectionTrainerController extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private SectionService sectionService;
	@Autowired
	private CurriculumService curriculumService;

	// Constructors
	// ----------------------------------------------------------------

	public SectionTrainerController() {
		super();
	}

	// Create-----------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int curriculumId) {
		ModelAndView result;
		Section section;
		Curriculum curriculum;

		section = sectionService.create();
		curriculum = curriculumService.findOne(curriculumId);
		section.setCurriculum(curriculum);

		result = createEditModelAndView(section);

		return result;
	}

	// Save-------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Section section, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(section);

		} else {
			try {
				sectionService.save(section);
				result = new ModelAndView(
						"redirect:/section/list.do?curriculumId="
								+ section.getCurriculum().getId());
				redir.addFlashAttribute("message", "section.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(section);

				result.addObject("message", "section.commit.error");
			}
		}

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editSave(@RequestParam int sectionId) {
		ModelAndView result;
		Section section;
		section = sectionService.findOne(sectionId);

		result = createEditModelAndView(section);
		result.addObject("section", section);
		return result;
	}

	// Save --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveEdit")
	public ModelAndView saveEdit(@Valid Section section, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(section);

		} else {
			try {
				sectionService.save(section);
				result = new ModelAndView(
						"redirect:/curriculum/trainer/mylist.do");
				result.addObject("requestUri", "/curriculum/trainer/mylist.do");
				redir.addFlashAttribute("message", "section.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(section, "section.commit.error");
			}
		}

		return result;
	}

	// Delete-----------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam int sectionId) {
		ModelAndView result;

		Section desccription = sectionService.findOne(sectionId);

		sectionService.delete(desccription);

		result = new ModelAndView("redirect:/curriculum/trainer/mylist.do");
		result.addObject("requestUri", "/curriculum/trainer/mylist.do");

		return result;
	}

	// Ancillary methods--------------------------------------------------------

	protected ModelAndView createEditModelAndView(Section section) {
		ModelAndView result;

		result = createEditModelAndView(section, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Section section,
			String message) {
		ModelAndView result;

		result = new ModelAndView("section/create");

		result.addObject("section", section);
		result.addObject("message", message);

		return result;
	}

}
