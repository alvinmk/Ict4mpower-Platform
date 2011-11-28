package authentication;

import ict4mpower.AppSession;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.request.*;
import org.apache.wicket.markup.html.WebPage;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;


public class OpenIdCallbackPage extends WebPage {
	private static final long serialVersionUID = -7370038018494236607L;
	final Logger log = Logger.getLogger(OpenIdCallbackPage.class);
	
	/*
	 * Handles callback from the openid provider.
	 */
	public OpenIdCallbackPage() {
		OpenIdConsumer consumer = OpenIdConsumer.get(getApplication());
		consumer.finishLogin(getRequest(), this);
	}
}
