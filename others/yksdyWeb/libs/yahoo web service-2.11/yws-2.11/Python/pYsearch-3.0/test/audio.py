"""Unit tests for all yahoo.search.image classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.audio import *


__revision__ = "$Id: audio.py,v 1.4 2007/02/28 05:20:07 zwoop Exp $"
__version__ = "$Revision: 1.4 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 12 14:35:18 MDT 2006"


#
# Test for artist search
#
class ArtistSearchTestCase(SearchServiceTest, unittest.TestCase):
    """ArtistSearchTestCase - ArtistSearch Unit tests.
    """
    SERVICE = ArtistSearch
    NUM_PARAMS = 7

    def testQueryParam(self):
        """Verify that the query parameter is accepted (%s)""" % self.SERVICE
        self.srch.artist = "Madonna"
        self.assertEqual(self.srch.artist, "Madonna")

    # Now test a simple query, this will be overriden where appropriate
    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.artist = "Madonna"
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)


#
# Test for album search
#
class AlbumSearchTestCase(ArtistSearchTestCase):
    """AlbumSearchTestCase - AlbumSearch Unit tests.
    """
    SERVICE = AlbumSearch
    NUM_PARAMS = 9


#
# Test for song search
#
class SongSearchTestCase(ArtistSearchTestCase):
    """SongSearchTestCase - SongSearch Unit tests.
    """
    SERVICE = SongSearch
    NUM_PARAMS = 11

#
# Test for song download location
#
class SongDownloadLocationTestCase(SearchServiceTest, unittest.TestCase):
    """SongDownloadLocationTestCase - SongSearch Unit tests.
    """
    SERVICE = SongDownloadLocation
    NUM_PARAMS = 6

    def testQueryParam(self):
        """Verify that the query parameter is accepted (%s)""" % self.SERVICE
        self.srch.songid = "XXXXXXT002734753"
        self.assertEqual(self.srch.songid, "XXXXXXT002734753")

    # Now test a simple query, this will be overriden where appropriate
    def testSimpleQuery(self):
        """Test one simple query and the XML result (%s)""" % self.SERVICE
        import xml.dom.minidom
        self.srch.songid = "XXXXXXT002734753"
        dom = self.srch.get_results()
        self.assertTrue(isinstance(dom, xml.dom.minidom.Document))
        res = self.srch.parse_results(dom)
        self.assertFalse(res.total_results_available == 0)
        self.assertTrue(res.first_result_position > 0)

#
# Test for podcast search
#
class PodcastSearchTestCase(SearchServiceTest, unittest.TestCase):
    """PodcastSearchTestCase - PodcastSearch Unit tests.
    """
    SERVICE = PodcastSearch
    NUM_PARAMS = 8
    TEST_QUERY = "science"


    def testSimpleQuery(self):
        """This service doesn't seem to work ..."""
        pass



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
