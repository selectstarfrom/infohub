package com.demo.infohub.webapp.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.infohub.serviceapi.constants.Constants;
import com.demo.infohub.serviceapi.dto.EmployeeDTO;
import com.demo.infohub.serviceimpl.services.EmployeeServiceImpl;

/**
 * @author imfroz
 *
 */
@ManagedBean(name = "indexPageView")
@ViewScoped
public class IndexPageView extends AbstractBaseBean implements Serializable {

	private static final long serialVersionUID = -4607567529855886049L;

	private String username = "admin";

	private String password;

	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostConstruct
	public void init() {
		super.init();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();

		redirect("/views/index.xhtml");

	}

	public void login() {

		System.out.println();
		FacesMessage message = null;
		boolean loggedIn = false;

		EmployeeDTO lUser = employeeService.getByEmailIdAndPassword(getUsername(), getPassword());

		if (lUser != null) {
			loggedIn = true;
			storeObjectToSession(Constants.LOGGED_IN_USER, lUser);
			storeToSession(Constants.AUTHENTICATED, "YES");
			FacesContext ctx = FacesContext.getCurrentInstance();

			redirect("/views/secured/employee/pg-employee.xhtml");

			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	}
}
