package ict4mpower;

import ict4mpower.openid.OpenIdCallbackPage;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;

public class AuthStrategy implements IAuthorizationStrategy {

	public <T extends Component> boolean isInstantiationAuthorized(
			Class<T> componentClass) {

		if (!Page.class.isAssignableFrom(componentClass)) {
			return true;
		}

		if (LoginPage.class.isAssignableFrom(componentClass)) {
			return true;
		}
		
		if (OpenIdCallbackPage.class.isAssignableFrom(componentClass)) {
			return true;
		}

		if (((AppSession) Session.get()).getUserID() == null) {
			throw new RestartResponseAtInterceptPageException(LoginPage.class);
		}



		return true;
	}

	public boolean isActionAuthorized(Component component, Action action) {
		return true;
	}

}