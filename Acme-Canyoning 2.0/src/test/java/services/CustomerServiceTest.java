package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Customer;
import forms.CustomerRegisterForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CustomerServiceTest extends AbstractTest {

	// Service under test ------------------------
	@Autowired
	private CustomerService customerService;

	/* *****************************************************
	 * *An actor who is not authenticated must be able to:* Register to the
	 * system as a customer.
	 * ******************************************************
	 */

	/**
	 * Positive: A non authenticated registers as a customer
	 */
	@Test
	public void registerCustomerPositiveTest() {
		int actual = customerService.findAll().size();
		unauthenticate();
		CustomerRegisterForm registerForm = new CustomerRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("+34 (95) 758400");
		registerForm.setName("Customername Test");
		registerForm.setPassword("Password Test");
		registerForm.setConfirmPassword("Password Test");
		registerForm.setAccept(true);
		registerForm.setPasswordPast("Password Test");
		registerForm.setEmail("test@gmail.com");
		Customer customer = customerService.reconstruct(registerForm);
		customerService.save(customer);
		int expected = customerService.findAll().size();
		Assert.isTrue(expected == actual + 1);
	}

	/**
	 * Negative: An authenticated none attempts to register as a customer
	 */
	@Test(expected = IllegalArgumentException.class)
	@Rollback(true)
	public void registerCustomerAsAdminNegativeTest() {
		authenticate("none");
		CustomerRegisterForm registerForm = new CustomerRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("+34 (95) 758400");
		registerForm.setName("Customername Test");
		registerForm.setPassword("Password Test");
		registerForm.setConfirmPassword("Password Test");
		registerForm.setAccept(true);
		registerForm.setEmail("test@gmail.com");
		registerForm.setPasswordPast("Password Test");
		Customer customer = customerService.reconstruct(registerForm);
		customerService.save(customer);
	}

	/**
	 * Negative: A non authenticated attempts to register as a customer
	 * submitting wrong confirmation password
	 */
	@Test(expected = IllegalArgumentException.class)
	@Rollback(true)
	public void registerCustomerWrongFieldsNegativeTest() {
		unauthenticate();
		CustomerRegisterForm registerForm = new CustomerRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("+34 (95) 758400");
		registerForm.setName("Customername Test");
		registerForm.setPassword("Password Test");
		registerForm.setConfirmPassword("Different Password Test");
		Customer customer = customerService.reconstruct(registerForm);
		customerService.save(customer);
	}

	// Listing requirement 1

	@Test
	public void testFindOrganisers() {
		Collection<Customer> customers = customerService.findAll();
		Assert.isTrue(customers.size() == 3);
	}

}
