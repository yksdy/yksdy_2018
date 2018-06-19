//$Id: Woody.java 11282 2007-03-14 22:05:59Z epbernard $
package org.hibernate.test.annotations.access;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.AccessType;

/**
 * @author Emmanuel Bernard
 */
@MappedSuperclass
@AccessType("property")
public class Woody extends Thingy {
	private String color;
	private String name;
	public boolean isAlive; //shouldn't be persistent

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
