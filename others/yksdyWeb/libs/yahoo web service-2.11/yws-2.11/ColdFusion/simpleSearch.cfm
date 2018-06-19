<!---
	Name         : simpleSearch.cfm
	Author       : Raymond Camden 
	Date         : March 1st, 2007
--->
<cftry>
    <cfhttp url="http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=madonna&results=2" charset="utf-8">
    <cfdump var="#cfhttp.filecontent#">
    <cfcatch>
    Sorry, something failed!
    </cfcatch>
</cftry> 
