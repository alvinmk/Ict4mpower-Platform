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
package ict4mpower;

import layout.Template;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.openid4java.OpenIDException;
import org.openid4java.discovery.Identifier;
import authentication.AuthStrategy;
import authentication.LoginPage;
import authentication.OpenIdCallbackPage;
import authentication.OpenIdConsumer;


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
	
	public AppSession newSession(Request req, Response res){
		return new AppSession(req);		
	}
	

	@Override
	protected void init() {
		super.init();
		//mountBookmarkablePage("/openid/finish", OpenIdCallbackPage.class);
		//mount(new MountedMapper("/openid/finish", OpenIdCallbackPage.class));
		mountPage("/openid/finish", OpenIdCallbackPage.class);
		
		
		
		getSecuritySettings().setAuthorizationStrategy(new AuthStrategy());
		OpenIdConsumer consumer = new OpenIdConsumer("http://localhost:8080/template") {
			
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
