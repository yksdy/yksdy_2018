#!/usr/bin/env ruby

# Author: Premshree Pillai
# http://premshree.livejournal.com

require "net/http"

request = "http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=madonna&results=2"

# make the request
begin
	results = Net::HTTP.get_response(URI.parse(request)).body
rescue
	raise "Web services request failed"
end
{"<"=>"&lt;", ">"=>"&gt;","&"=>"&amp;",'"'=>"&quot;", "'"=>"&#039;"}.each { |k,v|
	results.gsub!(k,v)
}

print results; 

