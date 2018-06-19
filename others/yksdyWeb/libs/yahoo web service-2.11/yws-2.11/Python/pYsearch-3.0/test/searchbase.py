"""Base classes for Y! Search Services

"""
import unittest
import yahoo.search


__revision__ = "$Id: searchbase.py,v 1.4 2007/02/28 05:20:08 zwoop Exp $"
__version__ = "$Revision: 1.4 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 12 15:59:04 MDT 2006"


#
# These are "shared" tests, across all the services
#
class SearchServiceTest(object):
    """Base class for our Unit tests."""
    SERVICE = None
    NUM_PARAMS = 999
    TEST_QUERY = "Yahoo"
    EXPECTED_RESULTS = -1

    def setUp(self):
        """Setup the search test system for Unit tests."""
        self.srch = self.SERVICE('YahooDemo')


    # First some very basic tests
    def testInstantiation(self):
        """Instantiate a search object (%s)""" % self.SERVICE
        self.assertNotEqual(self.srch, None)

    def testValidParams(self):
        """Verify that allowed parameters are correct (%s)""" % self.SERVICE
        params = self.srch.get_valid_params()
        self.assertEqual(len(params), self.NUM_PARAMS)
        for param in self.srch._valid_params.values():
            self.assertEqual(len(param), 6)
        self.assertNotEqual(len(self.srch.missing_params()), 0)

    def testQueryParam(self):
        """Verify that the query parameter is accepted (%s)""" % self.SERVICE
        self.srch.query = self.TEST_QUERY
        self.assertEqual(self.srch.query, self.TEST_QUERY)

    def testUnknownParam(self):
        """An exception must be raised on bad parameters (%s)""" % self.SERVICE
        self.assertRaises(yahoo.search.ParameterError, self.srch.set_param,
                          "FooBar", "Fum")

    # Now test a simple query, this will be overriden where appropriate
    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.query = self.TEST_QUERY
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)
        if self.EXPECTED_RESULTS > -1:
            self.assertTrue(res.total_results_available == self.EXPECTED_RESULTS)

        self.srch.output = "json"
        json = self.srch.get_results()
        self.assertTrue(len(json) > 0)
        self.srch.output = "php"
        json = self.srch.get_results()
        self.assertTrue(len(json) > 0)
        self.srch.output = "xml"


    def testProperties(self):
        """Test that various class properties work (%s)""" % self.SERVICE
        self.assertEqual(self.srch.appid, 'YahooDemo')
        self.assertEqual(self.srch.app_id, 'YahooDemo')
