package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CanyonRepository;
import domain.Activity;
import domain.Administrator;
import domain.Canyon;
import domain.Comment;
import domain.Customer;
import domain.GPSCoordinates;
import domain.Request;
import forms.CanyonEditForm;
import forms.CanyonRegisterForm;

@Service
@Transactional
public class CanyonService {

	// Managed repository -------------------
	@Autowired
	private CanyonRepository canyonRepository;
	// Supporting Services ------------------
	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private ActivityService activityService;

	// COnstructors -------------------------------------------------------
	public CanyonService() {
		super();
	}

	// Simple CRUD methods--------------------------------------------------

	public Canyon create() {
		Canyon result;
		Collection<Activity> activity;
		Collection<String> pictures;

		Collection<Comment> comments;

		Administrator administrator;

		result = new Canyon();

		activity = new ArrayList<Activity>();
		result.setActivities(activity);

		pictures = new ArrayList<String>();
		result.setPictures(pictures);

		administrator = administratorService.findByPrincipal();
		result.setAdministrator(administrator);

		comments = new ArrayList<Comment>();
		result.setComments(comments);

		return result;
	}

	public Collection<Canyon> findAll() {
		Collection<Canyon> result;

		result = canyonRepository.findAll();

		return result;
	}

	public Canyon findOne(int canyonId) {
		Canyon result;

		result = canyonRepository.findOne(canyonId);

		return result;
	}

	public void save(Canyon canyon) {
		Assert.notNull(canyon);

		canyonRepository.saveAndFlush(canyon);
	}

	public void delete(Canyon canyon) {
		Assert.notNull(canyon);
		checkPrincipal(canyon.getAdministrator());
		canyon.getComments().clear();
		canyonRepository.delete(canyon);
	}

	// Other Methods--------------------
	private void checkPrincipal(Administrator admin) {
		Administrator administrator;

		administrator = administratorService.findByPrincipal();
		Assert.isTrue(administrator != null);

		Assert.isTrue(administrator.equals(admin));
	}

	public Canyon canyonByActivity(int activityId) {
		Canyon result;

		result = canyonRepository.canyonByActivity(activityId);

		return result;
	}

	public Collection<Canyon> findCanyonsByAdministrator() {
		Collection<Canyon> result;
		Administrator administrator;
		administrator = administratorService.findByPrincipal();

		result = canyonRepository.findCanyonByAdministrator(administrator
				.getId());
		return result;
	}
	
	public Canyon reconstructRegister(CanyonRegisterForm canyonRegisterForm){
		Canyon result = create();
		result.setName(canyonRegisterForm.getName());
		result.setDescription(canyonRegisterForm.getDescription());
		result.setDifficultyLevel(canyonRegisterForm.getDifficultyLevel());
		result.setRoute(canyonRegisterForm.getRoute());
		result.setPictures(canyonRegisterForm.getPictures());
		GPSCoordinates gps = new GPSCoordinates();
		gps.setAltitude(canyonRegisterForm.getAltitude());
		gps.setLatitude(canyonRegisterForm.getLatitude());
		gps.setLongitude(canyonRegisterForm.getLongitude());
		result.setGpsCoordinates(gps);
		return result;
	}
	
	public CanyonEditForm constructCanyonEdit(Canyon canyon){
		CanyonEditForm result  = new CanyonEditForm();
		result.setCanyonId(canyon.getId());
		result.setName(canyon.getName());
		result.setDescription(canyon.getDescription());
		result.setDifficultyLevel(canyon.getDifficultyLevel());
		result.setRoute(canyon.getRoute());
		result.setLatitude(canyon.getGpsCoordinates().getLatitude());
		result.setLongitude(canyon.getGpsCoordinates().getLongitude());
		result.setAltitude(canyon.getGpsCoordinates().getAltitude());
		return result;
	}
	
	public Canyon reconstructCanyonEdit(CanyonEditForm canyonEditForm) {
		Canyon result= findOne(canyonEditForm.getCanyonId());
		
		GPSCoordinates gps = new GPSCoordinates();
		gps.setAltitude(canyonEditForm.getAltitude());
		gps.setLatitude(canyonEditForm.getLatitude());
		gps.setLongitude(canyonEditForm.getLongitude());
		
		result.setGpsCoordinates(gps);
		result.setName(canyonEditForm.getName());
		result.setDescription(canyonEditForm.getDescription());
		result.setDifficultyLevel(canyonEditForm.getDifficultyLevel());
		result.setRoute(canyonEditForm.getRoute());

		return result;
	}

}
