"""Unit tests for all yahoo.search.myweb classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.myweb import *


__revision__ = "$Id: myweb.py,v 1.3 2007/02/28 05:20:08 zwoop Exp $"
__version__ = "$Revision: 1.3 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 12 13:43:07 MDT 2006"


#
# Test for MyWeb List Folders
#
class ListFoldersTestCase(SearchServiceTest, unittest.TestCase):
    """ListFoldersTestCase - ListFolders Unit tests.
    """
    SERVICE = ListFolders
    NUM_PARAMS = 5

    # This is weird, but wth ...
    def testQueryParam(self):
        """Verify that the yahooid parameter is accepted (%s)""" % self.SERVICE
        self.srch.yahooid = "Yahoo"
        self.assertEqual(self.srch.yahooid, "Yahoo")

    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.yahooid = "janesmith306"
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)


#
# Tests for MyWeb List URLs
#
class ListUrlsTestCase(SearchServiceTest, unittest.TestCase):
    """ListUrlsTestCase - ListUrls Unit tests.
    """
    SERVICE = ListUrls
    NUM_PARAMS = 8

    def testQueryParam(self):
        """Verify that the yahooid parameter is accepted (%s)""" % self.SERVICE
        self.srch.yahooid = "Yahoo"
        self.assertEqual(self.srch.yahooid, "Yahoo")

    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.yahooid = "janesmith306"
        self.srch.folder = "Shared"
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)


#
# Tests for MyWeb2 UrlSearch
#
class UrlSearchTestCase(SearchServiceTest, unittest.TestCase):
    """UrlSearchTestCase - UrlSearch Unit tests.
    """
    SERVICE = UrlSearch
    NUM_PARAMS = 8

    def testQueryParam(self):
        """Verify that valid parameters ares accepted (%s)""" % self.SERVICE
        self.srch.yahooid = "Yahoo"
        self.srch.tag = "yahoo"
        self.assertEqual(self.srch.yahooid, "Yahoo")
        self.assertEqual(self.srch.tag, "yahoo")

    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.yahooid = "ysearchmyweb2"
        self.srch.tag = "yahoo"
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)

    def testValidParams(self):
        """Verify that allowed parameters are correct (%s)""" % self.SERVICE
        params = self.srch.get_valid_params()
        self.assertEqual(len(params), self.NUM_PARAMS)
        for param in self.srch._valid_params.values():
            self.assertEqual(len(param), 6)



#
# Tests for MyWeb2 TagSearch
#
class TagSearchTestCase(SearchServiceTest, unittest.TestCase):
    """TagSearchTestCase - TagSearch Unit tests.
    """
    SERVICE = TagSearch
    NUM_PARAMS = 8

    def testQueryParam(self):
        """Verify that valid parameters ares accepted (%s)""" % self.SERVICE
        self.srch.yahooid = "Yahoo"
        self.srch.url = "http://www.ogre.com/"
        self.assertEqual(self.srch.yahooid, "Yahoo")
        self.assertEqual(self.srch.url, "http://www.ogre.com/")

    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.yahooid = "ysearchmyweb2"
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)

    def testValidParams(self):
        """Verify that allowed parameters are correct (%s)""" % self.SERVICE
        params = self.srch.get_valid_params()
        self.assertEqual(len(params), self.NUM_PARAMS)
        for param in self.srch._valid_params.values():
            self.assertEqual(len(param), 6)


#
# Tests for MyWeb2 RelatedTags
#
class RelatedTagsTestCase(SearchServiceTest, unittest.TestCase):
    """RelatedTagsTestCase - RelatedTags Unit tests.
    """
    SERVICE = RelatedTags
    NUM_PARAMS = 8

    def testQueryParam(self):
        """Verify that valid parameters ares accepted (%s)""" % self.SERVICE
        self.srch.yahooid = "Yahoo"
        self.srch.tag = "yahoo"
        self.assertEqual(self.srch.yahooid, "Yahoo")
        self.assertEqual(self.srch.tag, "yahoo")

    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.tag = "yahoo"
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)

    def testValidParams(self):
        """Verify that allowed parameters are correct (%s)""" % self.SERVICE
        params = self.srch.get_valid_params()
        self.assertEqual(len(params), self.NUM_PARAMS)
        for param in self.srch._valid_params.values():
            self.assertEqual(len(param), 6)


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
