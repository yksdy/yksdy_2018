"""Unit tests for all yahoo.search.local classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.local import *


__revision__ = "$Id: local.py,v 1.2 2007/02/28 05:20:08 zwoop Exp $"
__version__ = "$Revision: 1.2 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Tue Feb 27 15:23:03 MST 2007"


#
# Test for local search
#
class LocalSearchTestCase(SearchServiceTest, unittest.TestCase):
    """LocalSearchTestCase - LocalSearch Unit tests.
    """
    SERVICE = LocalSearch
    NUM_PARAMS = 18

    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.query = "yahoo"
        self.srch.zip = "94019"
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)

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
