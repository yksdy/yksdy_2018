"""Unit tests for all yahoo.search.image classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.image import *


__revision__ = "$Id: image.py,v 1.2 2007/02/28 05:20:08 zwoop Exp $"
__version__ = "$Revision: 1.2 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 12 13:34:35 MDT 2006"


#
# Test for image search
#
class ImageSearchTestCase(SearchServiceTest, unittest.TestCase):
    """ImageSearchTestCase - ImageSearch Unit tests.
    """
    SERVICE = ImageSearch
    NUM_PARAMS = 10


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
