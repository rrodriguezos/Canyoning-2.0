package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	@Query("select a from Activity a where a.canyon.id = ?1")
	Collection<Activity> activityByCanyon(int canyonId);

	@Query("select a from Activity a where a.title like CONCAT('%',?1,'%') or a.description like CONCAT('%',?1,'%') or a.canyon.name like CONCAT('%',?1,'%') or a.canyon.description like CONCAT('%',?1,'%'))")
	Collection<Activity> findActivityByKeyword(String text);

	@Query("select o.activities from Organiser o where o.id = ?1")
	Collection<Activity> requestByOrganiser(int id);
	//The average number of activities per organiser.
	@Query("select avg(o.activities.size) from Organiser o")
	Double averageNumberOfActivitiesByOrganisers();
	
	
	@Query("select avg(o.activities.numberSeats.size) from Organiser o where o.activity.moment>CURRENT_DATE and o.activity.moment<=?1")
	Double seatsAvaliablesNextThreeMonths(Date upToDateCriteria);
	
	
	@Query("select a from Activity a where a.numberSeats > 0.2*(select avg(a.numberSeats) from Activity a)")
	Collection<Activity> findWithMoreTenPercentOfSeatsAvg();
	
	@Query("select a from Activity a where a.numberSeats < 0.2*(select avg(a.numberSeats) from Activity a)")
	Collection<Activity> findWithLessTenPercentOfSeatsAvg();

	@Query("select a from Activity a where a.organiser.id = ?1")
	Collection<Activity> findActivitiesByOrganiser(int organiserId);


}
