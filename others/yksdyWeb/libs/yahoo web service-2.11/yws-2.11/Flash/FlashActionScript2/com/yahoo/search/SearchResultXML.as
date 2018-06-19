import com.yahoo.xml.SimpleXML;
import com.yahoo.util.SearchUtil;
import com.yahoo.search.YahooSearchResult;
import com.yahoo.search.YahooImageSearchResult;
import com.yahoo.search.YahooSearchResultEvent;
import com.yahoo.search.YahooSearchThumbnail;

/**
 * This class wraps the SimpleXML (XML) class with a simple implementation for handling the Yahoo search web service.  It loads the correct URL with the params based in upon construction.
 * 
 * @see     SimpleXML	
 */
class com.yahoo.search.SearchResultXML extends SimpleXML
{
	private var queryURL:String;
	private var $searchType:String;
	private var $queryId:Number;
	/**
	 * 
	 * @param   parseHandler (Function)
	 * @param   needle    (String) text to search for 
	 * @param   searchType (String) type of search, can be any of the following: 'image', 'audio', 'news', 'video', 'web', 'local'
	 */
	public function SearchResultXML()
	{
		this.setClassDescription('com.yahoo.search.SearchResultXML');
		if(arguments[0] != undefined && arguments[1] != undefined && arguments[2] != undefined)
		{
			var parseHandler:Function = arguments[0];
			var needle:String = arguments[1];
			var searchType:String = arguments[2];
			this.init(parseHandler, needle, searchType, arguments[3], arguments[4], arguments[5])
		}
	}
	public function init(parseHandler:Function, needle:String, searchType:String):Void
	{
		this.$searchType = searchType;
		this.context = arguments[3];
		var additionalArgs:String = '';
		if(arguments[4] != undefined)
		{
			//trace('add: '+arguments[4])
			additionalArgs = arguments[4];
		}
		var zip = arguments[5];

		this.onLoad = parseHandler;

		this.queryURL = SearchUtil.getServiceURL(needle, searchType, zip) + additionalArgs;
		//this.trace('queryURL = ' + this.queryURL);
		this.$queryId = new Date().getTime();
		this.load(this.queryURL);
	}
	public function handleSearch(success:Boolean):Void
	{
		//trace('handleSearch invoked: ' + success);
		//trace('this.$searchType: ' + this.$searchType)
		/*
		trace('images found: ' + SimpleXML(this).firstChild.childNodes.length);
		trace('this: '+this.$instanceDescription)
		*/
		var i:Number = 0;
		var node:XMLNode;
		var resultsArray:Array = new Array();
		// build data array from xml results
		for(node in SimpleXML(this).firstChild.childNodes)
		{
			var nodes = SimpleXML(this).firstChild.childNodes[node].childNodes;
			var searchResult:YahooSearchResult;
			//this.trace('this.$searchType: ' + this.$searchType);
			switch(this.$searchType)
			{			
				case SearchUtil.YAHOO_IMAGE_SEARCH:
				{
					searchResult = new YahooImageSearchResult;
				}
				break;
				default:
				{
					searchResult = new YahooSearchResult;
				}
				break;
			}
			
			switch(this.$searchType)
			{			
				case SearchUtil.YAHOO_TERM_EXTRACTION:
				{
					//trace(i +' == ' + SimpleXML(this).firstChild.childNodes[i].firstChild.nodeValue)
					resultsArray[i] = SimpleXML(this).firstChild.childNodes[i].firstChild.nodeValue;
				}
				break;
				default:
				{
					for(var prop in nodes)
					{
						//trace(nodes[prop].nodeName+' = '+nodes[prop].firstChild.nodeValue)
						if(nodes[prop].childNodes.length > 1)
						{
							//trace(nodes[prop].nodeName + ' has children')
							//searchResult[nodes[prop].nodeName] = new Array();
							if(nodes[prop].nodeName == 'Thumbnail')
							{
								//trace(nodes[prop])
								//trace('u: ' + nodes[prop].childNodes[0].firstChild.nodeValue);
								//trace('w: ' + nodes[prop].childNodes[2].firstChild.nodeValue);
								//trace('h: ' + nodes[prop].childNodes[1].firstChild.nodeValue);

								var url:String = nodes[prop].childNodes[0].firstChild.nodeValue;
								var w:Number= nodes[prop].childNodes[2].firstChild.nodeValue;
								var h:Number = nodes[prop].childNodes[1].firstChild.nodeValue;

								searchResult[nodes[prop].nodeName] = new YahooSearchThumbnail(url, w, h);
							}
							else
							{
								for(var props in nodes[prop].childNodes)
								{								
									searchResult[nodes[prop].nodeName][nodes[prop].childNodes[props].nodeName] = nodes[prop].childNodes[props].firstChild.nodeValue
								}
							}
						}
						else
						{
							//trace(nodes[prop].nodeName + ' has NO children')
							searchResult[nodes[prop].nodeName] = nodes[prop].firstChild.nodeValue;
						}
					}
					// trace('put in: '+ searchResult.Title);
					resultsArray[i] = searchResult;
				}
				break;
			}
			i++;
		}
		
		
				
		var resultDetails = new Object;
		switch(this.$searchType)
		{			
			case SearchUtil.YAHOO_TERM_EXTRACTION:
			{
				resultDetails.results = resultsArray;
			}
			break;
			default:
			{
				resultDetails.results = resultsArray.sort(Array.DESCENDING);
			}
			break;
		}
		resultDetails.xmlns_xsi 			= SimpleXML(this).firstChild.attributes['xmlns:xsi'];
		resultDetails.xmlns 				= SimpleXML(this).firstChild.attributes.xmlns;
		resultDetails.xsi_schemaLocation 	= SimpleXML(this).firstChild.attributes['xsi:schemaLocation'];
		resultDetails.totalResultsAvailable = SimpleXML(this).firstChild.attributes.totalResultsAvailable;
		resultDetails.totalResultsReturned 	= SimpleXML(this).firstChild.attributes.totalResultsReturned;
		resultDetails.firstResultPosition 	= SimpleXML(this).firstChild.attributes.firstResultPosition;
		resultDetails.query = SimpleXML(this).firstChild.firstChild.firstChild.firstChild.nodeValue;	
		
		var e:YahooSearchResultEvent = new YahooSearchResultEvent(resultsArray, resultDetails.firstResultPosition, resultDetails.totalResultsReturned, resultDetails.totalResultsAvailable, resultDetails.xsi_schemaLocation, resultDetails.xmlns, resultDetails.xmlns_xsi);
		e.addArgument('xml', SimpleXML(this));
		e.addArgument('type', this.$searchType);
		e.setSender(this);
		e.addArgument('response', resultDetails);
		this.dispatchEvent(e);
	}
	public function get queryId():Number
	{
		return this.$queryId;
	}
	public function get searchType():String
	{
		return this.$searchType;
	}
}