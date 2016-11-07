package services;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.KayakRepository;
import domain.Activity;
import domain.Kayak;
import domain.Organiser;
import domain.PieceEquipment;

@Service
@Transactional
public class KayakService {

	// Managed repository -------------------
	@Autowired
	private KayakRepository kayakRepository;

	// Supporting Services ------------------
	@Autowired
	private OrganiserService organiserService;
	@Autowired
	private ActivityService activityService;

	// COnstructors -------------------------------------------------------
	public KayakService() {
		super();
	}

	// Simple CRUD methods--------------------------------------------------

	public Kayak create() {
		Kayak result;

		Organiser organiser;

		result = new Kayak();

		organiser = organiserService.findByPrincipal();
		result.setOrganiser(organiser);

		return result;
	}

	public Collection<Kayak> findAll() {
		Collection<Kayak> result;

		result = kayakRepository.findAll();

		return result;
	}

	public Kayak findOne(int kayakId) {
		Kayak result;

		result = kayakRepository.findOne(kayakId);

		return result;
	}

	public void save(Kayak kayak) {
		Assert.notNull(kayak);
		kayakRepository.saveAndFlush(kayak);
	}

	public void delete(Kayak kayak) {
		Assert.notNull(kayak);
		checkPrincipal(kayak.getOrganiser());

		kayakRepository.delete(kayak);
	}

	// Other Methods--------------------
	private void checkPrincipal(Organiser u) {
		Organiser organiser;

		organiser = organiserService.findByPrincipal();
		Assert.isTrue(organiser != null);

		Assert.isTrue(organiser.equals(u));
	}

	public Collection<Kayak> kayaksByOrganiserLogged() {
		Collection<Kayak> result;
		Organiser organiser;
		organiser = organiserService.findByPrincipal();
		result = kayakRepository.kayaksByOrganiserLogged(organiser.getId());
		return result;
	}

	public Collection<Kayak> kayaksByActivity(int activityId) {
		Collection<Kayak> result = new LinkedList<Kayak>();
		Collection<PieceEquipment> allPE;
		allPE = kayakRepository.kayaksByActivity(activityId);
		for (PieceEquipment ref : allPE) {
			if (ref instanceof domain.Kayak)

				result.add((Kayak) ref);

		}

		return result;
	}

	public Integer numberTotalKayaks() {
		Integer result;

		result = kayakRepository.numberTotalKayaks();
		return result;
	}

	public Double averageKayaksByActivity() {
		Double result;
		Double numera;
		Double denomi;
		Collection<Kayak> kayaks = new LinkedList<Kayak>();
		Collection<Activity> activities;
		activities = activityService.findAll();
		for (Activity a : activities) {
			for (PieceEquipment ref : a.getPieceEquipments()) {
				if (ref instanceof domain.Kayak)

					kayaks.add((Kayak) ref);

			}

		}
		numera = (double) kayaks.size();
		denomi = (double) activities.size();

		result = numera / denomi;
		Math.round(result);
		return result;
	}

}
