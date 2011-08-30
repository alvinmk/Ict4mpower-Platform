package ict4mpower;


import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;

import authentication.LoginPage;
import authentication.OpenIdCallbackPage;
/*
 * The authorization strategy used in the application. Sets access to pages and components.
 * 
 */
public class AuthStrategy implements IAuthorizationStrategy {

	public <T extends Component> boolean isInstantiationAuthorized(
			Class<T> componentClass) {
		//If the request is not for a page, allow it.
		if (!Page.class.isAssignableFrom(componentClass)) {
			return true;
		}
		
		//The login page is always available
		if (LoginPage.class.isAssignableFrom(componentClass)) {
			return true;
		}
		//The openidcallback page is always available
		if (OpenIdCallbackPage.class.isAssignableFrom(componentClass)) {
			return true;
		}
		//If the user is not logged in, intercept with the login page and then complete the request
		//if (((AppSession) Session.get()).getUserID() == null) {
		//	throw new RestartResponseAtInterceptPageException(LoginPage.class);
		//}
		//if nothing matches disallow.
		return true;
	}

	public boolean isActionAuthorized(Component component, Action action) {
		return true;
	}

}