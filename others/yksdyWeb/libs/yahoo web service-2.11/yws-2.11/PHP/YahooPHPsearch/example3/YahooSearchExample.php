<?php
// Yahoo Web Services PHP Example Code
// Rasmus Lerdorf
//
// This version uses PHP5's new unified dom architecture

require './common.php';
$q=build_query();
// Then send it to the appropriate service
$dom = DOMDocument::load($service[$_REQUEST['type']].$q);

// Now traverse the DOM 
function xml_to_result($dom) {
  $root = $dom->firstChild;
  foreach($root->attributes as $attr) $res[$attr->name] = $attr->value;
  $node = $root->firstChild;
  $i = 0;
  while($node) {
    switch($node->nodeName) {
      case 'Result':
        $subnode = $node->firstChild;
        while($subnode) {
          $subnodes = $subnode->childNodes;
          foreach($subnodes as $n) {
            if($n->hasChildNodes()) {
				foreach($n->childNodes as $cn) $res[$i][$subnode->nodeName][$n->nodeName]=trim($cn->nodeValue);
			} else $res[$i][$subnode->nodeName]=trim($n->nodeValue);
          }
          $subnode = $subnode->nextSibling;
        }
        break;
      default:
        $res[$node->nodeName] = trim($node->nodeValue);
        $i--;
        break;
    }
    $i++;
    $node = $node->nextSibling;
  }  
  return $res;
}

$res = xml_to_result($dom);

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
