package ict4mpower;

import javax.xml.transform.Templates;

import org.apache.wicket.protocol.http.WebApplication;

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
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}
	
	public Class<Template> getTemplate()
	{
		return Template.class;
		
	}
}
