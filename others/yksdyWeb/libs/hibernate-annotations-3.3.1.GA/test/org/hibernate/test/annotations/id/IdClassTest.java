//$Id: IdClassTest.java 11282 2007-03-14 22:05:59Z epbernard $
package org.hibernate.test.annotations.id;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.test.annotations.TestCase;

/**
 * @author Emmanuel Bernard
 */
public class IdClassTest extends TestCase {

	public void testIdClassInSuperclass() throws Exception {
		Tower tower = new Tower();
		tower.latitude = 10.3;
		tower.longitude = 45.4;
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( tower );
		s.flush();
		s.clear();
		Location loc = new Location();
		loc.latitude = tower.latitude;
		loc.longitude = tower.longitude;
		assertNotNull( s.get( Tower.class, loc ) );
		tx.rollback();
		s.close();
	}

	protected Class[] getMappings() {
		return new Class[]{
				Tower.class
		};
	}
}
