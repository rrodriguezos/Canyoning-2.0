package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculumRepository extends
		JpaRepository<Curriculum, Integer> {

	@Query("select c from Curriculum c where c.trainer.id = ?1")
	Collection<Curriculum> findCurriculumsByTrainer(int id);

	@Query("select c from Curriculum c where c.trainer.id=?1 and c.isActive = true ")
	Curriculum curriculumActiveByTrainer(Integer trainerId);

}
