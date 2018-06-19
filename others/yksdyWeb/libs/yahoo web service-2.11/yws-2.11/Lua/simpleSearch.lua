-- Author: Premshree Pillai
-- http://premshree.livejournal.com/

-- Lua: http://www.lua.org/
-- Requires LuaSocket: http://www.cs.princeton.edu/~diego/professional/luasocket/

http = require "socket.http"

local url = "http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=madonna&results=2"

-- make the request
results = http.request(url)
if not results then
	error("Web services request failed")
end

entities = {
	["<"] = "&lt;",
	[">"] = "&gt;",
	["&"] = "&amp;",
	["'"] = "&#039;",
	['"'] = "&quot;"
}
for k, v in pairs(entities) do
	results = string.gsub(results, k, v)
end
print(results)
