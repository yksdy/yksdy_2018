#!/usr/bin/python

from yahoo.search import webservices
from yahoo.search.debug import DEBUG_LEVELS

import hotshot


def tester():
    x = webservices.create_search("web", "YahooDemo", query="leif hedstrom", results=20)
    srch = x.parse_results()

if __name__ == "__main__":
    prof = hotshot.Profile("ysearch.prof", lineevents=1)
    prof.runcall(tester)
    prof.close()

    print "Convert this to a kcachegrind callstack with:"
    print "hotshot2calltree -o ysearch.out ysearch.prof"
