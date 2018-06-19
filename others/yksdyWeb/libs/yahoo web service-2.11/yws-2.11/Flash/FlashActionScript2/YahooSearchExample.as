// We need to import the following classes in order to make searching and responding to key presses super simple
import com.yahoo.util.SearchUtil;
import com.yahoo.util.KeyboardUtil;
import com.yahoo.search.YahooSearchResultEvent;
import com.yahoo.search.YahooImageSearchResult;
import com.yahoo.search.YahooSearchResult;
import com.yahoo.table.Table;
import com.yahoo.table.TableCell;
import mx.controls.MediaPlayback;
import mx.controls.ComboBox;
import mx.controls.UIScrollBar;

class YahooSearchExample extends MovieClip
{
	// This is the default string that will appear in the search input TextField
	public static var DEFAULT_DISPLAY_STRING:String = 'Please enter a search term';
	// This indicates the maximum number of results to return.
	public static var MAX_SEARCH_RESULTS:Number = 50; 
	
	private var $responses:Number;
	private var $thumbnails:Table;
	// the following assets are assumed to be present in the .fla file
	private var searchView:MovieClip;
	private var yahooWebSearchResults:TextField;
	private var yahooNewsSearchResults:TextField;
	private var yahooWebSearchSummary:TextField;
	private var yahooNewsSearchSummary:TextField;
	private var audioPlayback:MediaPlayback;
	private var videoPlayback:MediaPlayback;
	private var audioList:ComboBox;
	private var videoList:ComboBox;
	private var webScrollBar:UIScrollBar;
	private var newsScrollBar:UIScrollBar;
	
	/**
	 * Sole Constructor
	 */
	public function YahooSearchExample()
	{
	}
	public function onLoad():Void
	{
		this.init();
	}
	public function onCellSelect(e:com.yahoo.event.Event):Void
	{
		getURL(e.getArgument('cell').data, '_blank');
	}
	private function init():Void
	{
		this.hideAudio();
		this.hideVideo();
		this.hideNews();
		this.hideWeb();
		this.searchView.searchIndicator._visible = false;

		this.searchView._x = this.getStageCenterX(this.searchView);
		this.searchView._y = this.getStageCenterY(this.searchView);
		
		this.centerInsideClip(this._parent, this);
		
		videoList.addEventListener("change", this);
		audioList.addEventListener("change", this);
		// Here we register to listen to all keypress events
		KeyboardUtil.addKeyListener(this);
		
		// Here we simply apply a cascading stylesheet to our search results TextField.
		var YahooSearchCSS = new TextField.StyleSheet();
		YahooSearchCSS.load("YahooSearchExample.css");
		yahooWebSearchResults.styleSheet = YahooSearchCSS;
		yahooNewsSearchResults.styleSheet = YahooSearchCSS;
		
		//
		Table.CELL_SIZE = 28;
		this.$thumbnails = Table(this.attachMovie(Table.SymbolName, '$thumbnails', this.getNextHighestDepth()));
		this.$thumbnails.init(6, 12, false);
		this.$thumbnails._y = 345;
		this.$thumbnails._x = 395;
		this.$thumbnails._visible = false;
		this.$thumbnails.addEventObserver(this, 'onCellSelect');
		// Here we create a simple onRelease handler for our search button.
		this.searchView.searchButton.onRelease = function():Void
		{
			this._parent._parent.yahooSearch();
		}
		
		// here we set the default string in our search input TextField.
		this.searchView.searchInput.text = DEFAULT_DISPLAY_STRING;
		
		// The following lines cause the default string in our search input TextField
		// to disappear when a user focuses on the input field, and reappear if the user
		// has left the field blank.
		this.searchView.searchInput.onSetFocus = function():Void
		{
			if(this.text == DEFAULT_DISPLAY_STRING)
			{
				this.text = new String;
			}
		}
		this.searchView.searchInput.onKillFocus = function():Void
		{
			if(this.text.length == 0)
			{
				this.text = DEFAULT_DISPLAY_STRING;
			}
		}
	}
	private function intro():Void
	{
		this.searchView.searchIndicator._visible = false;
		this.searchView._x = 0;
		this.searchView._y = 0;
		this.showWeb();
		this.showNews();
		this.showAudio();
		this.showVideo();
		this.$thumbnails._visible = true;
	}
	public function change(event_obj:Object) 
	{
		trace('videoList: ' + videoList)
		trace('event_obj.target: ' + event_obj.target)
		trace('event_obj.target.selectedItem.data: ' + event_obj.target.selectedItem.data)
		switch(event_obj.target)
		{
			case videoList:
			{
				videoPlayback.setMedia(event_obj.target.selectedItem.data, 'FLV');
			}
			break;
			case audioList:
			{
				audioPlayback.setMedia(event_obj.target.selectedItem.data, 'MP3');
			}
			break;
		}
	}
	/**
	 * This method is invoked when a search response has been recieved.
	 * All of the details regarding the search may be found in the Event object
	 * passed in as the initial parameter.
	 * 
	 * @usage   
	 * @param   e:com.yahoo.event.Event
	 * @return  Void
	 */
	function onYahooSearchResult(e:com.yahoo.search.YahooSearchResultEvent):Void
	{	
		this.$responses++;
		
		if(this.$responses == 5)
		{
			this.intro();
		}
		
		trace('onYahooSearchResult invoked');
		trace('totalResultsAvailable: ' + e.length)
		switch(e.type)
		{
			case SearchUtil.YAHOO_WEB_SEARCH:
			{
				this.yahooWebSearchSummary.htmlText = this.htmlLink('http://developer.yahoo.com/search/web/', 'Yahoo! Web Search', '_blank');		
				this.yahooWebSearchResults.scroll = 0;
				if(e.length > 0)
				{
					this.yahooWebSearchSummary.htmlText += ' found ' + e.totalResultsAvailable + ' results';
					this.yahooWebSearchResults.htmlText = '<b>Web Results:</b><br/>';
					for(var i:Number = 0; i < e.length; i++)
					{
						this.yahooWebSearchResults.htmlText += this.htmlLink(e.getResult(i).ClickUrl, (i+1) + '. ' + e.getResult(i).Title, '_blank');
						this.yahooWebSearchResults.htmlText = this.yahooWebSearchResults.htmlText.concat('<br/>');
					}
					this.yahooWebSearchSummary.htmlText += ', displaying results 1 - ' + e.length;
				}
				else
				{
					this.yahooWebSearchResults.htmlText = 'No web results found.';
				}
			}
			break;
			case SearchUtil.YAHOO_NEWS_SEARCH:
			{
				this.yahooNewsSearchSummary.htmlText = this.htmlLink('http://developer.yahoo.com/search/news/', 'Yahoo! News Search', '_blank');		
				this.yahooNewsSearchResults.scroll = 0;
				if(e.length > 0)
				{
					this.yahooNewsSearchSummary.htmlText += ' found ' + e.totalResultsAvailable + ' results';
					this.yahooNewsSearchResults.htmlText = '<b>News Results:</b><br/>';
					for(var i:Number = 0; i < e.length; i++)
					{
						this.yahooNewsSearchResults.htmlText += this.htmlLink(e.getResult(i).ClickUrl, (i+1) + '. ' + e.getResult(i).Title, '_blank');
						this.yahooNewsSearchResults.htmlText = this.yahooNewsSearchResults.htmlText.concat('<br/>');
					}
					this.yahooNewsSearchSummary.htmlText += ', displaying results 1 - ' + e.length;
				}
				else
				{
					this.yahooNewsSearchResults.htmlText = 'No news results found.';
				}
			}
			break;
			case SearchUtil.YAHOO_IMAGE_SEARCH:
			{
				this.$thumbnails.unloadImages();
				if(e.length > 0)
				{
					for(var i:Number = 0; i < e.length; i++)
					{
						this.$thumbnails.loadImage(i, YahooImageSearchResult(e.getResult(i)).Thumbnail.Url);
						this.$thumbnails.getCell(i).data = e.getResult(i).Url;
					}
				}
				else
				{
					this.$thumbnails._visible = false;
				}
			}
			break;
			case SearchUtil.YAHOO_PODCAST_SEARCH:
			{
				if(e.length > 0)
				{
					var mp3s:Number = 0;
					var started:Boolean = false;
					for(var i:Number = 0; i < e.length; i++)
					{
						if(started != true)
						{
							audioPlayback.setMedia(e.getResult(0).Url, 'MP3');
							audioPlayback.play();
							started = true;
						}
						
						audioList.addItem(e.getResult(i).Title, e.getResult(i).Url);
						
						mp3s++;
						
						if(mp3s == 10)
						{
							break;
						}
					}
				}
				else
				{
					audioList.addItem('No podcasts found');
				}
			}
			break;
			case SearchUtil.YAHOO_VIDEO_SEARCH:
			{
				if(e.length > 0)
				{
					var flvs:Number = 0;
					var started:Boolean = false;
					for(var i:Number = 0; i < e.length; i++)
					{
						var ext:String = e.getResult(i).Url.substr(e.getResult(i).Url.length - 4)
						trace('ext: ' + ext);
						if(ext == '.flv')
						{
							var flvUrl = e.getResult(i).Url.substr(e.getResult(i).Url.lastIndexOf('http'));
							trace('e.getResult(i).Url: ' + flvUrl);
							
							if(started != true)
							{
								videoPlayback.setMedia(flvUrl, 'FLV');
								videoPlayback.play();
								started = true;
							}
							
							videoList.addItem(e.getResult(i).Title, flvUrl);
							
							flvs++;
							
							if(flvs == 10)
							{
								break;
							}
						}
					}
					
					if(flvs == 0)
					{
						videoList.addItem('No FLV video results found');
						this.videoPlayback._visible = false;
					}
				}
				else
				{
					videoList.addItem('No video results found');
					this.videoPlayback._visible = false;
				}
			}
			break;
		}
	}
	private function hideVideo():Void
	{
		this.videoPlayback._visible = false;
		this.videoList._visible = false;
	}
	private function hideAudio():Void
	{
		this.audioPlayback._visible = false;
		this.audioList._visible = false;
	}
	private function hideNews():Void
	{
		this.newsScrollBar._visible = false;
		this.yahooNewsSearchResults._visible = false;
		this.yahooNewsSearchSummary._visible = false;
	}
	private function hideWeb():Void
	{
		this.webScrollBar._visible = false;
		this.yahooWebSearchResults._visible = false;
		this.yahooWebSearchSummary._visible = false;
	}
	private function showVideo():Void
	{
		this.videoPlayback._visible = true;
		this.videoList._visible = true;
	}
	private function showAudio():Void
	{
		this.audioPlayback._visible = true;
		this.audioList._visible = true;
	}
	private function showNews():Void
	{
		this.newsScrollBar._visible = true;
		this.yahooNewsSearchResults._visible = true;
		this.yahooNewsSearchSummary._visible = true;
	}
	private function showWeb():Void
	{
		this.webScrollBar._visible = true;
		this.yahooWebSearchResults._visible = true;
		this.yahooWebSearchSummary._visible = true;
	}
	/**
	 * This method is invoked when the user has pressed a key down.
	 * All details about the key press are found in the com.yahoo.event.Event object
	 * passed in as the initial parameter.
	 * 
	 * @param   e:com.yahoo.event.Event
	 * @return  Void
	 */
	function onKeyDown(e:com.yahoo.event.Event):Void
	{
		if(e.getArgument('value') == 'Enter')
		{
			this.yahooSearch();
		}
	}
	/**
	 * This method performs a search by invoking the static search method on the SearchUtil class,
	 * which in turn makes an asyncrhonous call to the Yahoo Search Web service indicated by the second parameter.
	 * It passes a reference to 'this', which causes the onYahooSearchResult method on this class
	 * to be invoked upon receiving a response.
	 * 
	 * @return Void
	 */
	function yahooSearch():Void
	{
		this.searchView.searchIndicator._visible = true;
		this.$responses = 0;
		this.audioPlayback.stop();
		this.videoPlayback.stop();
		this.audioList.removeAll();
		this.videoList.removeAll();
		this.yahooWebSearchResults.htmlText = new String;
		this.yahooNewsSearchResults.htmlText = new String;
		this.yahooWebSearchSummary.htmlText = new String;
		this.yahooNewsSearchSummary.htmlText = new String;
		SearchUtil.search(this, this.searchView.searchInput.text, SearchUtil.YAHOO_WEB_SEARCH, MAX_SEARCH_RESULTS);
		SearchUtil.search(this, this.searchView.searchInput.text, SearchUtil.YAHOO_NEWS_SEARCH, MAX_SEARCH_RESULTS/2);
		SearchUtil.search(this, this.searchView.searchInput.text, SearchUtil.YAHOO_IMAGE_SEARCH, 12);
		SearchUtil.search(this, this.searchView.searchInput.text, SearchUtil.YAHOO_PODCAST_SEARCH, 5, '&format=mp3');
		SearchUtil.search(this, this.searchView.searchInput.text, SearchUtil.YAHOO_VIDEO_SEARCH, 50, '&format=flash');
	}
	/**
	 * This utility method simply takes a few parameters and returns a string containing
	 * a valid HTML anchor tag reflecting the parameters passed in.
	 * 
	 * @param   url:String  
	 * @param   display:String
	 * @param   targetWindow:String
	 * @return  String
	 */
	function htmlLink(url:String, display:String, targetWindow:String):String
	{
		var link:String = new String;
		link = link.concat('<a href="');
		link = link.concat(url);
		link = link.concat('" ');
		link = link.concat('target="');
		link = link.concat(targetWindow);
		link = link.concat('">');
		link = link.concat(display);
		link = link.concat('</a>');
	
		return link;
	}
	public function centerInsideClip(parentClip:MovieClip, clipToBeCentered:MovieClip, vertical:Boolean, horiz:Boolean):Void
	{
		if(horiz != false)
		{
			clipToBeCentered._x = (parentClip._width - clipToBeCentered._width) / 2;
		}
		if(vertical != false)
		{
			clipToBeCentered._y = (parentClip._height- clipToBeCentered._height) / 2;
		}
	}
	public function getStageCenterX(target:MovieClip):Number
	{
		return (Stage.width - target._width) / 2;
	}
	public function getStageCenterY(target:MovieClip):Number
	{
		return (Stage.height - target._height) / 2;
	}
}