package ict4mpower.openid;

import org.apache.wicket.markup.html.WebPage;

public class OpenIdCallbackPage extends WebPage {
	
	public OpenIdCallbackPage() {
		OpenIdConsumer consumer = OpenIdConsumer.get(getApplication());
		consumer.finishLogin(getRequest(), this);
	}
}
