//$Id$
package org.hibernate.test.annotations.collectionelement;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Embedded;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.FilterDef;

/**
 * @author Emmanuel Bernard
 */
@Entity
@FilterDef(name="selectedLocale", parameters={ @ParamDef( name="param", type="string" ) } )
public class TestCourse {

	private Long testCourseId;

	private LocalizedString title;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getTestCourseId() {
		return testCourseId;
	}

	public void setTestCourseId(Long testCourseId) {
		this.testCourseId = testCourseId;
	}

	@Embedded
	public LocalizedString getTitle() {
		return title;
	}

	public void setTitle(LocalizedString title) {
		this.title = title;
	}
}