package authentication;


import org.apache.wicket.Component;
import org.apache.wicket.Page;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

/*
 * The authorization strategy used in the application. Sets access to pages and components.
 * 
 */
public class AuthStrategy implements IAuthorizationStrategy {


	
	public boolean isActionAuthorized(Component component, Action action) {
		// TODO Auto-generated method stub
		return true;
	}

	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
		
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
}