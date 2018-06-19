//$Id: Sky.java 11282 2007-03-14 22:05:59Z epbernard $
package org.hibernate.test.annotations;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Emmanuel Bernard
 */
@Entity
@Table(name = "tbl_sky",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"month", "day"})}
)
public class Sky implements Serializable {
	@Id
	protected Long id;
	@Column(unique = true, columnDefinition = "varchar(250)")
	protected String color;
	protected String day;
	@Column(name = "MONTH")
	protected String month;
	static protected String area;
}
