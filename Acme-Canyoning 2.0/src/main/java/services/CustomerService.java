package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import domain.CustomerComment;
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
	
	@Autowired
	private RequestService requestService;

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
		CustomerComment customerComment = new CustomerComment();
		Collection<Comment> commentsCustomerComment = new LinkedList<Comment>();

		Authority aut = new Authority();

		aut.setAuthority("CUSTOMER");
		useraccount = userAccountService.create();

		result = new Customer();
		customerComment.setComments(commentsCustomerComment);
		customerComment.setCustomer(result);
		result.setCustomerComment(customerComment);

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
		res.setName(customerForm.getName());
		res.setSurname(customerForm.getSurname());
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
		result.setName(customer.getName());
		result.setSurname(customer.getSurname());
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
		result.setName(customerForm.getName());
		result.setSurname(customerForm.getSurname());
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
	
public Double averageCustomersInWaitingList() {
		Double result;
		int customersWaitingList;
		int customersRequest;

		customersRequest = customerRepository.numberCustomersRequest();
		customersWaitingList = customerRepository.customersInWaitingList();

		result = (double) customersRequest / (double) customersWaitingList;

		return result;
	}

	public Double averageTimeRemainWaitingList() {
		Double result;
		Double sumDays = 0.0;
		int numberRequest;
		Collection<Request> allRequestsAccepted;
		
		allRequestsAccepted = requestService.allRequestAccepted();
		numberRequest = allRequestsAccepted.size();
		for(Request r : allRequestsAccepted){
			Date accepted;
			Date pending;
			long substract;
			pending	= r.getMomentPending();
			accepted = r.getMomentAccepted();
			substract = accepted.getTime()- pending.getTime();
			long difd=substract / (24 * 60 * 60 * 1000);
			sumDays = (double)difd + sumDays;		
			
		}
		result = (double) (sumDays / numberRequest);
		result = Math.round( result * 10.0 ) / 10.0;
		return result;
	}

	public Double stdTimeRemainWaitingList() {
		Double result;
		Double media;
		Double sumMedia;
		Double numerador = 0.0;
		Double alCuadrado;
		Double dentroSqrt;
		Collection<Double> points = new LinkedList<Double>();
		Collection<Request> allRequestsAccepted;
		
		allRequestsAccepted = requestService.allRequestAccepted();
		for(Request r : allRequestsAccepted){
			Date accepted;
			Date pending;
			long substract;
			pending	= r.getMomentPending();
			accepted = r.getMomentAccepted();
			substract = accepted.getTime()- pending.getTime();
			long difd=substract / (24 * 60 * 60 * 1000);
			points.add((double)difd);
			
		}
		
		media =averageTimeRemainWaitingList();


		for(Double pos : points){
			sumMedia = (pos-media);
			Math.abs(sumMedia);
			alCuadrado = Math.pow(sumMedia, 2);
			numerador = alCuadrado + numerador;
		}
		dentroSqrt=numerador / points.size();
		result = Math.sqrt(dentroSqrt);
	
		
		return result;
	}	 

}
