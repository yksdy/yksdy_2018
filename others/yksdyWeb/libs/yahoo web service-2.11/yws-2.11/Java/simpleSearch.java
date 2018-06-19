/**
 * Simple Search using Yahoo! Web Services
 *
 * @author Daniel Jones
 * Copyright 2006 Daniel Jones
 * Licensed under BSD open source license
 * http://www.opensource.org/licenses/bsd-license.php
*/

import java.io.InputStream;
import java.net.URL;

public class YahooWebServiceExample {

       public static void main(String[] args) {
               String request = "http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=madonna&results=2";
               try {
                       URL url = new URL(request);
                       InputStream in = url.openStream();
                       byte[] buf = new byte[1024];
                       int len;
                       while ((len = in.read(buf)) > 0) {
                               for (int i = 0; i < len; i++) {
                                       System.out.print((char) buf[i]);
                               }
                       }
                       in.close();
               } catch (Exception e) {
                       System.out.println("Web services request failed");
               }
       }
}
