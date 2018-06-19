"""Unit tests for all yahoo.search.web classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.web import *


__revision__ = "$Id: web.py,v 1.4 2007/02/28 05:20:09 zwoop Exp $"
__version__ = "$Revision: 1.4 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 12 15:59:34 MDT 2006"


#
# Test for WebSearch
#
class WebSearchTestCase(SearchServiceTest, unittest.TestCase):
    """WebSearchTestCase - WebSearch Unit tests.
    """
    SERVICE = WebSearch
    NUM_PARAMS = 15

    def testBadParamValues(self):
        """Make sure bad parameter values are caught (%s)""" % self.SERVICE
        self.assertRaises(ValueError, self.srch.set_param, "results", "200")
        self.assertRaises(ValueError, self.srch.set_param, "start", -1)
        self.assertRaises(ValueError, self.srch.set_param, "similar_ok", 3)
        self.assertRaises(ValueError, self.srch.set_param, "format", "foo")
        self.assertRaises(ValueError, self.srch.set_param, "language", "Here")
        self.assertRaises(ValueError, self.srch.set_param, "country", "there")
        self.assertRaises(ValueError, self.srch.set_param, "license", "cc_none")
        self.assertRaises(ValueError, self.srch.set_param, "region", "qq")

    def test4DOM(self):
        """Test an alternative DOM parser, 4DOM (%s)""" % self.SERVICE
        try:
            from xml.dom.ext.reader import Sax2
            import xml.dom.Document
        except:
            pass
        else:
            srch = self.SERVICE('YahooDemo')
            srch.install_xml_parser(Sax2.Reader().fromStream)
            srch.query = "Yahoo"
            dom = srch.get_results()
            self.assertTrue(isinstance(dom, xml.dom.Document.Document))

#
# Test for context search
#
class ContextSearchTestCase(SearchServiceTest, unittest.TestCase):
    """ContextSearchTestCase - ContextSearch Unit tests.
    """
    SERVICE = ContextSearch
    NUM_PARAMS = 13

    def testBadParamValues(self):
        """Make sure bad parameter values are caught (%s)""" % self.SERVICE
        self.assertRaises(ValueError, self.srch.set_param, "results", "200")
        self.assertRaises(ValueError, self.srch.set_param, "start", -1)
        self.assertRaises(ValueError, self.srch.set_param, "similar_ok", 3)
        self.assertRaises(ValueError, self.srch.set_param, "format", "foo")
        self.assertRaises(ValueError, self.srch.set_param, "language", "Here")
        self.assertRaises(ValueError, self.srch.set_param, "country", "there")
        self.assertRaises(ValueError, self.srch.set_param, "license", "cc_none")

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
# Test for related suggestion
#
class RelatedSuggestionTestCase(SearchServiceTest, unittest.TestCase):
    """RelatedSuggestionTestCase - RelatedSuggestion Unit tests.
    """
    SERVICE = RelatedSuggestion
    NUM_PARAMS = 4
    TEST_QUERY = "yahoo"
    EXPECTED_RESULTS = 10


#
# Test for spelling suggestion
#
class SpellingSuggestionTestCase(SearchServiceTest, unittest.TestCase):
    """SpellingSuggestionTestCase - SpellingSuggestion Unit tests.
    """
    SERVICE = SpellingSuggestion
    NUM_PARAMS = 3
    TEST_QUERY = "yahok"
    EXPECTED_RESULTS = 1


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
