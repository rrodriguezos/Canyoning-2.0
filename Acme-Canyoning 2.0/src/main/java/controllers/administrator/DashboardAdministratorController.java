package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CourseService;
import services.CustomerService;
import services.LearningMaterialService;
import services.ModuleService;
import services.OrganiserService;
import services.RequestService;
import services.TrainerService;
import controllers.AbstractController;
import domain.Activity;
import domain.Customer;
import domain.Trainer;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {
	
	// Services -----------------------
		@Autowired
		private CustomerService customerService;
		@Autowired
		private OrganiserService organiserService;
		@Autowired
		private ActivityService activityService;
		@Autowired
		private RequestService requestService;
		
		@Autowired
		private TrainerService trainerService;
		
		@Autowired
		private CourseService courseService;
		
		@Autowired
		private ModuleService moduleService;
		@Autowired
		private LearningMaterialService learningMaterialService;


		// Constructor --------------------
		public DashboardAdministratorController() {
			super();
		}

		// Listing ------------------------
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;

			// The average number of activities per organiser.
			Double averageActivitiesPerOrganiser = activityService.averageNumberOfActivitiesByOrganisers();
			// The average number of customers in the waiting lists.
//			Double averageCustomersInWaitingList = customerService.averageCustomersInWaitingList();

		


			// 10% more than the average.
			Collection<Activity> activity10MoreAverage = activityService.findWithMoreTenPercentOfSeatsAvg();

 
			// 10% less than the average.
			Collection<Activity> activity10LessAverage = activityService.findWithLessTenPercentOfSeatsAvg();

			result = new ModelAndView("administrator/dashboard");
			result.addObject("averageActivitiesPerOrganiser", averageActivitiesPerOrganiser);
//			result.addObject("averageCustomersInWaitingList", averageCustomersInWaitingList);


			result.addObject("activity10MoreAverage", activity10MoreAverage);
			result.addObject("activity10LessAverage", activity10LessAverage);

			return result;
		}
		
		@RequestMapping(value = "/list2", method = RequestMethod.GET)
		public ModelAndView list2() {
			ModelAndView result;
			
			//----------------------ACME CANYONING 2.0--------------------------
			//The average number of courses per trainer.
			Double averageOfCoursesByTrainer = courseService.averageOfCoursesByTrainer();
			//The trainers who teach at least 10% courses above or below the average.
			Collection<Trainer> findTrainersLeastTenAverage = trainerService.findTrainersLeastTenAverage();
			//The average number of modules per course.
			Double averageOfModulesByCourse = moduleService.averageOfModulesByCourse();
			//The average number of learning materials per course.
			Double averageOfLearningMaterialByCourse = learningMaterialService.averageOfLearningMaterialByCourse();
			
			result = new ModelAndView("administrator/dashboard-2");
			result.addObject("averageOfCoursesByTrainer", averageOfCoursesByTrainer);
			result.addObject("findTrainersLeastTenAverage", findTrainersLeastTenAverage);
			result.addObject("averageOfModulesByCourse", averageOfModulesByCourse);
			result.addObject("averageOfLearningMaterialByCourse", averageOfLearningMaterialByCourse);
			
			
			return result;
		}

}
