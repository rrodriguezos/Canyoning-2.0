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
		Curriculum curriculum;
		Trainer trainer;
		Boolean mycurriculum;
		sections = sectionService.findSectionsByCurriculumId(curriculumId);
		result = new ModelAndView("section/list");
		result.addObject("sections", sections);
		result.addObject("curriculumId", curriculumId);
		mycurriculum = false;
		try {
			trainer = trainerService.findByPrincipal();
			curriculum = curriculumService.findOne(curriculumId);

			mycurriculum = trainer.equals(curriculum.getTrainer());

		} catch (Throwable oops) {

		}
		result.addObject("mycurriculum", mycurriculum);

		return result;
	}

	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int sectionId) {
		ModelAndView result;
		Section section;
		Curriculum curriculum;
		Boolean mycurriculum;
		Boolean logeado;
		Trainer trainer;
		logeado = false;

		section = sectionService.findOne(sectionId);
		curriculum = curriculumService.findOne(section.getCurriculum().getId());
		mycurriculum = false;

		try {
			trainer = trainerService.findByPrincipal();
			if (trainer != null) {
				logeado = true;
			}
			if (trainer.equals(curriculum.getTrainer())) {
				mycurriculum = true;
			}
		} catch (Throwable oops) {
			mycurriculum = false;
			logeado = false;
		}

		section = sectionService.findOne(sectionId);

		result = new ModelAndView("section/display");
		result.addObject("section", section);
		result.addObject("mycurriculum", mycurriculum);
		result.addObject("logeado", logeado);

		return result;
	}

}
