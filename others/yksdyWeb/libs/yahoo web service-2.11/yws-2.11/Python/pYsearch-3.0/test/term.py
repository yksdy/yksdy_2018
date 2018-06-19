"""Unit tests for all yahoo.search.term classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.term import *


__revision__ = "$Id: term.py,v 1.2 2007/02/28 05:20:09 zwoop Exp $"
__version__ = "$Revision: 1.2 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 12 13:35:34 MDT 2006"


#
# Test for Term Extraction
#
class TermExtractionTestCase(SearchServiceTest, unittest.TestCase):
    """TermExtractionTestCase - TermExtraction Unit tests.
    """
    SERVICE = TermExtraction
    NUM_PARAMS = 4

    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.query = "Yahoo"
        self.srch.context = "Web Search APIs developers"
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
