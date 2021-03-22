package com.bean;

import java.util.Date;

public class PrescriptionBean {
	
	int prescriptionid;
	int patientProfileid;
	int doctorProfileid;
	int appointmentid;
	String description;
	Date prescriptiondate;
	String generaladvice;
	String followupcomment;
	
	
	public int getPrescriptionid() {
		return prescriptionid;
	}
	public void setPrescriptionid(int prescriptionid) {
		this.prescriptionid = prescriptionid;
	}
	public int getPatientProfileid() {
		return patientProfileid;
	}
	public void setPatientProfileid(int patientProfileid) {
		this.patientProfileid = patientProfileid;
	}
	public int getDoctorProfileid() {
		return doctorProfileid;
	}
	public void setDoctorProfileid(int doctorProfileid) {
		this.doctorProfileid = doctorProfileid;
	}
	public int getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPrescriptiondate() {
		return prescriptiondate;
	}
	public void setPrescriptiondate(Date prescriptiondate) {
		this.prescriptiondate = prescriptiondate;
	}
	public String getGeneraladvice() {
		return generaladvice;
	}
	public void setGeneraladvice(String generaladvice) {
		this.generaladvice = generaladvice;
	}
	public String getFollowupcomment() {
		return followupcomment;
	}
	public void setFollowupcomment(String followupcomment) {
		this.followupcomment = followupcomment;
	}
	
	
	


}
