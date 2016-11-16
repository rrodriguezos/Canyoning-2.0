package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Activity;
import domain.Canyon;
import domain.Cord;
import domain.Customer;
import domain.Kayak;
import domain.Organiser;
import domain.Request;
import domain.Wetsuit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ActivityServiceTest extends AbstractTest {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private CanyonService canyonService;

	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private HelpDatesConvertService helpService;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private WetsuitService wetsuitService;

	@Autowired
	private KayakService kayakService;
	@Autowired
	private CordService cordService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateActivity1() {
		authenticate("organiser1");
		Activity activity = activityService.create();
		activity.setTitle("titulo 1");
		activity.setDescription("Description 1");
		activity.setNumberSeats(5);
		String momentString = "2016/12/92 12:00";

		Date moment = helpService.formatFromStringToDate(momentString);

		activity.setMoment(moment);

		Canyon canyon = canyonService.findOne(97);
		activity.setCanyon(canyon);

		activityService.save(activity);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// activity campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateActivityNegative1() {
		authenticate("organiser1");
		Activity activity = activityService.create();
		activity.setTitle("");
		activity.setDescription("");
		activity.setNumberSeats(5);
		String momentString = "04/12/2016 12:00";

		Date moment = helpService.formatFromStringToDate(momentString);

		activity.setMoment(moment);

		Canyon canyon = canyonService.findOne(97);
		activity.setCanyon(canyon);

		activityService.save(activity);
		unauthenticate();
	}

	// activity con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateActivityNegative2() {
		authenticate("admin");
		Activity activity = activityService.create();
		activity.setTitle("titulo 1");
		activity.setDescription("Description 1");
		activity.setNumberSeats(5);
		String momentString = "04/12/2016 12:00";

		Date moment = helpService.formatFromStringToDate(momentString);

		activity.setMoment(moment);

		Canyon canyon = canyonService.findOne(97);
		activity.setCanyon(canyon);

		activityService.save(activity);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editActivity1() {

		authenticate("organiser1");

		Activity activity = activityService.findOne(98);

		activity.setTitle("title edit");
		activity.setDescription("Description edit");

		Assert.isTrue(activity.getTitle().equals("title edit"));
		Assert.isTrue(activity.getDescription().equals("Description edit"));

		activityService.save(activity);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editActivity2() {

		authenticate("organiser1");

		Activity activity = activityService.findOne(98);
		Canyon canyon = canyonService.findOne(98);
		activity.setCanyon(canyon);

		activity.setTitle(" ");
		activity.setDescription(" ");

		activityService.save(activity);

		unauthenticate();
	}

	// editar con actores no autentificdo
	@Test(expected = IllegalArgumentException.class)
	public void editActivity3() {

		authenticate("");

		Activity activity = activityService.findOne(87);

		activity.setTitle("title edit");
		Canyon canyon = canyonService.findOne(88);
		activity.setCanyon(canyon);
		activity.setDescription("Description edit");

		activityService.save(activity);

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindActivity() {
		Collection<Activity> activities = activityService.findAll();
		Assert.isTrue(activities.size() == 6);
	}

	// Edition requirement 1
	@Test
	public void editionActivity1() {

		authenticate("organiser1");

		Activity activity = activityService.findOne(98);

		activity.setTitle("title edition");
		Canyon canyon = canyonService.findOne(97);
		activity.setCanyon(canyon);
		activity.setDescription("Description edition");

		activityService.save(activity);

		unauthenticate();
	}

	// Edition requirement 2
	@Test
	public void editionActivity2() {

		authenticate("organiser1");

		Activity activity = activityService.findOne(98);

		activity.setTitle("title edition");
		Canyon canyon = canyonService.findOne(97);
		activity.setCanyon(canyon);
		activity.setDescription("Description edition");

		activityService.save(activity);

		unauthenticate();
	}

	// 6.4 A user who is not authenticated must be able to:
	// Search for activities using a single keyword that must appear somewhere
	// in its title,
	// its description, the name of the corresponding canyon or its description.
	@Test
	public void testSearchActivity() {
		List<Activity> activities = (List<Activity>) activityService
				.findActivityByKeyword("en");
		Assert.isTrue(activities.size() == 5);
	}

	// 6. A user who is not authenticated must be able to:
	// 3. Navigate through the activitites
	@Test
	public void testFindCanyons() {
		Collection<Activity> activities = activityService.findAll();
		Assert.isTrue(activities.size() == 6);
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES Re-instantiate
	// ----------------------------------------------------
	// 9.2 Re-instantiate an activity at a different moment.
	// Re-instantiate exitosamente
	@Test
	public void testReinstantiateActivity1() {
		authenticate("organiser1");
		Activity activity;
		Organiser organiser = organiserService.findByPrincipal();

		activity = activityService.findOne(98);

		activityService.reinstantiateMomentActivity(activity, organiser);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES Re-instantiate
	// ----------------------------------------------------
	// copia erronea
	@Test(expected = NullPointerException.class)
	public void testReinstantiateActivity2() {
		authenticate("organiser1");
		Activity activity;
		Organiser organiser = organiserService.findByPrincipal();

		activity = activityService.findOne(2498);

		activityService.reinstantiateMomentActivity(activity, organiser);

		unauthenticate();
	}

	// lo hace un actor logueado como administrador, siendo erroneo tambien
	@Test(expected = IllegalArgumentException.class)
	public void testReinstantiateActivity3() {
		authenticate("admin");
		Activity activity;
		Organiser organiser = organiserService.findByPrincipal();

		activity = activityService.findOne(98);

		activityService.reinstantiateMomentActivity(activity, organiser);

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES register customer waiting list
	// ----------------------------------------------------
	// 9.3 Register a customer who is in a waiting list to the corresponding
	// activity.
	// The system must keep track of the moment when a user is registered to an
	// activity.
	// Correctamente
	@Test
	public void testRegisterCustomerWaitingActivity1() {
		authenticate("organiser1");
		Activity activity;
		Request request;

		activity = activityService.findOne(110);
		request = requestService.findOne(111);

		activityService.acceptRequestByActivity(activity, request);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES register customer waiting list
	// ----------------------------------------------------
	// activity sin asientos
	@Test(expected = IllegalArgumentException.class)
	public void testRegisterCustomerWaitingActivity2() {
		authenticate("organiser1");
		Activity activity;
		Request request;

		activity = activityService.findOne(98);
		request = requestService.findOne(42);

		activityService.acceptRequestByActivity(activity, request);

		unauthenticate();
	}

	// lo hace un actor logueado como administrador, siendo erroneo tambien
	@Test(expected = IllegalArgumentException.class)
	public void testRegisterCustomerWaitingActivity3() {
		authenticate("admin");
		Activity activity;
		Request request;

		activity = activityService.findOne(98);
		request = requestService.findOne(38);

		activityService.acceptRequestByActivity(activity, request);

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES request a seat on activity
	// ----------------------------------------------------
	// 8.1 Request a seat regarding an activity. This takes him or her into a
	// waiting list until the corresponding organiser registers him or her to
	// the activity, if possible. The maximum number of seats offered must
	// never be exceeded.
	// The system must keep track of the moment when a customer enters a waiting
	// list.
	// Correctamente
	@Test
	public void testRequestCustomerSeatActivity1() {
		authenticate("customer1");
		int actual = requestService.findAll().size();
		Activity activity;
		Customer customer;
		customer = customerService.findByPrincipal();

		activity = activityService.findOne(110);

		requestService.requestActivityByCustomer(activity.getId(), customer);
		int expected = requestService.findAll().size();
		Assert.isTrue(expected == actual + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES register customer waiting list
	// ----------------------------------------------------
	// activity inexistente
	@Test(expected = NullPointerException.class)
	public void testRequestCustomerSeatActivity2() {
		authenticate("customer1");
		Activity activity;
		Customer customer;
		customer = customerService.findByPrincipal();

		activity = activityService.findOne(88847);

		requestService.requestActivityByCustomer(activity.getId(), customer);

		unauthenticate();
	}

	// lo hace un actor logueado como administrador, siendo erroneo tambien
	@Test(expected = IllegalArgumentException.class)
	public void testRequestCustomerSeatActivity3() {
		authenticate("admin");
		Activity activity;
		Customer customer;
		customer = customerService.findByPrincipal();

		activity = activityService.findOne(39);

		requestService.requestActivityByCustomer(activity.getId(), customer);

		unauthenticate();
	}

	// Listing requirement 1
	// 8.2 List the activities in which he or shes been registered.

	@Test
	public void testFinActivititiesRegister() {
		authenticate("customer2");
		Customer customer;

		customer = customerService.findByPrincipal();

		Collection<Activity> activititiesRegister = activityService
				.activitiesAcceptedByRequestCustomer(customer.getId());
		Assert.isTrue(activititiesRegister.size() == 2);
		unauthenticate();
	}

	// 19.2 Describe the pieces of equipment that an activity requires.
	// Positive
	@Test
	public void addPieceEquipmentActivity1() {

		authenticate("organiser1");

		Activity activity = activityService.findOne(98);
		int numberPEquipmentBefore = activity.getPieceEquipments().size();
		Kayak kayak;
		Cord cord;
		Wetsuit wetsuit;

		kayak = kayakService.findOne(88);
		cord = cordService.findOne(96);
		wetsuit = wetsuitService.findOne(92);

		activity.getPieceEquipments().add(kayak);
		activity.getPieceEquipments().add(cord);
		activity.getPieceEquipments().add(wetsuit);

		activityService.save(activity);
		int numberPEquipmentAfter = activity.getPieceEquipments().size();
		Assert.isTrue(numberPEquipmentAfter == numberPEquipmentBefore + 3);
		unauthenticate();
	}

	// Negative
	// actor que no es organizador
	@Test(expected = IllegalArgumentException.class)
	public void addPieceEquipmentActivity2() {

		authenticate("admin");

		Activity activity = activityService.findOne(98);
		int numberPEquipmentBefore = activity.getPieceEquipments().size();
		Kayak kayak;
		Cord cord;
		Wetsuit wetsuit;

		kayak = kayakService.findOne(88);
		cord = cordService.findOne(96);
		wetsuit = wetsuitService.findOne(92);

		activity.getPieceEquipments().add(kayak);
		activity.getPieceEquipments().add(cord);
		activity.getPieceEquipments().add(wetsuit);

		activityService.save(activity);
		int numberPEquipmentAfter = activity.getPieceEquipments().size();
		Assert.isTrue(numberPEquipmentAfter == numberPEquipmentBefore + 3);
		unauthenticate();
	}

	// Negative
	// Actividad inexistente
	@Test(expected = NullPointerException.class)
	public void addPieceEquipmentActivity3() {

		authenticate("organiser1");

		Activity activity = activityService.findOne(887);
		int numberPEquipmentBefore = activity.getPieceEquipments().size();
		Kayak kayak;
		Cord cord;
		Wetsuit wetsuit;

		kayak = kayakService.findOne(88);
		cord = cordService.findOne(96);
		wetsuit = wetsuitService.findOne(92);

		activity.getPieceEquipments().add(kayak);
		activity.getPieceEquipments().add(cord);
		activity.getPieceEquipments().add(wetsuit);

		activityService.save(activity);
		int numberPEquipmentAfter = activity.getPieceEquipments().size();
		Assert.isTrue(numberPEquipmentAfter == numberPEquipmentBefore + 3);
		unauthenticate();
	}
}
