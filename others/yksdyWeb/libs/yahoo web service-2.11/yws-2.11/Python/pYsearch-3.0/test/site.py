"""Unit tests for all yahoo.search.web classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.site import *


__revision__ = "$Id: site.py,v 1.2 2007/02/28 05:20:09 zwoop Exp $"
__version__ = "$Revision: 1.2 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Tue Feb 27 22:14:35 MST 2007"


#
# Test for PageData
#
class PageDataTestCase(SearchServiceTest, unittest.TestCase):
    """PageDataTestCase - PageData Unit tests.
    """
    SERVICE = PageData
    NUM_PARAMS = 6
    TEST_QUERY = "http://www.yahoo.com"

    def testBadParamValues(self):
        """Make sure bad parameter values are caught (%s)""" % self.SERVICE
        self.assertRaises(ValueError, self.srch.set_param, "results", "200")
        self.assertRaises(ValueError, self.srch.set_param, "start", -1)


#
# Test for InlinkData
#
class InlinkDataTestCase(SearchServiceTest, unittest.TestCase):
    """InlinkDataTestCase - InlinkData Unit tests.
    """
    SERVICE = InlinkData
    NUM_PARAMS = 7
    TEST_QUERY = "http://www.yahoo.com"


#
# Test for UpdateNotification
#
class UpdateNotificationTestCase(SearchServiceTest, unittest.TestCase):
    """UpdateNotificationTestCase - UpdateNotification Unit tests.
    """
    SERVICE = UpdateNotification
    NUM_PARAMS = 1
    GOOD_URL = "http://www.yahoo.com"
    BAD_URL = "www.yahoo.com"

    def testQueryParam(self):
        """Verify that the URL parameter is accepted (%s)""" % self.SERVICE
        self.srch.url = self.GOOD_URL
        self.assertEqual(self.srch.url, self.GOOD_URL)

    # Now test a simple query, this will be overriden where appropriate
    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.url = self.GOOD_URL
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertTrue(res.results[0].Success)


#
# Finally, run all the tests
#
if __name__ == '__main__':
    unittest.main()



#
# local variables:
# mode: python
# indent-tabs-mode: nil
# py-indent-offset: 4
# end:
