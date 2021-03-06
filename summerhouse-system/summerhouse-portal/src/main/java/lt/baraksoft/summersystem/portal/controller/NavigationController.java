package lt.baraksoft.summersystem.portal.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.portal.helper.MailService;

/**
 * Created by LaurynasC on 2016-04-18.
 */
@Stateless
@Named
public class NavigationController implements Serializable {
	private static final long serialVersionUID = 2582693109850487119L;

	@Inject
	private UserLoginController userLoginController;

	@Inject
	private ConfigurationEntryDao configurationEntryDao;

	@Inject
	private ReservationPaymentController reservationPaymentController;

	private int currentTab = 1;

	@PostConstruct
	public void init() {
		// Jei kam reiks pasikurt CE (max naudotoju ir metinis mokestis) mock'as
		// uzkomentintas
		// ConfigurationEntry entry = new ConfigurationEntry();
		// entry.setType(ConfigurationEntryEnum.MAX_USERS_SIZE);
		// entry.setValue("100");
		// configurationEntryDao.save(entry);
		// entry = new ConfigurationEntry();
		// entry.setType(ConfigurationEntryEnum.YEARLY_PAYMENT_PRICE);
		// entry.setValue("20");
		// configurationEntryDao.save(entry);
	}

	public String getActiveClass(int tab) {
		return tab == currentTab ? "active" : "";
	}

	public Boolean checkActiveClass(){
		return currentTab == 4 || currentTab == 9;
	}

	public String goToUsersList() {
		currentTab = 2;
		return "/allusers.xhtml?faces-redirect=true";
	}

	public String goToLoggedUserInfo() {
		currentTab = 3;
		return "/loggedUserDetails.xhtml?faces-redirect=true";
	}

	public String goToUserRegistration() {
		return "/user_registration.xhtml?faces-redirect=true";
	}

	public String goToMain() {
		currentTab = 1;
		return "/index.xhtml?faces-redirect=true";
	}

	public String checkLoggedUser() {
		if (userLoginController.getLoggedUser() == null || !userLoginController.getLoggedUser().isApproved()){
			currentTab = 1;
			return "/signin.xhtml?faces-redirect=true";
		}
		return "";
	}

    public String checkLoggedUserWithoutApproved() {
        if (userLoginController.getLoggedUser() == null){
			currentTab = 1;
            return "/signin.xhtml?faces-redirect=true";}
        return "";
    }

	public String goToPayment() {
		return "/paymentProcess.xhtml?faces-redirect=true";
	}

	public String goToSumerhousesAdministration() {
		currentTab = 6;
		return "/summerhouse-admin.xhtml?faces-redirect=true";
	}

	public String goToUsersAdministration() {
		currentTab = 7;
		return "/user-admin.xhtml?faces-redirect=true";
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;
	}

	public ConfigurationEntryDao getConfigurationEntryDao() {
		return configurationEntryDao;
	}

	public void setConfigurationEntryDao(ConfigurationEntryDao configurationEntryDao) {
		this.configurationEntryDao = configurationEntryDao;
	}

}
