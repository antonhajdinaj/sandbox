package com.spring3.advanced.service;

import com.spring3.advanced.hr.ws.EmployeeType;
import com.spring3.advanced.hr.ws.HolidayType;

public interface HolidayService {
	
	public String requestHoliday(HolidayType holiday, EmployeeType employee);

}
