//$Id$
package org.hibernate.test.annotations.naturalid;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.test.annotations.TestCase;

/**
 * @author Emmanuel Bernard
 */
public class NaturalIdTest extends TestCase {

	public void testNaturalId() {
		Citizen c = new Citizen();
		c.setFirstname( "Emmanuel" );
		c.setLastname( "Bernard" );
		c.setSsn( "1234" );
		State ste = new State();
		ste.setName( "Ile de France");
		c.setState( ste);
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( ste );
		s.persist( c );
		s.flush();
		s.clear();
		List results = s.createCriteria( Citizen.class )
				.add( Restrictions.naturalId().set( "ssn", "1234" ).set( "state", ste ) )
				.list();
		assertEquals( 1, results.size() );

		tx.rollback();
		s.close();
	}

	protected Class[] getMappings() {
		return new Class[] {
        		Citizen.class,
				State.class
		};
	}
}
