package authentication;

import org.apache.wicket.markup.html.WebPage;

public class OpenIdCallbackPage extends WebPage {
	
	/*
	 * Handles callback from the openid provider.
	 */
	public OpenIdCallbackPage() {
		OpenIdConsumer consumer = OpenIdConsumer.get(getApplication());
		consumer.finishLogin(getRequest(), this);
	}
}
