package ict4mpower;

import ict4mpower.openid.OpenIdCallbackPage;
import ict4mpower.openid.OpenIdConsumer;
import javax.xml.transform.Templates;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.openid4java.OpenIDException;
import org.openid4java.discovery.Identifier;

import template.Template;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see ict4mpower.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<Template> getHomePage()
	{
		return Template.class;
	}
	
	@Override
	public AppSession newSession(Request req, Response res){
		return new AppSession(req);		
	}
	

	@Override
	protected void init() {
		super.init();
		mountBookmarkablePage("/openid/finish", OpenIdCallbackPage.class);
		getSecuritySettings().setAuthorizationStrategy(new AuthStrategy());
		OpenIdConsumer consumer = new OpenIdConsumer("http://anomie.ftpaccess.cc:8080/template") {
			
			@Override
			protected void onLoginSuccessful(Identifier identifier, Page page) {
				AppSession session = (AppSession) page.getSession();
				session.setUserID(identifier.getIdentifier());
				if (!page.continueToOriginalDestination()) {
					page.setResponsePage(getHomePage());
				}
			}

			@Override
			protected void onLoginFailed(String identity, OpenIDException cause, Page page) {
				LoginPage login = new LoginPage();
				login.error("OpenID Authentication Failed");
				page.setResponsePage(login);
			}

		};
		consumer.init(this);
	}
	
}
