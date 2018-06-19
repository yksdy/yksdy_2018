"""Unit tests for all yahoo.search.video classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.video import *


__revision__ = "$Id: video.py,v 1.3 2007/02/28 05:20:09 zwoop Exp $"
__version__ = "$Revision: 1.3 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 12 13:34:19 MDT 2006"


#
# Test for video search
#
class VideoSearchTestCase(SearchServiceTest, unittest.TestCase):
    """VideoSearchTestCase - VideoSearch Unit tests.
    """
    SERVICE = VideoSearch
    NUM_PARAMS = 9


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
