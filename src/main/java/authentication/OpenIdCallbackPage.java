package authentication;

import ict4mpower.AppSession;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;


public class OpenIdCallbackPage extends WebPage {
	
	/*
	 * Handles callback from the openid provider.
	 */
	public OpenIdCallbackPage() {
	  	final Logger log = Logger.getLogger(OpenIdCallbackPage.class);
		OpenIdConsumer consumer = OpenIdConsumer.get(getApplication());
		consumer.finishLogin(getRequest(), this);
		String user = ((AppSession) Session.get()).getUserID();
		//Get the roles for the user

		String roles = "";
		PostMethod post = new PostMethod("http://localhost:8081/openid-provider-sample-app/userRoles");
		post.addParameter("user", user);
		HttpClient client = new HttpClient();
		try {
			int status = client.executeMethod(post);
			roles = post.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		log.info("THE ROLE IS : "+roles);
		log.info("THE USER IS :" +user);
	}
}
