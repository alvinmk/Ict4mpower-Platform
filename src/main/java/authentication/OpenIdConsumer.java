/*
 *  This file is part of the ICT4MPOWER platform.
 *
 *  The ICT4MPOWER platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The ICT4MPOWER platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the ICT4MPOWER platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
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
import org.openid4java.message.ax.FetchResponse;
import org.openid4java.message.sreg.SRegResponse;


import com.google.common.collect.MapMaker;


public abstract class OpenIdConsumer {

	final Logger log = Logger.getLogger(OpenIdConsumer.class);
	
	private static MetaDataKey<OpenIdConsumer> KEY=new MetaDataKey<OpenIdConsumer>() {
		private static final long serialVersionUID = -3861162573902821715L;
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
		log.info("Openid application url: " +applicationUrl); 
		this.applicationUrl = applicationUrl;
	}

	public void init(WebApplication application) {
		consumers = new MapMaker().expireAfterWrite(5, TimeUnit.MINUTES).makeMap();
		application.setMetaData(KEY, this);
	}

	public void startLogin(String identity) throws OpenIDException {
		log.info("Starting login");
		//identity = "http://localhost:8081/openid-provider-sample-app/user";//id/" +identity;
		final Logger log = Logger.getLogger(OpenIdConsumer.class);
		log.info(identity);
		consumers.remove(identity);

		ConsumerManager manager;
		manager = new ConsumerManager();
				
		List<?> discoveries = manager.discover(identity);
		
		String callbackUrl = applicationUrl + "/openid/finish"; //+ RequestCycle.get().urlFor(OpenIdCallbackPage.class, null);
			
		callbackUrl += callbackUrl.contains("?") ? "&" : "?";
		callbackUrl += "wicket.identity=" + identity;
		log.info("CallbackURL: " +callbackUrl);
		log.info("Discovery info: " +discoveries.toString());
		DiscoveryInformation discovered = manager.associate(discoveries);
		AuthRequest req = manager.authenticate(discovered, callbackUrl);
		//FetchRequest fetchRequest = FetchRequest.createFetchRequest();
		//fetchRequest.addAttribute("roles", "http://makotogroup.com/schema/1.0/roles", false);
		//req.addExtension(fetchRequest);
		
		//SRegRequest sRegRequest =  SRegRequest.createFetchRequest();
		//sRegRequest.addAttribute("fullname", false);
		//req.addExtension(sRegRequest);
		
		consumers.put(identity, manager);
		
		throw new RedirectToUrlException(req.getDestinationUrl(true));
	}
	
	public ParameterList getParameterList(IRequestParameters param){
		log.info(param.toString());
		Map<String, String> parameterMap = new HashMap<String, String>();
		Set<String> keys = param.getParameterNames();
		for(String key : keys){
			parameterMap.put(key, param.getParameterValue(key).toString());
		}
		return new ParameterList(parameterMap);
	}

	public void finishLogin(Request req, Page page) {
		
		//HttpServletRequest request = (HttpServletRequest) req;
		//String identity = request.getParameter("wicket.identity");
		String identity = req.getRequestParameters().getParameterValue("wicket.identity").toString("");
		if (Strings.isEmpty(identity)) {
			log.warn("No manager for identity found");
			throw new AbortWithHttpErrorCodeException(500, identity);
		}
		log.trace("Trying to get the consumer for " +identity);
		ConsumerManager manager = consumers.get(identity);
		if (manager == null) {
			log.warn("Manager for identity is null");
			throw new AbortWithHttpErrorCodeException(500, identity);
		}
		log.trace("Removing manager");
		consumers.remove(manager);
		
		String url = applicationUrl +"/"+ req.getUrl().toString();
		log.info("url string is : " +url);
		//ParameterList response = new ParameterList(request.getParameterMap());
		//StringBuffer url = request.getRequestURL();
		//if (!Strings.isEmpty(request.getQueryString())) {
		if (!Strings.isEmpty(req.getQueryParameters().toString())) {
			url +="?"+req.getQueryParameters().toString();
		}
		log.trace("Setting up parameterlist");
		ParameterList response = getParameterList(req.getRequestParameters());
				
		try {
			VerificationResult verification = manager.verify(url,response, null);
			Identifier verified = verification.getVerifiedId();
			AuthSuccess authSuccess =(AuthSuccess) verification.getAuthResponse();
			
			log.info(authSuccess.getExtensions());	
			 
			 if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX))
             {
                 FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);
                 List<?> roles = fetchResp.getAttributeValues("roles");
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
