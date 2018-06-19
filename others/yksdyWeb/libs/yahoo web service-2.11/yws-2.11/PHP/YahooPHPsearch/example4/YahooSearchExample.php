<?php
// Yahoo Web Services PHP Example Code
// Rasmus Lerdorf
//
// This version uses PHP5's SimpleXML extension

require './common.php';
$q=build_query();
// Then send it to the appropriate service
$xml = simplexml_load_file($service[$_REQUEST['type']].$q);
// Load up the root element attributes
foreach($xml->attributes() as $name=>$attr) $res[$name]=$attr;

$first = $res['firstResultPosition'];
$last = $first + $res['totalResultsReturned']-1;
echo "<p>Matched ${res[totalResultsAvailable]}, showing $first to $last</p>\n";
if(!empty($res['ResultSetMapUrl'])) {
  echo "<p>Result Set Map: <a href=\"${res[ResultSetMapUrl]}\">${res[ResultSetMapUrl]}</a></p>\n";
}
for($i=0; $i<$res['totalResultsReturned']; $i++) {
  foreach($xml->Result[$i] as $key=>$value) {
    switch($key) {
      case 'Thumbnail':
        echo "<img src=\"{$value->Url}\" height=\"{$value->Height}\" width=\"{$value->Width}\" />\n";
        break;
      case 'Cache':
        echo "Cache: <a href=\"{$value->Url}\">{$value->Url}</a> [{$value->Size}]<br />\n";
        break;
      case 'PublishDate':
      case 'ModificationDate':
        echo "<b>$key:</b> ".strftime('%X %x',$value)."<br />\n";
        break;
      default:
        if(stristr($key,'url')) echo "<a href=\"$value\">$value</a><br />\n";
        else echo "<b>$key:</b> $value<br />";
        break;
    }
  }
  echo "<hr />\n";
}
next_prev($res, $first, $last);
done();
?>
