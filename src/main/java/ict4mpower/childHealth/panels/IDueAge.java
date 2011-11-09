package ict4mpower.childHealth.panels;

import org.apache.wicket.Component;

/**
 * An interface for getting due age
 * @author Joakim Lindskog
 *
 */
public interface IDueAge {
	/**
	 * Returns the due age as a String
	 * @param parent the parent component
	 * @return the due age as a String
	 */
	String getDueAge(Component parent);
}
