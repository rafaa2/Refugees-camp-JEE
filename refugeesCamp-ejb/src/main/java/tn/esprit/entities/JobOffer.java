package tn.esprit.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.WebApplicationException;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Entity implementation class for Entity: JobOffer
 *
 */
@Entity

public class JobOffer implements Serializable {

	   
	
	private int id;
	private String description;
	private Date begindate;
	private Date enddate;
	private int contactnumber;
	private String fieldOfWork;
	private String jobTitle;
	private int salaryEstimate;
	private String companyName;
	private String companyAdress;
	private String contactEmail;
	private String contactName;
	private String title;
	private DistrictChef districtchef;
	private CampChef campchef;
	private static final long serialVersionUID = 1L;

	public JobOffer() {
		super();
	}   



	public JobOffer(String description, Date begindate, Date enddate, int contactnumber, String fieldOfWork,
			String jobTitle, int salaryEstimate, String companyName, String companyAdress, String contactEmail,
			String contactName, String title, DistrictChef districtchef, CampChef campchef) {
		super();
		this.description = description;
		this.begindate = begindate;
		this.enddate = enddate;
		this.contactnumber = contactnumber;
		this.fieldOfWork = fieldOfWork;
		this.jobTitle = jobTitle;
		this.salaryEstimate = salaryEstimate;
		this.companyName = companyName;
		this.companyAdress = companyAdress;
		this.contactEmail = contactEmail;
		this.contactName = contactName;
		this.title=title;
		this.districtchef = districtchef;
		this.campchef = campchef;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}   
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}   
	public int getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(int contactnumber) {
		this.contactnumber = contactnumber;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idDistrictchef",referencedColumnName="id",insertable=false,updatable=false)
	@JsonBackReference(value="aaa")
	public DistrictChef getDistrictchef() {
		return districtchef;
	}
	public void setDistrictchef(DistrictChef districtchef) {
		this.districtchef = districtchef;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idCampchef",referencedColumnName="id",insertable=false,updatable=false)
	@JsonBackReference
	public CampChef getCampchef() {
		return campchef;
	}
	public void setCampchef(CampChef campchef) {
		this.campchef = campchef;
	}


	public String getFieldOfWork() {
		return fieldOfWork;
	}



	public void setFieldOfWork(String fieldOfWork) {
		this.fieldOfWork = fieldOfWork;
	}



	public String getJobTitle() {
		return jobTitle;
	}



	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}



	public int getSalaryEstimate() {
		return salaryEstimate;
	}



	public void setSalaryEstimate(int salaryEstimate) {
		this.salaryEstimate = salaryEstimate;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getCompanyAdress() {
		return companyAdress;
	}



	public void setCompanyAdress(String companyAdress) {
		this.companyAdress = companyAdress;
	}



	public String getContactEmail() {
		return contactEmail;
	}



	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}



	public String getContactName() {
		return contactName;
	}



	public void setContactName(String contactName) {
		this.contactName = contactName;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	@Override
	public String toString() {
		return "JobOffer [description=" + description + ", begindate=" + begindate + ", enddate=" + enddate
				+ ", contactnumber=" + contactnumber + ", fieldOfWork=" + fieldOfWork + ", jobTitle=" + jobTitle
				+ ", salaryEstimate=" + salaryEstimate + ", companyName=" + companyName + ", companyAdress="
				+ companyAdress + ", contactEmail=" + contactEmail + ", contactName=" + contactName + ", title=" + title
				+ ", districtchef=" + districtchef + ", campchef=" + campchef + "]";
	}





	


	
   
}
