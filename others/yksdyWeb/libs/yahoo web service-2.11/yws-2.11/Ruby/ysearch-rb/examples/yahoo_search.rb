#!/usr/bin/env ruby

# $Id: yahoo_search.rb,v 1.1 2006/06/14 05:40:58 premshree Exp $

# A simple Yahoo! search script using the
# Usage ruby yahoo_search.rb <query>

# include the yahoo-ruby API
require 'ysearch'

# get the query parameter
query = ARGV[0]? ARGV[0] : exit

##
# create a web search object:
# Arguments:
# 1. App ID (You can get one at http://developer.yahoo.net)
# 2. The query
# 3. type can be one of: 'all', 'any' or 'phrase'
# 4. The no. of results
##
obj = WebSearch.new('YahooDemo', query, 'all', 3)

# store the results -- returns an array of hashes
results = obj.parse_results

# now loop over each item in results, and display the title, summary and URL
results.each { |result|
	print "Title:\t#{result['Title']}\n"
	print "Summary:\t#{result['Summary']}\n"
	print "URL:\t#{result['Url']}\n"
	print "="*36+"\n\n"
} 
