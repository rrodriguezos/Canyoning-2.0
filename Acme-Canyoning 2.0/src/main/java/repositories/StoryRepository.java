package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

	@Query("select o.stories from Administrator o where o.userAccount.id=?1")
	Collection<Story> findStoryByAdministrator(int administradorId);

	@Query("select s from Story s where s.canyon.id = ?1")
	Collection<Story> storiesByCanyon(int canyonId);

	@Query("select avg(c.stories.size) from Canyon c ")
	Double avgStoriesPerCanyon();

	@Query("select min(c.stories.size) from Canyon c")
	Double minStoriesPerCanyon();

	@Query("select max(c.stories.size) from Canyon c")
	Double maxStoriesPerCanyon();
}
