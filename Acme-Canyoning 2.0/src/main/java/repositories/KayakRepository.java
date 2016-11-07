package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Kayak;
import domain.PieceEquipment;

@Repository
public interface KayakRepository extends JpaRepository<Kayak, Integer> {

	@Query("select k from Kayak k where k.organiser.id = ?1")
	Collection<Kayak> kayaksByOrganiserLogged(int organiserId);

	@Query("select a.pieceEquipments  from Activity a where a.id =?1")
	Collection<PieceEquipment> kayaksByActivity(int activityId);

	@Query("select count(k) from Kayak k")
	Integer numberTotalKayaks();

	@Query("select avg(a.pieceEquipments.size) from Activity a")
	Double averageKayaksByActivity();

}
