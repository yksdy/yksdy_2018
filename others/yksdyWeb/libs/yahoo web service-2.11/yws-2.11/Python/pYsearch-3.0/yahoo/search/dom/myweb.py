"""DOM parser for MyWeb search results

Implement a simple DOM parser for the Yahoo Search Web Services
MyWeb and MyWeb2 search APIs.
"""


__revision__ = "$Id: myweb.py,v 1.4 2005/10/27 18:07:59 zwoop Exp $"
__version__ = "$Revision: 1.4 $"
__author__ = "Leif Hedstrom <leif@ogre.com>"
__date__ = "Thu Oct 27 10:47:35 PDT 2005"

from yahoo.search import dom


#
# MyWeb ListFolders DOM parser
#
class ListFolders(dom.DOMResultParser):
    """ListFolders - DOM parser for MyWeb Public Folders

    Each result is a dictionary populated with the extracted data from the
    XML results. The following keys are always available:

        Title            - The title of the folder.
        UrlCount         - The number of URLs stored in this folder.


    Example:
        results = ws.parse_results(dom)
        for res in results:
            print "%s [%d]" % (res.Title, res.UrlCount)

    """
    def _init_res_fields(self):
        """Initialize the valid result fields."""
        self._res_fields = [('Title', None, None),
                            ('UrlCount', None, int),
                            ]


#
# MyWeb ListUrls DOM parser
#
class ListUrls(dom.DOMResultParser):
    """ListUrls - DOM parser for MyWeb Stored URLs

    Each result is a dictionary populated with the extracted data from the
    XML results. The following keys are always available:

        Title            - The title of the folder.
        Summary          - Summary text associated with the web page.
        Url              - The URL for the web page.
        ClickUrl         - The URL for linking to the page.
        Note             - Any note the Yahoo! user has chosen to annotate
                           the URL with.
        StoreDate        - The date the URL was stored, unix timestamp format.


    Example:
        results = ws.parse_results(dom)
        for res in results:
            print "%s - %s" % (res.Title, res.Url)

    """
    def _init_res_fields(self):
        """Initialize the valid result fields."""
        super(ListUrls, self)._init_res_fields()
        self._res_fields.extend((('Note', None, None),
                                 ('StoreDate', 0, int)))


#
# MyWeb2 UrlSearch DOM parser
#
class UrlSearch(dom.DOMResultParser):
    """UrlSearch - DOM parser for MyWeb2 URL search

    Each result is a dictionary populated with the extracted data from the
    XML results. The following keys are always available:

        Title        - The title of the web page.
        Summary      - Summary text associated with the web page.
        Url          - The URL for the web page.
        ClickUrl     - The URL for linking to the page.
        User         - The Yahoo! ID of the last user to store the URL (or
                       the user specified with the yahooid parameter).
        Note         - Any note the Yahoo! user has chosen to annotate
                       the URL with.
        Date         - The date the URL was stored, in unix timestamp format.
        Tags         - The tags associated with this URL.
    """
    def _init_res_fields(self):
        """Initialize the valid result fields."""
        super(UrlSearch, self)._init_res_fields()
        self._res_fields.extend((('User', None, None),
                                 ('Note', None, None),
                                 ('Date', 0, int)))

    def _parse_result(self, result):
        """Internal method to parse one Result node"""
        res = super(UrlSearch, self)._parse_result(result)
        node = result.getElementsByTagName('Tags')
        if node:
            res['Tags'] = self._tag_to_list(node[0], 'Tag', casting=None)
        else:
            res['Tags'] = []
        return res


#
# MyWeb2 TagSearch DOM parser
#
class TagSearch(dom.DOMResultParser):
    """UrlSearch - DOM parser for MyWeb2 Tag Search

    Each result is a dictionary populated with the extracted data from the
    XML results. The following keys are always available:

        Tag          - The value of the tag.
        Frequency    - The number of times the tag has been used publicly. IF
                       the query is filtered by yahoo ID, and/or URL, it will
                       return the number of times the tag has been used by
                       that user (and/or on that URL).
        Date         - The date the URL was stored, in unix timestamp format.
    """
    def _init_res_fields(self):
        """Initialize the valid result fields."""
        self._res_fields = [('Tag', None, None),
                            ('Frequency', None, int),
                            ('Date', 0, int),
                            ]



#
# local variables:
# mode: python
# indent-tabs-mode: nil
# py-indent-offset: 4
# end:
