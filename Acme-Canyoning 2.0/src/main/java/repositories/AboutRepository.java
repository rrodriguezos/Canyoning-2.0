package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.About;



@Repository
public interface AboutRepository extends JpaRepository<About, Integer> {

	@Query("select o.about from Organiser o where o.id = ?1")
	About findAboutByOrganiser(int organiserId);

}
