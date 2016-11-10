package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query("select t.courses from Trainer t where t.id = ?1")
	Collection<Course> coursesByTrainer(int trainerId);

	@Query("select t.courses from Trainer t where t.id = ?1")
	Collection<Course> findCourseByTrainer(int id);

	@Query("select avg(t.courses.size) from Trainer t")
	Double averageOfCoursesByTrainer();

}
