package co.com.ceiba.parqueadero.business.client.soap.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.business.client.soap.TRMService;
import co.com.ceiba.parqueadero.business.exception.BusinessException;
import co.com.ceiba.parqueadero.business.exception.ExceptionConstants;
import co.com.ceiba.parqueadero.util.PropiedadConstants;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterface;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;

@Service
public class TRMServiceImpl implements TRMService {

	private String wsdlUrl;

	public String getWsdlUrl() {
		return wsdlUrl;
	}

	@Value("${" + PropiedadConstants.WSDL_TRM_URL + "}")
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	@Override
	public Float getTRM() {
		try {
			TCRMServicesInterface proxy = new TCRMServicesInterfaceProxy(getWsdlUrl());
			TcrmResponse tcrmResponse = proxy.queryTCRM(null);
			return tcrmResponse.getValue();
		} catch (Exception e) {
			throw new BusinessException(ExceptionConstants.MSG_ERROR_CONSULTANDO_TRM, e);
		}
	}

}
