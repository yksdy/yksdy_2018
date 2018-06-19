#!/usr/bin/env perl

# Author: Premshree Pillai
# http://premshree.livejournal.com

use LWP::Simple;

my $url = "http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=madonna&results=2";

# make the request
my $results = get $url || die "Web services request failed: $!";

my %entities = ( "<"=>"&lt;", ">"=>"&gt;", "&"=>"&amp;", "'"=>"&#039;", '"'=>"&quot;" );
while (my ($k, $v) = each(%entities)) {
	$results =~ s/$k/$v/g;
}
print $results;
