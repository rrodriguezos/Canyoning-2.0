package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CordService;
import services.CourseService;
import services.CurriculumService;
import services.CustomerService;
import services.KayakService;
import services.LearningMaterialService;
import services.ModuleService;
import services.PieceEquipmentService;
import services.SectionService;
import services.TrainerService;
import services.WetsuitService;
import controllers.AbstractController;
import domain.Activity;
import domain.Trainer;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services -----------------------
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private TrainerService trainerService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private LearningMaterialService learningMaterialService;

	@Autowired
	private KayakService kayakService;

	@Autowired
	private CordService cordService;

	@Autowired
	private WetsuitService wetsuitService;

	@Autowired
	private PieceEquipmentService pieceEquipmentService;

	@Autowired
	private CurriculumService curriculumService;

	@Autowired
	private SectionService sectionService;

	// Constructor --------------------
	public DashboardAdministratorController() {
		super();
	}

	// Listing ------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		// --------C-------------
		// The average number of activities per organiser.
		Double averageActivitiesPerOrganiser = activityService
				.averageNumberOfActivitiesByOrganisers();
		// The average number of customers in the waiting lists.
		Double averageCustomersInWaitingList = customerService
				.averageCustomersInWaitingList();
		// The average number of seats offered in the activities that are going
		// to be organised in the forthcoming three months.
		Double averageSeatsOrganisedThreeMonths = activityService
				.averageSeatsOrganisedThreeMonths();
		// 10% more than the average.
		Collection<Activity> activity10MoreAverage = activityService
				.findWithMoreTenPercentOfSeatsAvg();
		// 10% less than the average.
		Collection<Activity> activity10LessAverage = activityService
				.findWithLessTenPercentOfSeatsAvg();
		// The average of the time that a customer remains in a waiting list.
		Double averageTimeRemainWaitingList = customerService
				.averageTimeRemainWaitingList();
		// The standard deviation of the time that a customer remains in a
		// waiting list.
		Double stdTimeRemainWaitingList = customerService
				.stdTimeRemainWaitingList();
		// --------B-------------
		// The total number of kayaks, cords, and wetsuits that the organisers
		// have registered.
		Integer totalNumberKayaks = kayakService.numberTotalKayaks();
		Integer totalNumberCords = cordService.numberTotalCords();
		Integer totalNumberWetsuits = wetsuitService.numberTotalWetsuits();

		// The average number of pieces of equipment required per activity.
		Double averagePiecesPerActivity = pieceEquipmentService
				.averagePiecesPerActivity();

		// The average number of kayaks per activity.
		Double averageKayaksByActivity = kayakService.averageKayaksByActivity();

		// The average number of cords per activity.
		Double averageCordsByActivity = cordService.averageCordsByActivity();

		// The average number of wetsuits per activity.
		Double averageWetsuitsByActivity = wetsuitService
				.averageWetsuitsByActivity();

		// --------2.0 B-------------

		// The average number of curricula per trainer.
		Double averageCurriculumsByTrainer = curriculumService
				.averageCurriculumsByTrainer();

		// The average number of sections per curriculum.
		Double averageSectionsByCurriculums = sectionService
				.averageSectionsByCurriculums();

		// The trainers who have registered at least a curriculum
		// in which his or her full name does not coincide with the full name
		// in his or her user account.
		Collection<Trainer> trainersNameNotMatchCurriculumName = trainerService
				.trainersNameNotMatchCurriculumName();

		// The trainers who havent registered any curricula.
		Collection<Trainer> trainersNoCurriculum = trainerService
				.trainersNoCurriculum();

		// The trainers who havent updated their curricula for more than three
		// months.
		Collection<Trainer> trainersNoUpdateCurriculumThree = trainerService
				.trainersNoUpdateCurriculumThree();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("averageActivitiesPerOrganiser",
				averageActivitiesPerOrganiser);
		result.addObject("averageCustomersInWaitingList",
				averageCustomersInWaitingList);
		result.addObject("averageSeatsOrganisedThreeMonths",
				averageSeatsOrganisedThreeMonths);

		result.addObject("activity10MoreAverage", activity10MoreAverage);
		result.addObject("activity10LessAverage", activity10LessAverage);
		result.addObject("averageTimeRemainWaitingList",
				averageTimeRemainWaitingList);
		result.addObject("stdTimeRemainWaitingList", stdTimeRemainWaitingList);
		result.addObject("totalNumberKayaks", totalNumberKayaks);
		result.addObject("totalNumberCords", totalNumberCords);
		result.addObject("totalNumberWetsuits", totalNumberWetsuits);
		result.addObject("averagePiecesPerActivity", averagePiecesPerActivity);
		result.addObject("averageKayaksByActivity", averageKayaksByActivity);
		result.addObject("averageCordsByActivity", averageCordsByActivity);
		result.addObject("averageWetsuitsByActivity", averageWetsuitsByActivity);

		result.addObject("averageCurriculumsByTrainer",
				averageCurriculumsByTrainer);
		result.addObject("averageSectionsByCurriculums",
				averageSectionsByCurriculums);
		result.addObject("trainersNameNotMatchCurriculumName",
				trainersNameNotMatchCurriculumName);
		result.addObject("trainersNoCurriculum", trainersNoCurriculum);
		result.addObject("trainersNoUpdateCurriculumThree",
				trainersNoUpdateCurriculumThree);

		return result;
	}

	@RequestMapping(value = "/list2", method = RequestMethod.GET)
	public ModelAndView list2() {
		ModelAndView result;

		// ----------------------ACME CANYONING 2.0--------------------------
		// The average number of courses per trainer.
		Double averageOfCoursesByTrainer = courseService
				.averageOfCoursesByTrainer();
		// The trainers who teach at least 10% courses above or below the
		// average.
		Collection<Trainer> findTrainersLeastTenAverage = trainerService
				.findTrainersLeastTenAverage();
		// The average number of modules per course.
		Double averageOfModulesByCourse = moduleService
				.averageOfModulesByCourse();
		// The average number of learning materials per course.
		Double averageOfLearningMaterialByCourse = learningMaterialService
				.averageOfLearningMaterialByCourse();

		result = new ModelAndView("administrator/dashboard-2");
		result.addObject("averageOfCoursesByTrainer", averageOfCoursesByTrainer);
		result.addObject("findTrainersLeastTenAverage",
				findTrainersLeastTenAverage);
		result.addObject("averageOfModulesByCourse", averageOfModulesByCourse);
		result.addObject("averageOfLearningMaterialByCourse",
				averageOfLearningMaterialByCourse);

		return result;
	}

}
