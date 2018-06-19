//$Id: Phone.java 11282 2007-03-14 22:05:59Z epbernard $
package org.hibernate.test.annotations.id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Emmanuel Bernard
 */
@Entity()
public class Phone {
	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Phone_Gen")
	@javax.persistence.SequenceGenerator(
			name = "Phone_Gen",
			sequenceName = "phone_seq"
	)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
