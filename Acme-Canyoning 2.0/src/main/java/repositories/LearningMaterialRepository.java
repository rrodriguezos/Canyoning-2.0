package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LearningMaterial;

@Repository
public interface LearningMaterialRepository extends
		JpaRepository<LearningMaterial, Integer> {

	@Query("select l from LearningMaterial l where l.module.id = ?1")
	Collection<LearningMaterial> learningMaterialsByModule(int moduleId);

	@Query("select avg(c.modules.learningMaterials.size) from Course c")
	Double averageOfLearningMaterialByCourse();

}
