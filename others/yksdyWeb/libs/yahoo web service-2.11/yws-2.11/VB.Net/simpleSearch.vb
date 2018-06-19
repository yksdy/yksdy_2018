'
' simpleSearch.cs -- simple web services search in C#
' Author: Daniel Jones
' Copyright 2006 Daniel Jones
' Licensed under BSD open source license
' http://www.opensource.org/licenses/bsd-license.php
'
'
Module YahooWebServiceExample
    Sub Main()
        Dim request As String =
"http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=madonna&results=2"
        Dim wc As New System.Net.WebClient
        Try
            Dim html As String = System.Text.Encoding.UTF8.GetString(wc.DownloadData(request))
            System.Console.WriteLine(html)
        Catch ex As Exception
            System.Console.WriteLine("Web services request failed")
        End Try
    End Sub
End Module
