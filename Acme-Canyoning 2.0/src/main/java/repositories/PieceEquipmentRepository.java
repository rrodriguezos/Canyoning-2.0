package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PieceEquipment;

@Repository
public interface PieceEquipmentRepository extends
		JpaRepository<PieceEquipment, Integer> {
	
	@Query("select avg(a.pieceEquipments.size) from Activity a")
	Double averagePiecesPerActivity();

	@Query("select a.pieceEquipments from Activity a where a.id=?1")
	Collection<PieceEquipment> pieceEquipmentsByActivity(int activityId);
	
//	Double averagePiecesPerActivity();
}
