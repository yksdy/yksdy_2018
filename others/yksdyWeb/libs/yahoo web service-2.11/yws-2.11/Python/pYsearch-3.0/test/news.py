"""Unit tests for all yahoo.search.news classes

"""

import unittest
from searchbase import SearchServiceTest

from yahoo.search.news import *


__revision__ = "$Id: news.py,v 1.3 2007/02/28 05:20:08 zwoop Exp $"
__version__ = "$Revision: 1.3 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 12 13:34:07 MDT 2006"


#
# Test for news search
#
class NewsSearchTestCase(SearchServiceTest, unittest.TestCase):
    """NewsSearchTestCase - NewsSearch Unit tests.
    """
    SERVICE = NewsSearch
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
