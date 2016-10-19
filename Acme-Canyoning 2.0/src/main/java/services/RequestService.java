package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Activity;
import domain.Customer;
import domain.Request;
import domain.Request.RequestState;

@Service
@Transactional
public class RequestService {

	// Managed repository -------------------
	@Autowired
	private RequestRepository requestRepository;

	// Supporting Services ------------------
	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ActivityService activityService;

	// COnstructors -------------------------
	public RequestService() {
		super();
	}

	// Simple CRUD methods--------------------

	public Request create(Activity activity, Customer customer) {
		Request result;

		result = new Request();

		Assert.isTrue(customer == customerService.findByPrincipal());
		result.setMomentPending(new Date(System.currentTimeMillis() - 1000));
		result.setRequestState(RequestState.PENDING);
		result.setCustomer(customer);
		activity.getRequests().add(result);

		return result;
	}

	public void save(Request request) {
		Assert.notNull(request);
		requestRepository.saveAndFlush(request);
	}

	public Request findOne(int requestId) {
		Assert.isTrue(requestId != 0);

		Request result;

		result = requestRepository.findOne(requestId);
		Assert.notNull(result);

		return result;
	}

	// Other Methods--------------------

	public Collection<Request> requestByCustomer() {
		Collection<Request> result;
		Customer customer;
		customer = customerService.findByPrincipal();

		result = requestRepository.requestByCustomer(customer.getId());
		return result;
	}

	public Collection<Request> requestAcceptedByActivity(int activityId) {
		Collection<Request> result;

		result = requestRepository.requestAcceptedByActivity(activityId);

		return result;
	}

	public Collection<Request> requestPendingByCustomer() {
		Collection<Request> result;
		Customer customer = customerService.findByPrincipal();

		result = requestRepository.requestPendingByCustomer(customer.getId());

		return result;

	}

	public Collection<Request> requestRejectByCustomer() {
		Collection<Request> result;
		Customer customer = customerService.findByPrincipal();

		result = requestRepository.requestRejectByCustomer(customer.getId());

		return result;

	}

	public Collection<Request> requestAcceptedByCustomer() {
		Collection<Request> result;
		Customer customer = customerService.findByPrincipal();

		result = requestRepository.requestAcceptedByCustomer(customer.getId());

		return result;

	}

	public Request requestActivityByCustomer(int activityId, Customer customer) {
		Assert.notNull(customer.getId());
		Request result;
		Activity activity = activityService.findOne(activityId);
		result = create(activity, customer);
		result.setActivity(activity);
		return result;

	}

	

	public Collection<Request> requestsPendingByActivity(int activityId) {
		Collection<Request> result;

		result = requestRepository.requestsPendingByActivity(activityId);

		return result;
	}

	public Boolean acceptRequest(int requestId) {
		Request request;
		Boolean accept;
		accept = false;
		Activity activity;
		request = findOne(requestId);
		activity = request.getActivity();
		if (activity.getSeatsAvailable() > 0) {
			accept = true;
			request.setRequestState(RequestState.ACCEPTED);
			request.setMomentAccepted(new Date(
					System.currentTimeMillis() - 1000));
			save(request);
			
			activityService.restaAsiento(activity);
		}else{
			request.setRequestState(RequestState.REJECTED);
			save(request);
		}
		return accept;
	}

	public boolean rejectRequest(int requestId) {
		Request request;
		Boolean accept;
		accept = false;
		request = findOne(requestId);
		request.setRequestState(RequestState.REJECTED);
		save(request);
		return accept;
	}

}
