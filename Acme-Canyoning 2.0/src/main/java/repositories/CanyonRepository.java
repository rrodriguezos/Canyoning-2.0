package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Canyon;

@Repository
public interface CanyonRepository extends JpaRepository<Canyon, Integer> {

	

	@Query("select a.canyon from Activity a where a.id = ?1")
	Canyon canyonByActivity(int activityId);

	@Query("select a.canyons from Administrator a where a.id = ?1")
	Collection<Canyon> findCanyonByAdministrator(int id);

@Query("select s.canyon from Story s where s.id = ?1")
	Canyon canyonByStory(int storyId);

	@Query("select c from Canyon c order by c.stories.size desc")
	Collection<Canyon> canyonsSortedStories();

}
