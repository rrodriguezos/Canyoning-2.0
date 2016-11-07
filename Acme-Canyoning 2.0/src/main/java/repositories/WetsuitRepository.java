package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PieceEquipment;
import domain.Wetsuit;

@Repository
public interface WetsuitRepository extends JpaRepository<Wetsuit, Integer> {

	@Query("select w from Wetsuit w where w.organiser.id = ?1")
	Collection<Wetsuit> wetsuitsByOrganiserLogged(int organiserId);

	@Query("select a.pieceEquipments from Activity a where a.id =?1")
	Collection<PieceEquipment> wetsuitsByActivity(int activityId);

	@Query("select count(w) from Wetsuit w")
	Integer numberTotalWetsuits();

	@Query("select avg(a.pieceEquipments.size) from Activity a")
	Double averageWetsuitsByActivity();

}
