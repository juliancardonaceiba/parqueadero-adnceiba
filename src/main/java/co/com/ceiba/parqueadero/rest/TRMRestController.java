package co.com.ceiba.parqueadero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.business.client.soap.TRMService;

@RestController
@RequestMapping("/trm")
public class TRMRestController {

	private TRMService trmService;

	public TRMService getTrmService() {
		return trmService;
	}

	@Autowired
	public void setTrmService(TRMService trmService) {
		this.trmService = trmService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Float getTRM() {
		return getTrmService().getTRM();
	}

}
