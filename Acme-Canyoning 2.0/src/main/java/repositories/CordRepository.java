package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Cord;
import domain.PieceEquipment;

@Repository
public interface CordRepository extends JpaRepository<Cord, Integer> {

	@Query("select c from Cord c where c.organiser.id = ?1")
	Collection<Cord> cordsByOrganiserLogged(int organiserId);

	@Query("select a.pieceEquipments  from Activity a where a.id =?1")
	Collection<PieceEquipment> cordsByActivity(int activityId);

	@Query("select count(c) from Cord c")
	Integer numberTotalCords();

	@Query("select avg(a.pieceEquipments.size) from Activity a")
	Double averageCordsByActivity();

}
