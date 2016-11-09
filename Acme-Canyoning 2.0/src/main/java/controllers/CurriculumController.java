package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.TrainerService;
import domain.Curriculum;
import domain.Trainer;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private CurriculumService curriculumService;

	@Autowired
	private TrainerService trainerService;

	// Constructors -----------------------------------------------------------
	public CurriculumController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Curriculum> curriculums;

		curriculums = curriculumService.findAll();

		result = new ModelAndView("curriculum/list");
		result.addObject("curriculums", curriculums);
		result.addObject("requestUri", "/curriculum/list.do");

		return result;
	}
	
	// ListActive -----------------------------------------------------------
		@RequestMapping(value = "/listActive")
		public ModelAndView listActive(@RequestParam int trainerId) {

			ModelAndView result;
			Curriculum curriculumActive;
			
			
			curriculumActive = curriculumService.curriculumActiveByTrainer(trainerId);


			result = new ModelAndView("curriculum/list");
			result.addObject("curriculums", curriculumActive);
			result.addObject("requestUri", "/curriculum/list.do");

			return result;
		}

	// Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int curriculumId) {
		ModelAndView result;
		Curriculum curriculum;
		Trainer trainer;
		Boolean mycurriculum;
		Boolean active;
		curriculum = curriculumService.findOne(curriculumId);
		mycurriculum = false;
		active = false;

		try {
			trainer = trainerService.findByPrincipal();
			if (trainer.equals(curriculum.getTrainer())) {
				mycurriculum = true;
			}
			if (curriculum.getIsActive() == true) {
				active = true;
			}

		} catch (Throwable oops) {
			mycurriculum = false;
			active=false;
		}

		curriculum = curriculumService.findOne(curriculumId);

		result = new ModelAndView("curriculum/display");
		result.addObject("curriculum", curriculum);

		result.addObject("mycurriculum", mycurriculum);
		result.addObject("active", active);

		return result;

	}

}
