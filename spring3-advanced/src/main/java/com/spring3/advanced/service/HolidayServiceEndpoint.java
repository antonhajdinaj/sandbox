package com.spring3.advanced.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.spring3.advanced.hr.ws.HolidayRequest;
import com.spring3.advanced.hr.ws.HolidayResponse;

@Endpoint
public class HolidayServiceEndpoint {
	private static final String TARGET_NAMESPACE = "http://com/realdolmen/hr/ws";
	@Autowired
	private HolidayService holidayService;

	@PayloadRoot(localPart = "HolidayRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload
	HolidayResponse requestHoliday(@RequestPayload HolidayRequest request) {
		HolidayResponse response = new HolidayResponse();
		String result = holidayService.requestHoliday(request.getHoliday(),
				request.getEmployee());
		response.setResult(result);
		return response;
	}
}