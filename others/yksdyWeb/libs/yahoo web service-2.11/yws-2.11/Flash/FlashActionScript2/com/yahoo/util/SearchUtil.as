import com.yahoo.event.ObservableObject;
import com.yahoo.xml.SimpleXML;
import com.yahoo.search.SearchResultXML;

class com.yahoo.util.SearchUtil extends ObservableObject
{
	public static var APPLICATION_ID:String = 'YahooFlashSDKSearchUtil';
	public static var YAHOO_BETA_SEARCH_SERVICE_URL:String = '';
	public static var YAHOO_SEARCH_SERVICE_URL:String = 'http://search.yahooapis.com/';
	public static var YAHOO_PODCAST_SEARCH:String 	= 'podcast';
	public static var YAHOO_WEB_SEARCH:String 	= 'web';
	public static var YAHOO_IMAGE_SEARCH:String = 'image';
	public static var YAHOO_NEWS_SEARCH:String 	= 'news';
	public static var YAHOO_VIDEO_SEARCH:String = 'video';
	public static var YAHOO_LOCAL_SEARCH:String = 'local';
	public static var YAHOO_TERM_EXTRACTION:String = 'termextraction';
	
	public static function search(target:Object, query:String, searchType:String, resultsLength:Number):SearchResultXML
	{
		if(resultsLength == undefined)
		{
			resultsLength = 20;
		}
		var additionalArguments:String = '';
		if(arguments[4] != undefined)
		{
			additionalArguments = arguments[4];
		}
		var $searchXML = new SearchResultXML;
		
		if(target != undefined)
		{
			$searchXML.addEventObserver(target, 'onYahooSearchResult')
		}
		$searchXML.init($searchXML.handleSearch, query, searchType, target, '&results=' + resultsLength + additionalArguments);
		
		return $searchXML;
	}
	/**
	 * This method simply returns a properly formated query url for performing a Yahoo! search
	 * 
	 * @usage   this.queryURL:String = SearchUtil.getServiceURL(needle, searchType, zip);
	 * @param   needle    (String) text to search for 
	 * @param   searchType (String) tyoe of search, can be any of the following: 'image', 'audio', 'news', 'video', 'web', 'local'
	 * @return  String
	 */
	public static function getServiceURL(needle:String, searchType:String):String
	{
		var query:String;
		var zip:String;
		var serviceURL:String;
		// chack for zip
		if(arguments[2] != undefined)
		{
			zip = '&zip=' + arguments[2];
		}
		// ensure presence of required parameters
		if(needle == undefined || searchType == undefined)
		{
			return undefined;
		}
		else
		{
			query = '?appid=' + APPLICATION_ID + '&';
		}
		
		var queryKey:String;
		switch(searchType)
		{
			case SearchUtil.YAHOO_TERM_EXTRACTION:
			{
				queryKey = 'context';
			}
			break;
			default:
			{
				queryKey = 'query';
			}
			break;
		}
		query += queryKey.concat('=');
		// access Y! search string
		query += needle;
		// add zip if need be
		if(zip != undefined)
		{
			query += zip;
		}
		var services:Array = new Array;
		services[SearchUtil.YAHOO_IMAGE_SEARCH] = SearchUtil.YAHOO_SEARCH_SERVICE_URL + 'ImageSearchService/V1/imageSearch';
		services[SearchUtil.YAHOO_NEWS_SEARCH]  = SearchUtil.YAHOO_SEARCH_SERVICE_URL + 'NewsSearchService/V1/newsSearch';
		services[SearchUtil.YAHOO_VIDEO_SEARCH] = SearchUtil.YAHOO_SEARCH_SERVICE_URL + 'VideoSearchService/V1/videoSearch';
		services[SearchUtil.YAHOO_WEB_SEARCH]   = SearchUtil.YAHOO_SEARCH_SERVICE_URL + 'WebSearchService/V1/webSearch';
		services[SearchUtil.YAHOO_PODCAST_SEARCH] = SearchUtil.YAHOO_SEARCH_SERVICE_URL + 'AudioSearchService/V1/podcastSearch';
		services[SearchUtil.YAHOO_TERM_EXTRACTION] = SearchUtil.YAHOO_SEARCH_SERVICE_URL + 'ContentAnalysisService/V1/termExtraction';
		// local search is on a different host
		services[SearchUtil.YAHOO_LOCAL_SEARCH] = 'http://local.yahooapis.com/LocalSearchService/V1/localSearch';
		// build service url
		serviceURL = services[searchType].concat(query);
		
		//trace('serviceURL: ' + serviceURL);
		return serviceURL;
	}
}