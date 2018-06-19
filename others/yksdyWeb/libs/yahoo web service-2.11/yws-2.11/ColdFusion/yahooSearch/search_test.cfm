<cfset searchAPI = createObject("component", "org.camden.yahoo.search")>

<cfinvoke component="#searchAPI#" method="search" returnVariable="result">
	<cfinvokeargument name="query" value="coldfusion blog">
</cfinvoke>

<cfdump var="#result#" label="Search for 'coldfusion blog'">

<p>
<hr>
</p>

<cfinvoke component="#searchAPI#" method="search" returnVariable="result">
	<cfinvokeargument name="query" value="coldfusion blog">
	<cfinvokeargument name="type" value="phrase">
</cfinvoke>

<cfdump var="#result#" label="Search for 'coldfusion blog', type=phrase">

<p>
<hr>
</p>

<cfinvoke component="#searchAPI#" method="search" returnVariable="result">
	<cfinvokeargument name="query" value="coldfusion blog">
	<cfinvokeargument name="type" value="phrase">
	<cfinvokeargument name="format" value="rss">
</cfinvoke>

<cfdump var="#result#" label="Search for 'coldfusion blog', type=phrase, format=rss">

<p>
<hr>
</p>

<cfinvoke component="#searchAPI#" method="search" returnVariable="result">
	<cfinvokeargument name="query" value="coldfusion blog">
	<cfinvokeargument name="start" value="900">
</cfinvoke>

<cfdump var="#result#" label="Search for 'coldfusion blog', start on row 900">

<p>
<hr>
</p>

<cfinvoke component="#searchAPI#" method="search" returnVariable="result">
	<cfinvokeargument name="query" value="coldfusion">
	<cfinvokeargument name="site" value="ray.camdenfamily.com,www.camdenfamily.com">
</cfinvoke>

<cfdump var="#result#" label="Search for 'coldfusion blog', site restriction">
