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

import services.CurriculumService;
import services.TrainerService;
import controllers.AbstractController;
import domain.Curriculum;

@Controller
@RequestMapping("/curriculum/trainer")
public class CurriculumTrainerController extends AbstractController {
	// Supporting services -------------------------------

		@Autowired
		private CurriculumService curriculumService;

		@Autowired
		private TrainerService trainerService;

		

		// Constructors --------------------------------------

		public CurriculumTrainerController() {
			super();
		}

		// List ----------------------------------------------

		@RequestMapping(value = "/mylist")
		public ModelAndView list() {

			ModelAndView result;
			Collection<Curriculum> curriculums;

			curriculums = curriculumService.curriculumByTrainerLogged();

			result = new ModelAndView("curriculum/trainer/mylist");
			result.addObject("curriculums", curriculums);
			result.addObject("requestUri", "/curriculum/trainer/mylist.do");
			
			return result;
		}

		// Create --------------------------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Curriculum curriculum;

			curriculum = curriculumService.create();

			result = createEditModelAndView(curriculum);

			return result;
		}

		// Edit -----------------------------------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int curriculumId) {
			ModelAndView result;
			Curriculum curriculum;
			curriculum = curriculumService.findOne(curriculumId);

			result = createEditModelAndView(curriculum);
			result.addObject("curriculum", curriculum);
			return result;
		}

		// Save --------------------------------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Curriculum curriculum, BindingResult binding,
				RedirectAttributes redir) {
			ModelAndView result;


			if (binding.hasErrors()) {
				result = createEditModelAndView(curriculum);
			} else {
				try {
					curriculumService.save(curriculum);
					result = new ModelAndView("redirect:/curriculum/trainer/mylist.do");
					result.addObject("requestUri", "/curriculum/trainer/mylist.do");
					redir.addFlashAttribute("message", "curriculum.commit.ok");

				} catch (Throwable oops) {
					result = createEditModelAndView(curriculum, "curriculum.commit.error");
				}
			}

			return result;
		}

		// Delete --------------------------------------------------------------
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid Curriculum curriculum, BindingResult binding) {
			ModelAndView result;

			if (binding.hasErrors()) {
				result = createEditModelAndView(curriculum, binding.toString());
			} else {
				try {
					curriculumService.delete(curriculum);
					result = new ModelAndView("redirect:/curriculum/trainer/mylist.do");
					result.addObject("requestUri", "/curriculum/trainer/mylist.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(curriculum, "curriculum.commit.error");
				}
			}
			return result;
		}


		

		// Ancillary methods
		// --------------------------------------------------------

		protected ModelAndView createEditModelAndView(Curriculum curriculum) {
			ModelAndView result;

			result = createEditModelAndView(curriculum, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Curriculum curriculum, String message) {
			ModelAndView result;

			result = new ModelAndView("curriculum/trainer/edit");
			result.addObject("curriculum", curriculum);
			result.addObject("message", message);

			return result;
		}

	}

