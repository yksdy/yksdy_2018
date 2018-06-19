#!/usr/bin/env python

__revision__ = "$Id: setup.py,v 1.8 2007/02/28 05:22:23 zwoop Exp $"
__version__ = "$Revision: 1.8 $"
__author__ = "Leif Hedstrom <leifh@yahoo-inc.com>"
__date__ = "Tue Feb 27 22:21:49 MST 2007"

from distutils.core import setup
from yahoo.search import version

setup(name="pYsearch",
      packages=['yahoo', 'yahoo.search', 'yahoo.search.dom'],
      version=version.version,
      description="Yahoo Search Web Services SDK for Python",
      author=version.authorName,
      author_email=version.authorMail,
      maintainer=version.maintainerName,
      maintainer_email=version.maintainerMail,
      url="http://pysearch.sourceforge.net/",
      download_url="http://prdownloads.sourceforge.net/pysearch/pYsearch-3.0.tar.gz?download",
      license="http://www.opensource.org/licenses/bsd-license.php",
      platforms=["any"],
      keywords=["yahoo", "search", "API", "web services"],
      long_description="""This module implements a set of classes and functions to work with the
Yahoo Search Web Services. All results from these services are properly
formatted XML, and this package facilitates for correct parsing of these
result sets.""",
      classifiers=["Development Status :: 5 - Production/Stable",
                   "Environment :: Web Environment",
                   "Intended Audience :: Developers",
                   "Operating System :: OS Independent",
                   "License :: OSI Approved :: BSD License",
                   "Topic :: Internet :: WWW/HTTP :: Indexing/Search",
                   "Topic :: Software Development :: Libraries",
                   ],
      )
