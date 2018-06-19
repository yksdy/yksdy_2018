//$Id: Computer.java 11282 2007-03-14 22:05:59Z epbernard $
package org.hibernate.test.annotations.id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Emmanuel Bernard
 */
@Entity()
public class Computer {
	private Long id;
	private String serialNumber;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
