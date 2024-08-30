package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.LeadDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidAlphabetic1;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidDouble;
import com.rays.validation.ValidLong;

public class LeadForm extends BaseForm {

	@Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "Invalid name format")
	@NotEmpty(message = "Please enter contactName")

	private String contactName;

	@NotEmpty(message = "Please enter mobile")

	@Pattern(regexp = "(^$|^[6-9]\\d{9}$)", message = "Invalid input for mobileNo")
	private String mobile;

	@NotNull(message = "Please enter date")
	@ValidDate(message = "Invalid date format or value")
	private String date;

	private String statusName;

	@NotEmpty(message = "Please enter statusId")
	@ValidLong(message = "Invalid input for status id", allowEmpty = true)
	@Min(value = 1, message = "statusId should be greater than 0")
	private String statusId;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	@Override
	public BaseDTO getDto() {
		LeadDTO dto = initDTO(new LeadDTO());

		dto.setContactName(contactName);
		;
		if (date != null && !date.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(date);
				dto.setDate(parsedDate);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}

		if (mobile != null && !mobile.isEmpty()) {
			dto.setMobile(Long.parseLong(mobile));
		}

		if (statusId != null && !statusId.isEmpty()) {
			dto.setStatusId(Long.valueOf(statusId));
		}

		dto.setStatusName(statusName);

		return dto;
	}
}