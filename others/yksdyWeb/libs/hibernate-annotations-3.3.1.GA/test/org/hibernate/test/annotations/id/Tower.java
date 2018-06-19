//$Id: Tower.java 11282 2007-03-14 22:05:59Z epbernard $
package org.hibernate.test.annotations.id;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Emmanuel Bernard
 */
@Entity
@AttributeOverride(name = "longitude", column = @Column(name = "fld_longitude"))
public class Tower extends MilitaryBuilding {
}
