package services;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.Assert;

import com.mchange.v1.util.UnexpectedException;

import domain.Trainer;
import forms.TrainerForm;

import utilities.AbstractTest;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class TrainerServiceTest extends AbstractTest{
	
	@Autowired
	private TrainerService trainerService;
	
	private String password;
	private String passwordRepeat;
	private String username;
	private String name;
	private String surname;
	private String phone;
	private String email;
	
	@Parameters
	public static Collection<Object[]> data() {

		return Arrays.asList(new Object[][] {
				{ "trainerTest", "trainert", "trainerTest", "Rafael", "Rodriguez", "+34644512313", "rafarod@gmail.com"},
				{ "trainerTest", "trainerTest", "", "Rafael", "Rodriguez", "+34644512313", "rafarod@gmail.com"},
				{ "trainerTest", "trainerTest", "admin", "Rafael", "Rodriguez", "+34644512313", "rafarod@gmail.com"},
				{ "trainerTest", "trainerTest", "trainerTest", "Rafael", "Rodriguez", "+34644512313", "rafarod@.com"},
				{ "trainerTest", "trainerTest", "trainerTest", "Rafael", "Rodriguez", "+34644512313", "rafarod@gmail.com"},
				{ "trainerTest", "trainerTest", "trainerTest", "Rafael", "", "+34644512313", "rafarod@gmail.com"},
				{ "trainerTest", "trainerTest", "trainerTest", "Rafael", "Rodriguez", "644512313", "rafarod@gmail.com"},
				{ "trainerTest", "trainerTest", "trainerTest", "Rafael", "Rodriguez", "+34644512313", "rafarod@gmail.com"}});

	}
	
	//7.1. Register a new trainer.
	
	public TrainerServiceTest(String password, String passwordRepeat, String username, String name, String surname, String phone, String email){
		this.password = password;
		this.passwordRepeat = passwordRepeat;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
	}
	
		//1º crear trainer con pass diferentes
	//2º crear trainer con username vacio
	//3ºcrear con pass vacio
	//4º crear con username existente
	//5º crear con un correo no valido
	//6ºcon campo username vacio
	//7ºtelefono con patron erroneo
	//8º se crea exitosamente
	
	@Test
	public void testCreateTrainer() {
		authenticate("admin");
		try {

			TrainerForm trainerForm;
			
			trainerForm = new TrainerForm();
			
			trainerForm.setPassword(password);
			trainerForm.setConfirmPassword(passwordRepeat);
			trainerForm.setUsername(username);
			trainerForm.setName(name);
			trainerForm.setSurname(surname);
			trainerForm.setPhone(phone);
			trainerForm.setEmail(email);
			
			
			trainerService.reconstruct(trainerForm);

		} catch (TransactionSystemException e1) {
			System.out.println(e1);
		} catch (IllegalArgumentException e2) {
			System.out.println(e2);
		} catch (UnexpectedException e3) {
			System.out.println(e3);
			throw e3;
		}
		unauthenticate();
	}
	
	// Listing requirement 1

	@Test
	public void testFindTrainers() {
		Collection<Trainer> trainers = trainerService.findAll();
		Assert.isTrue(trainers.size() == 4);
	}


}
