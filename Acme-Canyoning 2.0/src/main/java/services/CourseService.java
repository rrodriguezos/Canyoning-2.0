package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CourseRepository;
import repositories.CourseRepository;
import domain.Activity;
import domain.Trainer;
import domain.Course;
import domain.Comment;
import domain.Course;
import domain.Module;
import domain.Trainer;

@Service
@Transactional
public class CourseService {

	// Managed repository -------------------
	@Autowired
	private CourseRepository courseRepository;
	// Supporting Services ------------------
	@Autowired
	private TrainerService trainerService;

	@Autowired
	private ModuleService moduleService;

	// COnstructors -------------------------------------------------------
	public CourseService() {
		super();
	}

	// Simple CRUD methods--------------------------------------------------

	public Course create() {
		Course result;
		Collection<Module> modules;

		Collection<Comment> comments;

		Trainer trainer;

		result = new Course();

		modules = new ArrayList<Module>();
		result.setModules(modules);

		trainer = trainerService.findByPrincipal();
		result.setTrainer(trainer);

		comments = new ArrayList<Comment>();
		result.setComments(comments);

		return result;
	}

	public Collection<Course> findAll() {
		Collection<Course> result;

		result = courseRepository.findAll();

		return result;
	}

	public Course findOne(int courseId) {
		Course result;

		result = courseRepository.findOne(courseId);

		return result;
	}

	public void save(Course course) {
		Assert.notNull(course);

		courseRepository.saveAndFlush(course);
	}

	public void delete(Course course) {
		Assert.notNull(course);
		checkPrincipal(course.getTrainer());
		course.getComments().clear();
		courseRepository.delete(course);
	}

	// Other Methods--------------------
	private void checkPrincipal(Trainer t) {
		Trainer trainer;

		trainer = trainerService.findByPrincipal();
		Assert.isTrue(trainer != null);

		Assert.isTrue(trainer.equals(trainer));
	}

	public Collection<Course> coursesByTrainer(int courseId) {
		Collection<Course> result;

		result = courseRepository.coursesByTrainer(courseId);

		return result;
	}

	public Collection<Course> findCoursesByTrainer() {
		Collection<Course> result;
		Trainer trainer;
		trainer = trainerService.findByPrincipal();

		result = courseRepository.findCourseByTrainer(trainer.getId());
		return result;
	}
	public Collection<Course> coursesByTrainerLogged() {
		Collection<Course> result;
		Trainer trainer;

		trainer = trainerService.findByPrincipal();

		result = courseRepository.findCourseByTrainer(trainer.getId());

		return result;
	}
}
