package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Customer;
import domain.Request;
import forms.CustomerRegisterForm;

@Service
@Transactional
public class CustomerService {
	// Managed repository -------------------------

	@Autowired
	private CustomerRepository customerRepository;

	// Supporting Services -------------------------
	@Autowired
	private UserAccountService userAccountService;

	// Constructors -------------------------------
	public CustomerService() {
		super();
	}

	// Simple CRUD methods -----------------------------
	public Customer create() {
		UserAccount useraccount;
		Customer result;
		Collection<Comment> comments;
		Collection<Request> requests;

		Authority aut = new Authority();

		aut.setAuthority("CUSTOMER");
		useraccount = userAccountService.create();

		result = new Customer();

		useraccount.addAuthority(aut);
		result.setUserAccount(useraccount);
		

		requests = new LinkedList<Request>();
		comments = new LinkedList<Comment>();
		result.setComments(comments);
		result.setRequests(requests);

		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;

		result = customerRepository.findAll();

		return result;
	}

	public Customer findOne(int customerId) {
		Customer result;

		result = customerRepository.findOne(customerId);

		return result;
	}

	public void save(Customer customer) {

		if (customer.getId() == 0) {
			Md5PasswordEncoder encoder;
			encoder = new Md5PasswordEncoder();

			customer.getUserAccount().setPassword(
					encoder.encodePassword(customer.getUserAccount()
							.getPassword(), null));
		}
		customer = customerRepository.saveAndFlush(customer);
		Assert.notNull(customer);
	}

	public Customer findByPrincipal() {
		UserAccount userAccount;
		Customer result;
		int id;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		id = userAccount.getId();
		result = customerRepository.findByUserAccountId(id);
		Assert.notNull(result);

		return result;

	}

	public Customer reconstruct(CustomerRegisterForm customerForm) {
		Customer res;
		res = create();
		Assert.isTrue(customerForm.getPassword().equals(
				customerForm.getConfirmPassword()));
		Assert.isTrue(customerForm.getAccept());
		res.setComments(new ArrayList<Comment>());
		res.setRequests(new ArrayList<Request>());
		res.setPhone(customerForm.getPhone());
		res.setEmail(customerForm.getEmail());
		res.getUserAccount().setUsername(customerForm.getUsername());
		res.getUserAccount().setPassword(customerForm.getPassword());

		return res;
	}

	public Customer findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Customer result;
		result = customerRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public CustomerRegisterForm copyCustomer() {
		CustomerRegisterForm result;
		Customer customer;

		result = new CustomerRegisterForm();
		customer = findByPrincipal();

		result.setId(customer.getId());
		result.setEmail(customer.getEmail());
		result.setPhone(customer.getPhone());
		result.setUsername(customer.getUserAccount().getUsername());
		result.setPassword(customer.getUserAccount().getPassword());
		result.setConfirmPassword(customer.getUserAccount().getPassword());
		result.setPasswordPast(customer.getUserAccount().getPassword());
		result.setAccept(true);

		return result;
	}

	public Boolean passActual(CustomerRegisterForm customerForm) {
		Customer customer;
		String passActual;
		Boolean result;

		customer = findByPrincipal();

		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		passActual = encoder.encodePassword(customerForm.getPasswordPast(),
				null);

		result = customer.getUserAccount().getPassword().equals(passActual);

		return result;
	}

	public void reconstructPerfilCustomer(CustomerRegisterForm customerForm) {
		Customer result;

		result = findByPrincipal();

		Assert.isTrue(customerForm.getPassword().equals(
				customerForm.getConfirmPassword()));

		result.setPhone(customerForm.getPhone());
		result.setEmail(customerForm.getEmail());
		if (customerForm.getPassword() != "") {
			Md5PasswordEncoder encoder1;
			encoder1 = new Md5PasswordEncoder();

			result.getUserAccount().setPassword(
					encoder1.encodePassword(customerForm.getPassword(), null));
		}

		Assert.isTrue(findByPrincipal().getId() == (customerForm.getId()));

		save(result);

	}
	
	//DashBoard
	
//	public Double averageCustomersInWaitingList() {
//		
//		 return customerRepository.averageCustomersInWaitingList();
//		 }

}
