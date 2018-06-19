#!/usr/bin/env python 

# Author: Premshree Pillai
# http://premshree.livejournal.com

import urllib2, sys

request = "http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=madonna&results=2"

# make the request
try:
	results = urllib2.urlopen(request).read()
except:
	print "Web services request failed"
	sys.exit()

entities = { "<":"&lt;", ">":"&gt;", "&":"&amp;", "'":"&#039;", '"':"&quot;" }
for k,v in entities.items():
	results = results.replace(k, v)

print results 

