//$Id: BigBed.java 11282 2007-03-14 22:05:59Z epbernard $
package org.hibernate.test.annotations.access;

import javax.persistence.Entity;

/**
 * @author Emmanuel Bernard
 */
@Entity
public class BigBed extends Bed {
	public int size;
}
