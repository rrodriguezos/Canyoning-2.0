package services;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WetsuitRepository;
import domain.Activity;
import domain.Organiser;
import domain.PieceEquipment;
import domain.Wetsuit;

@Service
@Transactional
public class WetsuitService {

	// Managed repository -------------------
	@Autowired
	private WetsuitRepository wetsuitRepository;

	// Supporting Services ------------------
	@Autowired
	private OrganiserService organiserService;
	@Autowired
	private ActivityService activityService;

	// COnstructors -------------------------------------------------------
	public WetsuitService() {
		super();
	}

	// Simple CRUD methods--------------------------------------------------

	public Wetsuit create() {
		Wetsuit result;

		Organiser organiser;

		result = new Wetsuit();

		organiser = organiserService.findByPrincipal();
		result.setOrganiser(organiser);

		return result;
	}

	public Collection<Wetsuit> findAll() {
		Collection<Wetsuit> result;

		result = wetsuitRepository.findAll();

		return result;
	}

	public Wetsuit findOne(int wetsuitId) {
		Wetsuit result;

		result = wetsuitRepository.findOne(wetsuitId);

		return result;
	}

	public void save(Wetsuit wetsuit) {
		Assert.notNull(wetsuit);
		wetsuitRepository.saveAndFlush(wetsuit);
	}

	public void delete(Wetsuit wetsuit) {
		Assert.notNull(wetsuit);
		checkPrincipal(wetsuit.getOrganiser());

		wetsuitRepository.delete(wetsuit);
	}

	// Other Methods--------------------
	private void checkPrincipal(Organiser u) {
		Organiser organiser;

		organiser = organiserService.findByPrincipal();
		Assert.isTrue(organiser != null);

		Assert.isTrue(organiser.equals(u));
	}

	public Collection<Wetsuit> wetsuitsByOrganiserLogged() {
		Collection<Wetsuit> result;
		Organiser organiser;
		organiser = organiserService.findByPrincipal();
		result = wetsuitRepository.wetsuitsByOrganiserLogged(organiser.getId());
		return result;
	}

	public Integer numberTotalWetsuits() {
		Integer result;
		result = wetsuitRepository.numberTotalWetsuits();
		return result;
	}

	public Double averageWetsuitsByActivity() {
		Double result;
		Double numera;
		Double denomi;
		Collection<Wetsuit> wetsuits = new LinkedList<Wetsuit>();
		Collection<Activity> activities;
		activities = activityService.findAll();
		for (Activity a : activities) {
			for (PieceEquipment ref : a.getPieceEquipments()) {
				if (ref instanceof domain.Wetsuit)

					wetsuits.add((Wetsuit) ref);

			}

		}
		numera = (double) wetsuits.size();
		denomi = (double) activities.size();

		result = numera / denomi;
		Math.round(result);
		return result;
	}

	public Collection<Wetsuit> wetsuitByActivity(int activityId) {
		Collection<Wetsuit> result = new LinkedList<Wetsuit>();
		Collection<PieceEquipment> allPE;
		allPE = wetsuitRepository.wetsuitsByActivity(activityId);

		for (PieceEquipment ref : allPE) {
			if (ref instanceof domain.Wetsuit)

				result.add((Wetsuit) ref);

		}
		return result;

	}
}
