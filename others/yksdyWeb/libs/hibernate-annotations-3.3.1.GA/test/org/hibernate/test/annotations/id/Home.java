//$Id: Home.java 11282 2007-03-14 22:05:59Z epbernard $
package org.hibernate.test.annotations.id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Default sequence generation usage
 *
 * @author Emmanuel Bernard
 */
@Entity
public class Home {
	private Long id;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
