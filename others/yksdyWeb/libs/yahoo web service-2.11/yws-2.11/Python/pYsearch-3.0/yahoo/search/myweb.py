"""yahoo.search.myweb - Yahoo Search MyWeb Web Services

This module implements a set of classes and functions to work with the
Yahoo Search MyWeb Web Services. The supported classes of MyWeb
and MyWeb2 searches are:

    ListFolders    - List (public) MyWeb folders
    ListUrls       - List the URLs in a MyWeb folder

    UrlSearch      - Search for URLs with particular tags
    TagSearch      - Search for tags associated with URL or user
    RelatedTags    - Find tags that appear together on URLs


The various sub-classes of MyWeb Search supports different sets of query
parameters. They all require an application ID parameter (app_id). The
following tables describes all other allowed parameters for each of the
supported services:

                List Folders  List URLs
                ------------  ---------
    folder           .           [X]
    yahooid         [X]          [X]
    sort             .           [X]
    sort_order       .           [X]
    results         [X]          [X]
    start           [X]          [X]



MyWeb2 also adds the following services:

                URL Search  Tag Search  Related Tags
                ----------  ----------  ------------
    tag             [X]         .           [X]
    url              .         [X]           .
    yahooid         [X]        [X]          [X]
    sort            [X]        [X]          [X]
    reverse_sort    [X]        [X]          [X]
    results         [X]        [X]          [X]
    start           [X]        [X]          [X]


Each of these parameter is implemented as an attribute of each
respective class. For example, you can set parameters like:

    from yahoo.search.myweb import ListFolders

    app_id = "YahooDemo"
    srch = ListFolders(app_id)
    srch.yahooid = "some_valid_yahoo_id"
    srch.results = 40
"""

import types

import yahoo.search
import yahoo.search.dom.myweb


__revision__ = "$Id: myweb.py,v 1.5 2007/02/28 05:20:09 zwoop Exp $"
__version__ = "$Revision: 1.5 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Tue Feb 27 17:06:45 MST 2007"


#
# Base class for all MyWeb classes
#
class _MyWeb(yahoo.search._Search):
    """Yahoo Search WebService - MyWeb parameters

    Setup the basic CGI parameters for all My Web services. Since these
    type of services do not take a query argument, we can't subclass
    the Basic or Common search classes.
    """
    def _init_valid_params(self):
        """Initialize the set of valid parameters."""
        self._valid_params.update({
            "results" : (types.IntType, 10, int, lambda x: x > -1 and x < 51,
                         "the range 1 to 50", False),
            "start" : (types.IntType, 1, int, lambda x: x > -1 and x < 1001,
                       "the range 1 to 1000", False),
            })


#
# ListFolders class
#
class ListFolders(_MyWeb):
    """ListFolders - Retrieving public folders

    This class implements the My Web service to retrieve public folders.
    Allowed parameters are:
    
        yahooid      - The Yahoo! user who owns the folder being accessed.
        results      - The number of results to return (1-50).
        start        - The starting result position to return (1-based).
                       The finishing position (start + results - 1) cannot
                       exceed 1000.
        output       - The format for the output result. If json or php is
                       requested, the result is not XML parseable, so we
                       will simply return the "raw" string.
        callback     - The name of the callback function to wrap around
                       the JSON data.


    Full documentation for this service is available at:

        http://developer.yahoo.net/myweb/V1/listFolders.html
    """
    NAME = "listFolders"
    SERVICE = "MyWebService"
    _RESULT_FACTORY = yahoo.search.dom.myweb.ListFolders


    def _init_valid_params(self):
        """Initialize the set of valid parameters."""
        super(ListFolders, self)._init_valid_params()
        self._valid_params.update({
            "yahooid" : (types.StringTypes, None, None, None, None, True),
            })


#
# ListUrls class
#
class ListUrls(_MyWeb):
    """ListUrls - Retrieving public URL stores

    This class implements the My Web service to retrieve URLs from a
    public folder. Allowed parameters are:
    
        folder       - The folder to retreive queries from. The folder
                       must be set to "Public" in order to be accessed.
        yahooid      - The Yahoo! user who owns the folder being accessed.
        sort         - The field by which the results should be sorted.
        sort_order   - Ascending or descending sort order, "asc" which is
                       the default, or "desc".
        results      - The number of results to return (1-50).
        start        - The starting result position to return (1-based).
                       The finishing position (start + results - 1) cannot
                       exceed 1000.
        output       - The format for the output result. If json or php is
                       requested, the result is not XML parseable, so we
                       will simply return the "raw" string.
        callback     - The name of the callback function to wrap around
                       the JSON data.

    The sort parameter can take one of the following values:

        storedate  - Date stored (default)
        title      - Title
        summary    - Summary
        note       - Note
        url        - URL
        

    Full documentation for this service is available at:

        http://developer.yahoo.net/myweb/V1/listUrls.html
    """
    NAME = "listUrls"
    SERVICE = "MyWebService"
    _RESULT_FACTORY = yahoo.search.dom.myweb.ListUrls

    def _init_valid_params(self):
        """Initialize the set of valid parameters."""
        super(ListUrls, self)._init_valid_params()
        self._valid_params.update({
            "yahooid" : (types.StringTypes, None, None, None, None, True),
            "folder" : (types.StringTypes, None, None, None, None, True),
            "sort" : (types.StringTypes, "storedate", str.lower,
                      ("storedate", "title", "summary", "note", "url"),
                      None, False),
            "sort_order" : (types.StringTypes, "asc", str.lower,
                            ("asc", "desc"), None, False),
            })


#
# UrlSearch class
#
class UrlSearch(_MyWeb):
    """UrlSearch - Search for URLs with particular tags

    This class implements the MyWeb2 service to search for URLs that have
    been tagged by particular tags. Allowed parameters are:
    
        tag          - The tag to search for. Multiple tags may be
                       specified (e.g. ["tag1", "tag2"]).
        yahooid      - The Yahoo! user who owns the folder being accessed.
        sort         - The field by which the results should be sorted.
                       date sorts most-recent-first, title and url are
                       sorted alphabetically.
        reverse_sort - If set to 1, reverses the sort order.
        results      - The number of results to return (1-50).
        start        - The starting result position to return (1-based).
                       The finishing position (start + results - 1) cannot
                       exceed 1000.
        output       - The format for the output result. If json or php is
                       requested, the result is not XML parseable, so we
                       will simply return the "raw" string.
        callback     - The name of the callback function to wrap around
                       the JSON data.


    Full documentation for this service is available at:

        http://developer.yahoo.net/myweb/V1/urlSearch.html
    """
    NAME = "urlSearch"
    SERVICE = "MyWebService"
    _RESULT_FACTORY = yahoo.search.dom.myweb.UrlSearch

    def _init_valid_params(self):
        """Initialize the set of valid parameters."""
        super(UrlSearch, self)._init_valid_params()
        self._valid_params.update({
            "yahooid" : (types.StringTypes, None, None, None, None, False),
            "tag" : (types.StringTypes, [], None, None, None, False),
            "sort" : (types.StringTypes, "date", str.lower,
                      ("date", "title","url"), None, False),
            "reverse_sort" : (types.IntType, 0, int, (0, 1), None, False),
            })


#
# TagSearch class
#
class TagSearch(_MyWeb):
    """TagSearch - Search for tags by URL and/or by user

    This class implements a MyWeb2 service which allows you to find tags that
    have been associated with URLs by MyWeb 2.0 users. Allowed parameters are:
    
        url          - If specified, only returns tags associated with this url.
        yahooid      - The Yahoo! user who owns the folder being accessed.
        sort         - The field by which the results should be sorted. Options
                       are popularity (default), tag or date.
        reverse_sort - If set to 1, reverses the sort order. This has no effect
                       on popularity searches.
        results      - The number of results to return (1-50).
        start        - The starting result position to return (1-based).
                       The finishing position (start + results - 1) cannot
                       exceed 1000.
        output       - The format for the output result. If json or php is
                       requested, the result is not XML parseable, so we
                       will simply return the "raw" string.
        callback     - The name of the callback function to wrap around
                       the JSON data.


    Full documentation for this service is available at:

        http://developer.yahoo.net/myweb/V1/tagSearch.html
    """
    NAME = "tagSearch"
    SERVICE = "MyWebService"
    _RESULT_FACTORY = yahoo.search.dom.myweb.TagSearch

    def _init_valid_params(self):
        """Initialize the set of valid parameters."""
        super(TagSearch, self)._init_valid_params()
        self._valid_params.update({
            "yahooid" : (types.StringTypes, None, None, None, None, False),
            "url" : (types.StringTypes, None, None, None, None, False),
            "sort" : (types.StringTypes, "popularity", str.lower,
                      ("popularity", "tag", "date"), None, False),
            "reverse_sort" : (types.IntType, 0, int, (0, 1), None, False),
            })


#
# RelatedTags class
#
class RelatedTags(_MyWeb):
    """RelatedTags - Find tags that appear together on URLs

    This class implements a MyWeb2 service which allows you to find tags that
    appear together on URLs. For example, if a URL is tagger with 'yahoo' and
    'music', a search for the tag 'yahoo' will return 'music' as a related
    tag.
    
        tag          - The tag to search for. Multiple tags may be
                       specified (e.g. ["tag1", "tag2"]).
        yahooid      - The Yahoo! user who owns the folder being accessed.
        sort         - The field by which the results should be sorted. Options
                       are popularity (default), tag or date.
        reverse_sort - If set to 1, reverses the sort order. This has no effect
                       on popularity searches.
        results      - The number of results to return (1-50).
        start        - The starting result position to return (1-based).
                       The finishing position (start + results - 1) cannot
                       exceed 1000.
        output       - The format for the output result. If json or php is
                       requested, the result is not XML parseable, so we
                       will simply return the "raw" string.
        callback     - The name of the callback function to wrap around
                       the JSON data.


    Full documentation for this service is available at:

        http://developer.yahoo.net/myweb/V1/relatedTags.html
    """
    NAME = "relatedTags"
    SERVICE = "MyWebService"
    _RESULT_FACTORY = yahoo.search.dom.myweb.TagSearch

    def _init_valid_params(self):
        """Initialize the set of valid parameters."""
        super(RelatedTags, self)._init_valid_params()
        self._valid_params.update({
            "yahooid" : (types.StringTypes, None, None, None, None, False),
            "tag" : (types.StringTypes, [], None, None, None, True),
            "sort" : (types.StringTypes, "popularity", str.lower,
                      ("popularity", "tag", "date"), None, False),
            "reverse_sort" : (types.IntType, 0, int, (0, 1), None, False),
            })



#
# local variables:
# mode: python
# indent-tabs-mode: nil
# py-indent-offset: 4
# end:
