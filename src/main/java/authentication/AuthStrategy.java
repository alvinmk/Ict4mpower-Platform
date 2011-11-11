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