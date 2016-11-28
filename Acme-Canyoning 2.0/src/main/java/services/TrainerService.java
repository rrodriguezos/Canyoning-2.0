package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TrainerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Comment;
import domain.Course;
import domain.Curriculum;
import domain.Trainer;
import domain.TrainerComment;
import forms.TrainerForm;

@Service
@Transactional
public class TrainerService {

	// Managed repository -------------------------

	@Autowired
	private TrainerRepository trainerRepository;

	// Supporting Services -------------------------

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private UserAccountService userAccountService;

	// Constructors -------------------------------
	public TrainerService() {
		super();
	}

	// Simple CRUD methods -----------------------------

	public Trainer create() {
		checkPrincipalAdministrator();

		UserAccount useraccount;
		Trainer result;
		Collection<Comment> comments;
		Collection<Course> courses;
		Collection<Curriculum> curriculums;

		TrainerComment trainerComment = new TrainerComment();
		Collection<Comment> commentsTrainerComment = new LinkedList<Comment>();
		Authority aut = new Authority();

		aut.setAuthority("TRAINER");
		useraccount = userAccountService.create();

		result = new Trainer();

		trainerComment.setComments(commentsTrainerComment);
		trainerComment.setTrainer(result);

		useraccount.addAuthority(aut);
		result.setUserAccount(useraccount);
		result.setTrainerComment(trainerComment);

		courses = new LinkedList<Course>();
		result.setCourses(courses);

		curriculums = new LinkedList<Curriculum>();
		result.setCurriculums(curriculums);

		comments = new LinkedList<Comment>();
		result.setComments(comments);

		return result;
	}

	public Collection<Trainer> findAll() {

		Collection<Trainer> result;

		result = trainerRepository.findAll();

		return result;
	}

	public Trainer findOne(int trainerId) {
		Trainer result;

		result = trainerRepository.findOne(trainerId);

		return result;
	}

	public void save(Trainer trainer) {

		if (trainer.getId() == 0) {
			Md5PasswordEncoder encoder;

			encoder = new Md5PasswordEncoder();

			trainer.getUserAccount().setPassword(
					encoder.encodePassword(trainer.getUserAccount()
							.getPassword(), null));
		}
		trainer = trainerRepository.saveAndFlush(trainer);
		Assert.notNull(trainer);
	}

	private void checkPrincipalAdministrator() {
		Administrator administrator;
		Authority authority;

		administrator = administratorService.findByPrincipal();
		Assert.isTrue(administrator != null);
		authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(administrator.getUserAccount().getAuthorities()
				.contains(authority));
	}

	// other methods
	// -------------------------------------------------------------------------

	public Trainer findByPrincipal() {
		UserAccount userAccount;
		Trainer result;
		int id;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		id = userAccount.getId();
		result = trainerRepository.findByUserAccountId(id);
		Assert.notNull(result);

		return result;

	}

	public Trainer reconstruct(TrainerForm trainerForm) {
		Trainer res;
		res = create();
		Assert.isTrue(trainerForm.getPassword().equals(
				trainerForm.getConfirmPassword()));
		res.setName(trainerForm.getName());
		res.setSurname(trainerForm.getSurname());
		res.setPhone(trainerForm.getPhone());
		res.setEmail(trainerForm.getEmail());

		res.getUserAccount().setUsername(trainerForm.getUsername());
		res.getUserAccount().setPassword(trainerForm.getPassword());

		return res;
	}

	public Trainer findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Trainer result;
		result = trainerRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public Trainer findTrainerByCourse(int courseId) {
		Trainer result;
		Course course;
		course = courseService.findOne(courseId);
		result = course.getTrainer();
		return result;
	}

	public Collection<Trainer> findTrainersLeastTenAverage() {
		// Collection<Trainer> result;
		Double media = courseService.averageOfCoursesByTrainer();
		Collection<Trainer> trainers = findAll();
		Collection<Trainer> result = new LinkedList<Trainer>();

		try {
			Double tenPercent = media * 0.10;
			Double tenPercentAbove = media + tenPercent;
			Double tenPercentBelow = media - tenPercent;

			for (Trainer t : trainers) {
				int tam;
				tam = t.getCourses().size();
				if (tam > tenPercentBelow && tam < tenPercentAbove) {
					result.add(t);
				}
			}

		} catch (Throwable oops) {
			return result;
		}

		// result = trainerRepository.courses10percAboveBelowAvg();
		return result;
	}

	public Collection<Trainer> trainersNameNotMatchCurriculumName() {
		Collection<Trainer> result;

		result = trainerRepository.trainersNameNotMatchCurriculumName();

		return result;
	}

	public Collection<Trainer> trainersNoCurriculum() {
		Collection<Trainer> result;
		result = trainerRepository.trainersNoCurriculum();

		return result;
	}

	public Collection<Trainer> trainersNoUpdateCurriculumThree() {
		Collection<Trainer> result;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.MONTH, -3);
		Date upToDateCriteria = calendar.getTime();

		result = trainerRepository
				.trainersNoUpdateCurriculumThree(upToDateCriteria);
		return result;
	}

}
