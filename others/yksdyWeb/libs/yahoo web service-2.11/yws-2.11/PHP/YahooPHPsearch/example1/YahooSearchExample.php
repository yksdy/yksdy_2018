<?php
// Yahoo Web Services PHP Example Code
// Rasmus Lerdorf

require './common.php';
$q=build_query();
// Then send it to the appropriate service
$xml = file_get_contents($service[$_REQUEST['type']].$q);

// Parse the XML and check it for errors
if (!$dom = domxml_open_mem($xml,DOMXML_LOAD_PARSING,$error)) {
  echo "XML parse error\n";
  foreach ($error as $errorline) {
  /* For production use this should obviously be logged to a file instead */
    echo $errorline['errormessage']."<br />\n";
    echo " Node  : " . $errorline['nodename'] . "<br />\n";
    echo " Line  : " . $errorline['line'] . "<br />\n";
    echo " Column : " . $errorline['col'] . "<br />\n";
  }
  done();
}

// Now traverse the DOM with this function
// It is basically a generic parser that turns limited XML into a PHP array
// with only a couple of hardcoded tags which are common across all the
// result xml from the web services
function xml_to_result($dom) {
  $root = $dom->document_element();
  $res['totalResultsAvailable'] = $root->get_attribute('totalResultsAvailable');
  $res['totalResultsReturned'] = $root->get_attribute('totalResultsReturned');
  $res['firstResultPosition'] = $root->get_attribute('firstResultPosition');

  $node = $root->first_child();
  $i = 0;
  while($node) {
    switch($node->tagname) {
      case 'Result':
        $subnode = $node->first_child();
        while($subnode) {
          $subnodes = $subnode->child_nodes();
          if(!empty($subnodes)) foreach($subnodes as $k=>$n) {
            if(empty($n->tagname)) $res[$i][$subnode->tagname] = trim($n->get_content());
            else $res[$i][$subnode->tagname][$n->tagname]=trim($n->get_content());
          }
          $subnode = $subnode->next_sibling();
        }
        break;
      default:
        $res[$node->tagname] = trim($node->get_content());
        $i--;
        break;
    }
    $i++;
    $node = $node->next_sibling();
  }  
  return $res;
}

$res = xml_to_result($dom);

// Ok, now that we have the results in an easy to use format,
// display them.  It's quite ugly because I am using a single
// display loop to display every type.
$first = $res['firstResultPosition'];
$last = $first + $res['totalResultsReturned']-1;
echo "<p>Matched ${res[totalResultsAvailable]}, showing $first to $last</p>\n";
if(!empty($res['ResultSetMapUrl'])) {
  echo "<p>Result Set Map: <a href=\"${res[ResultSetMapUrl]}\">${res[ResultSetMapUrl]}</a></p>\n";
}
for($i=0; $i<$res['totalResultsReturned']; $i++) {
  foreach($res[$i] as $key=>$value) {
    switch($key) {
      case 'Thumbnail':
        echo "<img src=\"${value[Url]}\" height=\"${value[Height]}\" width=\"${value[Width]}\" />\n";
        break;
      case 'Cache':
        echo "Cache: <a href=\"${value[Url]}\">${value[Url]}</a> [${value[Size]}]<br />\n";
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
