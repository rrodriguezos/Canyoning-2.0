package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PieceEquipmentRepository;
import domain.PieceEquipment;

@Service
@Transactional
public class PieceEquipmentService {

	// Managed repository -------------------
	@Autowired
	private PieceEquipmentRepository pieceEquipmentRepository;

	public Double averagePiecesPerActivity() {
		Double result;

		result = pieceEquipmentRepository.averagePiecesPerActivity();
		return result;
	}

	public Collection<PieceEquipment> findAll() {
		Collection<PieceEquipment> result;
		result = pieceEquipmentRepository.findAll();
		return result;
	}

	public Collection<PieceEquipment> pieceEquipmentsByActivity(int activityId) {
		Collection<PieceEquipment> result;

		result = pieceEquipmentRepository.pieceEquipmentsByActivity(activityId);
		return result;
	}

	public PieceEquipment findOne(int pieceEquipmentId) {
		PieceEquipment result;

		result = pieceEquipmentRepository.findOne(pieceEquipmentId);

		return result;
	}

}
