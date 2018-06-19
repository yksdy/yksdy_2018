//$Id: Child.java 11640 2007-06-06 14:25:29Z anthonyHib $
package org.hibernate.test.annotations.cid;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Entity having a many to one in its pk
 *
 * @author Emmanuel Bernard
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Child {
	@EmbeddedId
	@AttributeOverride(name = "nthChild", column = @Column(name = "nth"))
	public ChildPk id;
}
