package controllers.organiser;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CordService;
import services.KayakService;
import services.WetsuitService;
import controllers.AbstractController;
import domain.Cord;
import domain.Kayak;
import domain.Wetsuit;

@Controller
@RequestMapping("/pieceEquipment/organiser")
public class OrganiserPieceEquipmentController extends AbstractController {

	// Supporting services ----------------------------------------------------

	@Autowired
	private KayakService kayakService;

	@Autowired
	private WetsuitService wetsuitService;

	@Autowired
	private CordService cordService;

	// Constructors -----------------------------------------------------------
	public OrganiserPieceEquipmentController() {
		super();
	}

	// List -------------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;
		Collection<Kayak> kayaks;
		Collection<Cord> cords;
		Collection<Wetsuit> wetsuits;

		kayaks = kayakService.kayaksByOrganiserLogged();
		cords = cordService.cordsByOrganiserLogged();
		wetsuits = wetsuitService.wetsuitsByOrganiserLogged();

		result = new ModelAndView("pieceEquipment/organiser/list");
		result.addObject("requestUri", "/pieceEquipment/organiser/list.do");
		result.addObject("kayaks", kayaks);
		result.addObject("cords", cords);
		result.addObject("wetsuits", wetsuits);

		return result;
	}

}
