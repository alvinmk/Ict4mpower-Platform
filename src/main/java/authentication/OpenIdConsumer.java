package authentication;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.RedirectToUrlException;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.servlet.AbortWithWebErrorCodeException;
import org.apache.wicket.util.string.Strings;
import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.openid4java.message.sreg.SRegMessage;
import org.openid4java.message.sreg.SRegRequest;
import org.openid4java.message.sreg.SRegResponse;

import com.google.common.collect.MapMaker;


public abstract class OpenIdConsumer {

	final Logger log = Logger.getLogger(OpenIdConsumer.class);
	
	private static MetaDataKey<OpenIdConsumer> KEY=new MetaDataKey<OpenIdConsumer>() {
	};
	
	public static OpenIdConsumer get(Application application) {
		return application.getMetaData(KEY);
	}
	
	private Map<String, ConsumerManager> consumers;
	public ConsumerManager getConsumerManager(String identity){
		return consumers.get(identity);
	}
	private String applicationUrl;

	public OpenIdConsumer(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public void init(WebApplication application) {
		consumers = new MapMaker().expireAfterWrite(5, TimeUnit.MINUTES).makeMap();
		application.mountBookmarkablePage("/openid/finish", OpenIdCallbackPage.class);
		application.setMetaData(KEY, this);
	}

	public void startLogin(String identity) throws OpenIDException {
		identity = "http://localhost:8081/openid-provider-sample-app/user";//id/" +identity;
		final Logger log = Logger.getLogger(OpenIdConsumer.class);
		log.info(identity);
		consumers.remove(identity);

		ConsumerManager manager;
		manager = new ConsumerManager();
		List<?> discoveries = manager.discover(identity);

		String callbackUrl = applicationUrl + "/"
				+ RequestCycle.get().urlFor(OpenIdCallbackPage.class, null);
		callbackUrl += callbackUrl.contains("?") ? "&" : "?";
		callbackUrl += "wicket.identity=" + identity;
		log.info("CallbackURL: " +callbackUrl);
		DiscoveryInformation discovered = manager.associate(discoveries);
		AuthRequest req = manager.authenticate(discovered, callbackUrl);
		FetchRequest fetchRequest = FetchRequest.createFetchRequest();
		fetchRequest.addAttribute("roles", "http://makotogroup.com/schema/1.0/roles", false);
		req.addExtension(fetchRequest);
		
		SRegRequest sRegRequest =  SRegRequest.createFetchRequest();
		sRegRequest.addAttribute("email", false);
		sRegRequest.addAttribute("fullname", false);
		sRegRequest.addAttribute("dob", false);
		sRegRequest.addAttribute("postcode", false);
		req.addExtension(sRegRequest);
		
		consumers.put(identity, manager);
		
		throw new RedirectToUrlException(req.getDestinationUrl(true));
	}

	public void finishLogin(Request req, Page page) {
		HttpServletRequest request = ((WebRequest) req).getHttpServletRequest();
			
		
		String identity = request.getParameter("wicket.identity");
		if (Strings.isEmpty(identity)) {
			throw new AbortWithWebErrorCodeException(500);
		}

		ConsumerManager manager = consumers.get(identity);
		if (manager == null) {
			throw new AbortWithWebErrorCodeException(500);
		}
		consumers.remove(manager);

		ParameterList response = new ParameterList(request.getParameterMap());

		StringBuffer url = request.getRequestURL();
		if (!Strings.isEmpty(request.getQueryString())) {
			url.append("?").append(request.getQueryString());
		}

		try {
			VerificationResult verification = manager.verify(url.toString(),
					response, null);
			Identifier verified = verification.getVerifiedId();
			AuthSuccess authSuccess =(AuthSuccess) verification.getAuthResponse();
			
			 log.info(authSuccess.getExtensions());	
			 
			 if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX))
             {
                 FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);
                 List roles = fetchResp.getAttributeValues("roles");
                 String role = (String) roles.get(0);
                 log.info("Roles " +role);
                 
             }
			 
			 if (authSuccess.hasExtension(SRegResponse.OPENID_NS_SREG)){
			//	 log.info("SREG PRESENT");
			//	 FetchResponse fp = (FetchResponse) authSuccess.getExtension(SRegResponse.OPENID_NS_SREG);
			//	 log.info( fetchResp.getAttributes().toString());
			 }
					

			if (verified == null) {
				throw new OpenIDException("Authentication failed");
			}
			onLoginSuccessful(verified, page);
		} catch (OpenIDException e) {
			onLoginFailed(identity, e, page);
		}

	}

	protected abstract void onLoginSuccessful(Identifier identifier, Page page);
	protected abstract void onLoginFailed(String identity, OpenIDException cause, Page page);

}
