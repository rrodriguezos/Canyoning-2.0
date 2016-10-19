/* UserController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.CustomerService;
import domain.Customer;
import forms.CustomerRegisterForm;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ActivityService activityService;

	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// List -----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Customer> customers;

		customers = customerService.findAll();

		result = new ModelAndView("customer/list");
		result.addObject("customers", customers);
		result.addObject("requestURI", "customer/list.do");

		return result;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		CustomerRegisterForm customerRegisterForm;

		customerRegisterForm = new CustomerRegisterForm();

		result = new ModelAndView("customer/register");
		result.addObject("customerRegisterForm", customerRegisterForm);
		return result;
	}

	// Save ----------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView register(
			@Valid CustomerRegisterForm customerRegisterForm,
			BindingResult binding) {
		ModelAndView result;
		Customer customer;
		Boolean verificarContrasenas;

		verificarContrasenas = customerRegisterForm.getPassword().equals(
				customerRegisterForm.getConfirmPassword());

		if (binding.hasErrors() || !verificarContrasenas
				|| !customerRegisterForm.getAccept()) {
			result = createEditModelAndView(customerRegisterForm);
			if (!verificarContrasenas) {
				result.addObject("message2", "register.commit.password");
			}
			if (!customerRegisterForm.getAccept()) {
				result.addObject("message2", "register.commit.condition");
			}
		} else {
			try {
				customer = customerService.reconstruct(customerRegisterForm);
				customerService.save(customer);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = new ModelAndView("customer/register");
				result.addObject("customerRegisterForm", customerRegisterForm);

				if (oops instanceof DataIntegrityViolationException) {
					result.addObject("message2",
							"register.commit.duplicatedUsername");
				} else {
					result.addObject("message2", "register.commit.error");
				}
			}
		}

		return result;
	}

	// // Edit ----------------------------------------------------------------
	// @RequestMapping(value = "/edit", method = RequestMethod.GET)
	// public ModelAndView edit() {
	// ModelAndView result;
	// CustomerRegisterForm customerRegisterForm;
	//
	// customerRegisterForm = customerService.copyCustomer();
	// Assert.notNull(customerRegisterForm);
	// result = new ModelAndView("customer/edit");
	//
	// result.addObject("customerRegisterForm", customerRegisterForm);
	//
	// return result;
	// }

	// // Save ----------------------------------------------------------------
	// @RequestMapping(value = "/edit", method = RequestMethod.POST, params =
	// "save")
	// public ModelAndView save(@Valid CustomerRegisterForm
	// customerRegisterForm,
	// BindingResult binding) {
	// ModelAndView result;
	// Boolean verificarContrasenas;
	// Boolean contrasActual;
	//
	// verificarContrasenas = customerRegisterForm.getPassword().equals(
	// customerRegisterForm.getConfirmPassword());
	//
	// contrasActual = customerService.passActual(customerRegisterForm);
	//
	// if (binding.hasErrors() || !verificarContrasenas || !contrasActual) {
	// result = new ModelAndView("customer/edit");
	// result.addObject("customerRegisterForm", customerRegisterForm);
	// if (!verificarContrasenas) {
	// result.addObject("message2", "register.commit.password");
	// }
	// if (!contrasActual) {
	// result.addObject("message2", "register.commit.passwordPast");
	// }
	// } else {
	// try {
	// customerService.reconstructPerfilCustomer(customerRegisterForm);
	// result = new ModelAndView("redirect:/");
	// } catch (Throwable oops) {
	// result = new ModelAndView("customer/edit");
	// result.addObject("customerRegisterForm", customerRegisterForm);
	// result.addObject("message2", "register.commit.error");
	// }
	// }
	//
	// return result;
	// }

	// // Display
	// -----------------------------------------------------------------
	// @RequestMapping(value = "/display", method = RequestMethod.GET)
	// public ModelAndView display(int userId) {
	// ModelAndView result;
	// Organiser user;
	// Collection<Canyon> activities;
	//
	// user = customerService.findOne(userId);
	// activities = activityService.findTripByUser(userId);
	//
	// result = new ModelAndView("user/display");
	// result.addObject("user", user);
	// result.addObject("trips", activities);
	//
	// return result;
	// }

	// Ancillary methods
	// --------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			CustomerRegisterForm customerForm) {
		ModelAndView result;

		result = createEditModelAndView(customerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			CustomerRegisterForm customerForm, String message) {
		ModelAndView result;

		result = new ModelAndView("customer/register");

		result.addObject("customer", customerForm);
		result.addObject("message2", message);
		return result;
	}
}