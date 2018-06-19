--
-- $YahooSearch.lua$ $2005-06-04 02:13$
--
-- Lua API for Yahoo! Search Web Services
-- See http://developer.yahoo.net/
-- (C) 2005 Premshree Pillai
-- http://www.livejournal.com/~premshree
--
-- EXAMPLE:
--
--  require "YahooSearch"
--  obj = YahooSearch.WebSearch('premshree')
--  obj[0]['Title'] -- title of first result
--  obj[2]['Url']   -- url of third result
--
-- See code to understand return keys
-- See the Yahoo! developer website for
--  understanding the input parameters
--
-- NOTE:
--  
--  supports Yahoo! web, image, news, and
--   video searches. Support for others can
--   be easily added
--
--  xml-c.lua is bundled with this package,
--   the Classic Lua-only implementation for
--   parsing XML data. It's also available at
--   http://lua-users.org/wiki/LuaXml
--
--  The version available at the wiki is for
--   Lua 4.x only. The one bundled here is
--   with minor modifications, for Lua 5.x
-- 

require "xml-c"
http = require "socket.http"

YahooSearch = {}
local appid = "Yahoo! Lua"
local yws_url = "http://search.yahooapis.com"

function YahooSearch.escape (str)
	str = string.gsub(str, "([&=+%c])", function (c)
            return string.format("%%%02X", string.byte(c))
          end)
	str = string.gsub(str, " ", "+")
	return str
end

function YahooSearch.encode (tab)
	local str = ""
	for key, val in pairs(tab) do
		str = str .. "&" .. YahooSearch.escape(key) .. "=" .. YahooSearch.escape(val)
	end
	return string.sub(str, 2)
end

function YahooSearch.GetResults (url, num_results, num_ele)
	local http_content = http.request(url)
	local result_set = collect(http_content)
	local results = {}
	for i=1,num_results do
		results[i-1] = {}
		for j=1,num_ele do
			results[i-1][result_set[2][i][j]['label']] = result_set[2][i][j][1]
		end
	end
	return results
end

----
-- Retruns an array of hashes. Each has the following keys:
--  o Title
--  o Summary
--  o Url
--  o ClickUrl
--  o ModificationDate
--  o MimeType
----
function YahooSearch.WebSearch (query, type, results, start, format, adult_ok, similar_ok, language, country, license)
	local type = type or 'all'
	local results = results or 10
	local start = start or 1
	local format = format or 'any'
	local adult_ok = adult_ok or ''
	local similar_ok = similar_ok or ''
	local language = language or 'en'
	local country = country or ''
	local license = license or 'any'

	local params = {
		appid		= appid,
		query		= query,
		type		= type,
		results		= results,
		start		= start,
		format		= format,
		adult_ok	= adult_ok,
		similar_ok	= similar_ok,
		language	= language,
		country		= country,
		license		= license
	}
	local url = yws_url .. "/WebSearchService/V1/webSearch?" .. YahooSearch.encode(params)
	return YahooSearch.GetResults(url, results, 6)
end

----
-- Retruns an array of hashes. Each has the following keys:
--  o Title
--  o Summary
--  o Url
--  o ClickUrl
--  o RefererUrl
--  o FileSize
--  o FileFormat
--  o Height
--  o Width
----
YahooSearch.ImageSearch = function (query, type, results, start, format, adult_ok, coloration)
	local type = type or 'all'
	local results = results or 10
	local start = start or 1
	local format = format or 'any'
	local adult_ok = adult_ok or ''
	local coloration = coloration or 'any'

	local params = {
		appid		= appid,
		query		= query,
		type		= type,
		results		= results,
		start		= start,
		format		= format,
		adult_ok	= adult_ok,
		coloration	= coloration
	}
	local url = yws_url .. "/ImageSearchService/V1/imageSearch?" .. YahooSearch.encode(params)
	return YahooSearch.GetResults(url, results, 9)
end

----
-- Retruns an array of hashes. Each has the following keys:
--  o Title
--  o Summary
--  o Url
--  o ClickUrl
--  o NewsSource
--  o NewsSourceUrl
--  o Language
--  o PublishDate
--  o ModificationDate
----
YahooSearch.NewsSearch = function (query, type, results, start, sort, language)
	local type = type or 'all'
	local results = results or 10
	local start = start or 1
	local sort = sort or 'rank'
	local language = language or ''

	local params = {
		appid		= appid,
		query		= query,
		type		= type,
		results		= results,
		start		= start,
		sort		= sort,
		language	= language
	}
	local url = yws_url .. "/NewsSearchService/V1/newsSearch?" .. YahooSearch.encode(params)
	return YahooSearch.GetResults(url, results, 9)
end

----
-- Retruns an array of hashes. Each has the following keys:
--  o Title
--  o Summary
--  o Url
--  o ClickUrl
--  o RefererUrl
--  o FileSize
--  o FileFormat
--  o Height
--  o Width
--  o Duration
--  o Streaming
--  o Channels
----
YahooSearch.VideoSearch = function (query, type, results, start, format, adult_ok)
	local type = type or 'all'
	local results = results or 10
	local start = start or 1
	local format = format or 'any'
	local adult_ok = adult_ok or ''
	local coloration = coloration or 'any'

	local params = {
		appid		= appid,
		query		= query,
		type		= type,
		results		= results,
		start		= start,
		format		= format,
		adult_ok	= adult_ok,
	}
	local url = yws_url .. "/VideoSearchService/V1/videoSearch?" .. YahooSearch.encode(params)
	return YahooSearch.GetResults(url, results, 12)
end