package acme.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import main.SpamManagement;

@Service
public class TextValidator {

	@Autowired
	protected SystemConfigurationRepository repository;
	
	public boolean checkSpam(final String text) {
		final SystemConfiguration sysConfig = this.repository.findSystemConfiguration();
		return SpamManagement.checkSpam(text, sysConfig.getStrongSpamTerms(), sysConfig.getStrongThreshold(), 
								sysConfig.getWeakSpamTerms(), sysConfig.getWeakThreshold());
	}
	
}
