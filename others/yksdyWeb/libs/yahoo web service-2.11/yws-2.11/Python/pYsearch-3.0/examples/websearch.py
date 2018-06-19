#!/usr/bin/python

import sys
import getopt

from yahoo.search import factory, debug


#
# Print usage/help information, and exit
#
def usage_exit(msg=None):
    if msg:
        print "Error: ",
        print msg
        print
    print """\
Usage: websearch [options] query

Options:
  -h, --help         Show this message
  -t, --type         Yahoo search service type (web, news, context, image,
                     video, spelling, related, podcast, pagedata or
                     inlinkdata)
  -i, --appid        Application ID to send to Yahoo servers
  -r, --results      Show this many results
  -s, --start        Show results starting at this position
  -a, --adult-ok     Allow adult content in the results
  -f, --format       Limit the search to specific data formats
  -l, --license      Creative Commons License
  -L, --language     Language (only for web search)
  -c, --country      Country (only for web search)
  -S, --subscription Subscription code (web search only)
  -R, --region       The region to send the search to (e.g. "uk")
  -C, --coloration   Coloration of images (image search only)
  -x, --context      Context string (for context search and term extraction)
  -u, --site         Site information/URL (used for site discovery services)
  -F, --flag         Flag (0 or 1) used by various services
  -D, --raw          Print a "raw" version of each result
  -j, --json         Get the results as JSON objects
  -p, --php          Get the results as PHP code
  -v, --verbose      Show more verbose results, including summary
  -d, --debug        Turn on some debugging information

Examples:
  websearch.py -t news -s 50 -r 20 -a Yahoo
  websearch.py -v -t web -f pdf "Linux threads"
"""
    sys.exit(2)


#
# Parse arguments and create the search object
#
if __name__ == "__main__":

    # Options/settings
    params = {  'verbose' : 0,
                'raw' : 0,
                'debug' : 0,
                'type' : 'Web',
                'results' : 10,
                'start' : 1,
                'adult' : None,
                'format' : None,
                'language' : None,
                'country' : None,
                'coloration' : None,
                'context' : None,
                'region' : None,
                'appid' : 'YahooDemo',
                'flag' : 0,
                'json' : 0,
                'php' : 0,
                'license' : [],
                'site' : [],
                'subscription' : [],
                }

    try:
        options, args = getopt.getopt(sys.argv[1:],
                                      'hHt:i:r:s:af:l:L:c:S:R:C:x:u:F:Djpvd:',
                                      ['help',
                                       'type=',
                                       'appid=',
                                       'results=',
                                       'start=',
                                       'adult-ok',
                                       'format=',
                                       'license=',
                                       'language=',
                                       'country=',
                                       'subscription=',
                                       'region=',
                                       'coloration=',
                                       'context=',
                                       'site=',
                                       'flag=',
                                       'raw',
                                       'json',
                                       'php',
                                       'verbose',
                                       'debug=',
                                       ])
        for opt, value in options:
            if opt in ('-h', '-H','--help'):
                usage_exit()
            elif opt in ('-v', '--verbose'):
                params['verbose'] += 1
            elif opt in ('-D', '--raw'):
                params['raw'] += 1
            elif opt in ('-j', '--json'):
                params['json'] += 1
            elif opt in ('-p', '--php'):
                params['php'] += 1
            elif opt in ('-t', '--type'):
                params['type'] = value.lower()
            elif opt in ('-r', '--results'):
                try:
                    params['results'] = int(value)
                except ValueError:
                    usage_exit("Results must be an integer between 1 and 50")
            elif opt in ('-s', '--start'):
                try:
                    params['start'] = int(value)
                except ValueError:
                    usage_exit("Start must be an integer")
            elif opt in ('-a', '--adult-ok'):
                params['adult'] = 1
            elif opt in ('-f', '--format'):
                params['format'] = value
            elif opt in ('-l', '--license'):
                if not value.lower() in params['license']:
                    params['license'].append(value.lower())
            elif opt in ('-u', '--site'):
                if not value in params['site']:
                    params['site'].append(value)
            elif opt in ('-S', '--subscription'):
                if not value.lower() in params['subscription']:
                    params['subscription'].append(value.lower())
            elif opt in ('-L', '--language'):
                params['language'] = value
            elif opt in ('-c', '--country'):
                params['country'] = value
            elif opt in ('-R', '--region'):
                params['region'] = value
            elif opt in ('-C', '--coloration'):
                params['coloration'] = value
            elif opt in ('-x', '--context'):
                params['context'] = value
            elif opt in ('-F', '--flag'):
                try:
                    params['flag'] = int(value)
                except ValueError:
                    usage_exit("Flag must be an integer, 0 or 1")
            elif opt in ('-i', '--appid'):
                params['appid'] = value
            elif opt in ('-d', '--debug'):
                if debug.DEBUG_LEVELS.has_key(value.upper()):
                    params['debug'] = params['debug'] | debug.DEBUG_LEVELS[value.upper()]
                else:
                    try:
                        params['debug'] = int(value)
                    except:
                        usage_exit("Debug value must be a valid string or integer")
    except getopt.error, msg:
        usage_exit(msg)

    try:
        srch = factory.create_search(params['type'], params['appid'],
                                     debug_level=params['debug'])
    except ValueError:
        usage_exit("AppID can only contain a-zA-Z0-9 _()[]*+-=,.:\@, 8-40 characters")

    if srch is None:
        usage_exit("%s is not a valid search service type" % params['type'])

    if params['format'] is not None:
        try:
            srch.format = params['format']
        except ValueError, err:
            usage_exit(err)
    if params['adult'] is not None:
        srch.adult_ok = 1
    if params['language'] is not None:
        srch.language = params['language']
    if params['country'] is not None:
        srch.country = params['country']
    if params['region'] is not None:
        srch.region = params['region']
    if params['coloration'] is not None:
        srch.coloration = params['coloration']
    if params['context'] is not None:
        srch.context = params['context']
    if len(params['license']) > 0:
        srch.license = params['license']
    if len(params['site']) > 0:
        srch.site = params['site']
    if len(params['subscription']) > 0:
        srch.subscription = params['subscription']
    if params['json'] > 0:
        srch.output = "json"
    elif params['php'] > 0:
        srch.output = "php"
    # This is odd, but useful ...
    if params['flag'] > 0:
        if params['type'] == 'inlinkdata':
            srch.entire_site = params['flag']
        elif params['type'] == 'pagedata':
            srch.domain_only = params['flag']

    srch.results = params['results']
    srch.start = params['start']

    srch.query = " ".join(args)
    if srch.query == "" and params['type'].lower() != 'term':
        usage_exit("You must provide a search query")

    if ((params['json'] > 0) or (params['php'] > 0)):
        print srch.get_results()
    else:
        try:
            results = srch.parse_results()
        except Exception, err:
            print "Got an error ->", err
            sys.exit(1)
        
        if results.total_results_returned > 0:
            print "Search for %s -> %s results (showing %d - %d)\n" % (
                srch.query,
                results.total_results_available,
                srch.start,
                srch.start + results.total_results_returned - 1)

            # The try clauses are weird, but it seems less and other apps can cause
            # the decoding of Unicode characters to raise exceptions.
            cnt = srch.start
            if (params['type'].lower() in ("spelling", "related", "term") or
                params['raw'] > 0):
                result_only = True
            else:
                result_only = False
            for res in results:
                try:
                    if result_only:
                        print "[%3d]\t%s" % (cnt, res)
                    else:
                        print "[%3d]\t%s\n\t%s" % (cnt, res.Title, res.Url)
                except UnicodeEncodeError:
                    print "[%3d]\tfailed to decode data" % (cnt)
                cnt += 1
                if params['verbose'] > 0:
                    try:
                        print "\t%s" % (res.Summary)
                    except UnicodeEncodeError:
                        print "\tfailed to decode Summary"
                if params['verbose'] > 1:
                    try:
                        print res
                    except UnicodeEncodeError:
                        pass
            

        else:
            print "No results"



#
# local variables:
# mode: python
# indent-tabs-mode: nil
# py-indent-offset: 4
# end:
