package services;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CordRepository;
import domain.Activity;
import domain.Cord;
import domain.Organiser;
import domain.PieceEquipment;

@Service
@Transactional
public class CordService {

	// Managed repository -------------------
	@Autowired
	private CordRepository cordRepository;

	// Supporting Services ------------------
	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private ActivityService activityService;

	// COnstructors -------------------------------------------------------
	public CordService() {
		super();
	}

	// Simple CRUD methods--------------------------------------------------

	public Cord create() {
		Cord result;

		Organiser organiser;

		result = new Cord();

		organiser = organiserService.findByPrincipal();
		result.setOrganiser(organiser);

		return result;
	}

	public Collection<Cord> findAll() {
		Collection<Cord> result;

		result = cordRepository.findAll();

		return result;
	}

	public Cord findOne(int cordId) {
		Cord result;

		result = cordRepository.findOne(cordId);

		return result;
	}

	public void save(Cord cord) {
		Assert.notNull(cord);
		cordRepository.saveAndFlush(cord);
	}

	public void delete(Cord cord) {
		Assert.notNull(cord);
		checkPrincipal(cord.getOrganiser());

		cordRepository.delete(cord);
	}

	// Other Methods--------------------
	private void checkPrincipal(Organiser u) {
		Organiser organiser;

		organiser = organiserService.findByPrincipal();
		Assert.isTrue(organiser != null);

		Assert.isTrue(organiser.equals(u));
	}

	public Collection<Cord> cordsByOrganiserLogged() {
		Collection<Cord> result;
		Organiser organiser;
		organiser = organiserService.findByPrincipal();
		result = cordRepository.cordsByOrganiserLogged(organiser.getId());
		return result;
	}

	public Collection<Cord> cordsByActivity(int activityId) {
		Collection<Cord> result = new LinkedList<Cord>();
		Collection<PieceEquipment> allPE;
		allPE = cordRepository.cordsByActivity(activityId);
		for (PieceEquipment ref : allPE) {
			if (ref instanceof domain.Cord)

				result.add((Cord) ref);

		}

		return result;
	}

	public Integer numberTotalCords() {
		Integer result;
		result = cordRepository.numberTotalCords();
		return result;
	}

	public Double averageCordsByActivity() {
		Double result;
		Double numera;
		Double denomi;
		Collection<Cord> cords = new LinkedList<Cord>();
		Collection<Activity> activities;
		activities = activityService.findAll();
		for (Activity a : activities) {
			for (PieceEquipment ref : a.getPieceEquipments()) {
				if (ref instanceof domain.Cord)

					cords.add((Cord) ref);

			}

		}
		numera = (double) cords.size();
		denomi = (double) activities.size();

		result = numera / denomi;
		Math.round(result);
		return result;
	}

}
