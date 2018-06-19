//$
package org.hibernate.test.annotations.collectionelement;

import java.util.Map;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.MapKey;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Type;

/**
 * @author Emmanuel Bernard
 */
@Entity
public class Matrix {
	@Id
	@GeneratedValue
	private Integer id;
	@MapKey(type = @Type(type="integer") )
	@CollectionOfElements
	@Type(type = "float")
	private Map<Integer, Float> values = new HashMap<Integer, Float>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Integer, Float> getValues() {
		return values;
	}

	public void setValues(Map<Integer, Float> values) {
		this.values = values;
	}
}
