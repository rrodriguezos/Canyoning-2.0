package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.SectionService;
import services.TrainerService;
import domain.Curriculum;
import domain.Section;
import domain.Trainer;

@Controller
@RequestMapping("/section")
public class SectionController extends AbstractController {
	// Supporting services ----------------------------------------------------
	@Autowired
	private SectionService sectionService;
	@Autowired
	private CurriculumService curriculumService;
	@Autowired
	private TrainerService trainerService;

	// Constructors -----------------------------------------------------------
	public SectionController() {
		super();
	}

	// List
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam int curriculumId) {

		ModelAndView result;
		Collection<Section> sections;
		Curriculum curriculum = curriculumService.findOne(curriculumId);
		Trainer trainer;
		Boolean mycurriculum;
		mycurriculum = false;
		sections = sectionService.findSectionsByCurriculumId(curriculumId);
		try {
			trainer = trainerService.findByPrincipal();
			curriculum = curriculumService.findOne(curriculumId);

			mycurriculum = trainer.equals(curriculum.getTrainer());

		} catch (Throwable oops) {

		}
		result = new ModelAndView("section/list");

		result.addObject("mycurriculum", mycurriculum);
		result.addObject("sections", sections);
		result.addObject("curriculumId", curriculumId);
		result.addObject("curriculum", curriculum);

		return result;
	}

	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int sectionId) {
		ModelAndView result;
		Section section;

		section = sectionService.findOne(sectionId);

		result = new ModelAndView("section/display");
		result.addObject("section", section);

		return result;
	}
}
