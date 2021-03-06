package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

	@Query("select m from Module m where m.course.id = ?1 order by m.orderModule")
	Collection<Module> findModulesByCourse(int courseId);
	
	@Query("select avg(c.modules.size) from Course c")
	Double averageOfModulesByCourse();

}
