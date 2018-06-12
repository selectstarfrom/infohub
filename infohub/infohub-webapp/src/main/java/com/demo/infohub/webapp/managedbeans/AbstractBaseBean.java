package com.demo.infohub.webapp.managedbeans;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.demo.infohub.serviceapi.constants.Constants;
import com.demo.infohub.serviceapi.dto.EmployeeDTO;

/**
 * @author imfroz
 *
 */
public abstract class AbstractBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12321L;

	@ManagedProperty("#{msg}")
	private ResourceBundle bundle;

	protected Logger logger = null;

	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	protected AbstractBaseBean getViewScoppedBean(String pViewScopedBean) {
		Map<String, Object> viewMap = getFacesContext().getViewRoot().getViewMap();
		Object lObject = viewMap.get(pViewScopedBean);
		if (lObject != null) {
			AbstractBaseBean viewScopedBean = (AbstractBaseBean) lObject;
			return viewScopedBean;
		} else {
			return null;
		}
	}

	protected AbstractBaseBean getSessionScopedBean(String pBeanName) {
		AbstractBaseBean sessionBean = (AbstractBaseBean) getValueFromSession(pBeanName);
		return sessionBean;
	}

	protected Map<String, Object> getSessionMap() {
		return getExternalContext().getSessionMap();
	}

	protected Object getValueFromSession(String pKey) {
		return getExternalContext().getSessionMap().get(pKey);
	}

	protected Object getValueFromRequest(String pKey) {
		return getExternalContext().getRequestParameterMap().get(pKey);
	}

	protected void storeToSession(String pKey, String pValue) {
		getExternalContext().getSessionMap().put(pKey, pValue);
	}

	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	protected void storeObjectToSession(String pKey, Object pValue) {
		getExternalContext().getSessionMap().put(pKey, pValue);
	}

	protected Double getValueFromSessionAsDouble(String pKey) {
		Object valueFromSession = getValueFromSession(pKey);
		return valueFromSession != null ? Double.parseDouble(valueFromSession.toString().trim()) : null;
	}

	protected String getStringValueFromSessionAsDouble(String pKey) {
		Object valueFromSession = getValueFromSession(pKey);
		return valueFromSession != null ? (valueFromSession + "").trim() : null;
	}

	protected Application getApplication() {
		return getFacesContext().getApplication();
	}

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected void info(String pMessage, String pClientId) {
		message(new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", pMessage), pClientId);
	}

	protected void error(String pMessage, String pClientId) {
		message(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", pMessage), pClientId);
	}

	protected void warning(String pMessage, String pClientId) {
		message(new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", pMessage), pClientId);
	}

	private void message(FacesMessage pMessage, String pClientId) {
		getFacesContext().addMessage(pClientId, pMessage);
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public String getMessage(String pName) {
		return getBundle().getString(pName);
	}

	public void setBundle(ResourceBundle pBundle) {
		bundle = pBundle;
	}

	public boolean hasAdminRights() {

		Object lValueFromSession = getValueFromSession(Constants.LOGGED_IN_USER);

		if (lValueFromSession != null) {
			EmployeeDTO lEmployeeDTO = (EmployeeDTO) lValueFromSession;
			if (Constants.ADMIN_EMAILID.equals(lEmployeeDTO.getEmail())) {
				return true;
			}

		}
		return false;
	}

	public boolean isAuthenticated() {

		Object lValueFromSession = getValueFromSession(Constants.IS_AUTHENTICATED);

		if (Constants.AUTHENTICATED.equals(lValueFromSession))
			return true;

		return false;
	}
	
	public String getLoggedInUserFullName() {

		Object lValueFromSession = getValueFromSession(Constants.LOGGED_IN_USER);

		if (lValueFromSession!=null)
			return ((EmployeeDTO)lValueFromSession).getName();

		return "";
	}

	protected void redirect(String pUrl) {
		String url = getExternalContext()
				.encodeActionURL(getApplication().getViewHandler().getActionURL(getFacesContext(), pUrl));
		try {
			getExternalContext().redirect(url);
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}
}