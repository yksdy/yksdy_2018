<?php
// Yahoo Web Services PHP Example Code
// Rasmus Lerdorf
//
// This file contains the common pieces used by all the examples.
// 
$appid = 'YahooDemo';

$service = array('image'=>'http://search.yahooapis.com/ImageSearchService/V1/imageSearch',
                 'local'=>'http://local.yahooapis.com/LocalSearchService/V1/localSearch',
                 'news'=>'http://search.yahooapis.com/NewsSearchService/V1/newsSearch',
                 'video'=>'http://search.yahooapis.com/VideoSearchService/V1/videoSearch',
                 'web'=>'http://search.yahooapis.com/WebSearchService/V1/webSearch');

header('Content-Type: text/html; charset=UTF-8');
?>
<html>
<head><title>PHP Yahoo Web Service Example Code</title></head>
<body>
<form action="YahooSearchExample.php" method="GET">
Search Term: <input type="text" name="query" /><br />
Zip Code: <input type="text" name="zip" /> (for local search)<br />
<input type="submit" value=" Go! " />
<select name="type">
<?php foreach($service as $name => $val) {
    if(!empty($_REQUEST['type']) && $name == $_REQUEST['type'])
      echo "<option SELECTED>$name</option>\n";
    else echo "<option>$name</option>\n";
} ?>
</select>
</form>
<?php

function done() {
  echo '</body></html>';
  exit;
}

function build_query() {
  global $appid, $service;
  if(empty($_REQUEST['query']) || !in_array($_REQUEST['type'],array_keys($service))) done();

  $q = '?query='.rawurlencode($_REQUEST['query']);
  if(!empty($_REQUEST['zip'])) $q.="&zip=".$_REQUEST['zip'];
  if(!empty($_REQUEST['start'])) $q.="&start=".$_REQUEST['start'];
  $q .= "&appid=$appid";
  return $q;
}

// Create Previous/Next Page links
function next_prev($res, $start, $last) {
  if($start > 1)
    echo '<a href="'.$_SERVER['PHP_SELF'].
                   '?query='.rawurlencode($_REQUEST['query']).
                   '&zip='.rawurlencode($_REQUEST['zip']).
                   '&type='.rawurlencode($_REQUEST['type']).
                   '&start='.($start-10).'">&lt;-Previous Page</a> &nbsp; ';
  if($last < $res['totalResultsAvailable'])
    echo '<a href="'.$_SERVER['PHP_SELF'].
                   '?query='.rawurlencode($_REQUEST['query']).
                   '&zip='.rawurlencode($_REQUEST['zip']).
                   '&type='.rawurlencode($_REQUEST['type']).
                   '&start='.($last+1).'">Next Page-&gt;</a>';
}
?>
