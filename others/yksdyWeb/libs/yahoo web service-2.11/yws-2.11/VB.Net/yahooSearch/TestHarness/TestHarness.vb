Imports System.IO
Imports Yahoo.API

Public Class TestHarness
    Inherits System.Windows.Forms.Form

#Region " Windows Form Designer generated code "

    Public Sub New()
        MyBase.New()

        'This call is required by the Windows Form Designer.
        InitializeComponent()

        'Add any initialization after the InitializeComponent() call

    End Sub

    'Form overrides dispose to clean up the component list.
    Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing Then
            If Not (components Is Nothing) Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(disposing)
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    Friend WithEvents btnSearch As System.Windows.Forms.Button
    Friend WithEvents btnRelatedSuggestions As System.Windows.Forms.Button
    Friend WithEvents btnSpelling As System.Windows.Forms.Button
    Friend WithEvents btnVideo As System.Windows.Forms.Button
    Friend WithEvents btnNews As System.Windows.Forms.Button
    Friend WithEvents btnLocal As System.Windows.Forms.Button
    Friend WithEvents btnImage As System.Windows.Forms.Button
    Friend WithEvents txtResult As System.Windows.Forms.TextBox
    Friend WithEvents btnTermExtraction As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btnSearch = New System.Windows.Forms.Button
        Me.btnRelatedSuggestions = New System.Windows.Forms.Button
        Me.btnSpelling = New System.Windows.Forms.Button
        Me.btnVideo = New System.Windows.Forms.Button
        Me.btnNews = New System.Windows.Forms.Button
        Me.btnLocal = New System.Windows.Forms.Button
        Me.btnImage = New System.Windows.Forms.Button
        Me.txtResult = New System.Windows.Forms.TextBox
        Me.btnTermExtraction = New System.Windows.Forms.Button
        Me.SuspendLayout()
        '
        'btnSearch
        '
        Me.btnSearch.Location = New System.Drawing.Point(16, 16)
        Me.btnSearch.Name = "btnSearch"
        Me.btnSearch.TabIndex = 0
        Me.btnSearch.Text = "Search"
        '
        'btnRelatedSuggestions
        '
        Me.btnRelatedSuggestions.Location = New System.Drawing.Point(96, 16)
        Me.btnRelatedSuggestions.Name = "btnRelatedSuggestions"
        Me.btnRelatedSuggestions.TabIndex = 1
        Me.btnRelatedSuggestions.Text = "Related Suggestions"
        '
        'btnSpelling
        '
        Me.btnSpelling.Location = New System.Drawing.Point(176, 16)
        Me.btnSpelling.Name = "btnSpelling"
        Me.btnSpelling.TabIndex = 2
        Me.btnSpelling.Text = "Spelling"
        '
        'btnVideo
        '
        Me.btnVideo.Location = New System.Drawing.Point(256, 16)
        Me.btnVideo.Name = "btnVideo"
        Me.btnVideo.TabIndex = 3
        Me.btnVideo.Text = "Video"
        '
        'btnNews
        '
        Me.btnNews.Location = New System.Drawing.Point(16, 48)
        Me.btnNews.Name = "btnNews"
        Me.btnNews.TabIndex = 4
        Me.btnNews.Text = "News"
        '
        'btnLocal
        '
        Me.btnLocal.Location = New System.Drawing.Point(96, 48)
        Me.btnLocal.Name = "btnLocal"
        Me.btnLocal.TabIndex = 5
        Me.btnLocal.Text = "Local"
        '
        'btnImage
        '
        Me.btnImage.Location = New System.Drawing.Point(176, 48)
        Me.btnImage.Name = "btnImage"
        Me.btnImage.TabIndex = 6
        Me.btnImage.Text = "Image"
        '
        'txtResult
        '
        Me.txtResult.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.txtResult.Location = New System.Drawing.Point(16, 80)
        Me.txtResult.Multiline = True
        Me.txtResult.Name = "txtResult"
        Me.txtResult.ScrollBars = System.Windows.Forms.ScrollBars.Both
        Me.txtResult.Size = New System.Drawing.Size(480, 224)
        Me.txtResult.TabIndex = 7
        Me.txtResult.Text = ""
        '
        'btnTermExtraction
        '
        Me.btnTermExtraction.Location = New System.Drawing.Point(256, 48)
        Me.btnTermExtraction.Name = "btnTermExtraction"
        Me.btnTermExtraction.TabIndex = 8
        Me.btnTermExtraction.Text = "Term Extrac"
        '
        'TestHarness
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(512, 318)
        Me.Controls.Add(Me.btnTermExtraction)
        Me.Controls.Add(Me.txtResult)
        Me.Controls.Add(Me.btnImage)
        Me.Controls.Add(Me.btnLocal)
        Me.Controls.Add(Me.btnNews)
        Me.Controls.Add(Me.btnVideo)
        Me.Controls.Add(Me.btnSpelling)
        Me.Controls.Add(Me.btnRelatedSuggestions)
        Me.Controls.Add(Me.btnSearch)
        Me.Name = "TestHarness"
        Me.Text = "Yahoo Search API Example"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub btnSearch_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSearch.Click
        Dim yahoo As New YahooSearchService

        Dim resultSet As Yahoo.API.WebSearchResponse.ResultSet
        resultSet = yahoo.WebSearch("YahooExample", "site:www.mgbrown.com", "all", 10, 1, "any", True, True, "en")

        Dim sw As New StringWriter

        For Each result As Yahoo.API.WebSearchResponse.ResultType In resultSet.Result
            sw.WriteLine("Title: {0}", result.Title)
            sw.WriteLine("Summary: {0}", result.Summary)
            sw.WriteLine("URL: {0}", result.Url)
            sw.WriteLine("===============================================================")
        Next

        txtResult.Text = sw.ToString()
    End Sub

    Private Sub btnRelatedSuggestions_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnRelatedSuggestions.Click
        Dim yahoo As New YahooSearchService

        Dim resultSet As Yahoo.API.WebSearchRelatedResponse.ResultSet
        resultSet = yahoo.WebSearchRelated("YahooExample", "madonna", 10)

        Dim sw As New StringWriter
        For Each result As String In resultSet.Result
            sw.WriteLine("Related Suggestion: {0}", result)
            sw.WriteLine("===============================================================")
        Next

        txtResult.Text = sw.ToString()
    End Sub

    Private Sub btnSpelling_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnSpelling.Click
        Dim yahoo As New YahooSearchService

        Dim resultSet As Yahoo.API.WebSearchSpellingResponse.ResultSet
        resultSet = yahoo.WebSearchSpelling("YahooExample", "madnna")

        Dim sw As New StringWriter
        sw.WriteLine("Spelling Suggestion: {0}", resultSet.Result)

        txtResult.Text = sw.ToString()
    End Sub

    Private Sub btnVideo_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnVideo.Click
        Dim yahoo As New YahooSearchService

        Dim resultSet As Yahoo.API.VideoSearchResponse.ResultSet
        resultSet = yahoo.VideoSearch("YahooExample", "madonna", "all", 10, 1, "any", True)

        Dim sw As New StringWriter
        For Each result As Yahoo.API.VideoSearchResponse.ResultType In resultSet.Result
            sw.WriteLine("Title: {0}", result.Title)
            sw.WriteLine("Summary: {0}", result.Summary)
            sw.WriteLine("URL: {0}", result.Url)
            sw.WriteLine("===============================================================")
        Next

        txtResult.Text = sw.ToString()
    End Sub

    Private Sub btnNews_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnNews.Click
        Dim yahoo As New YahooSearchService

        Dim resultSet As Yahoo.API.NewsSearchResponse.ResultSet
        resultSet = yahoo.NewsSearch("YahooExample", "madonna", "all", 10, 1, "rank", "en")

        Dim sw As New StringWriter
        For Each result As Yahoo.API.NewsSearchResponse.ResultType In resultSet.Result
            sw.WriteLine("Title: {0}", result.Title)
            sw.WriteLine("Summary: {0}", result.Summary)
            sw.WriteLine("URL: {0}", result.Url)
            sw.WriteLine("===============================================================")
        Next

        txtResult.Text = sw.ToString()
    End Sub

    Private Sub btnLocal_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnLocal.Click
        Dim yahoo As New YahooSearchService

        Dim resultSet As Yahoo.API.LocalSearchResponse.ResultSet
        resultSet = yahoo.LocalSearch("YahooExample", "pizza", 10, 1, CType(10.0, Single), "", "", "", "94306", "")

        Dim sw As New StringWriter
        For Each result As Yahoo.API.LocalSearchResponse.ResultType In resultSet.Result
            sw.WriteLine("Title: {0}", result.Title)
            sw.WriteLine("Address: {0}", result.Address)
            sw.WriteLine("URL: {0}", result.Url)
            sw.WriteLine("===============================================================")
        Next

        txtResult.Text = sw.ToString()
    End Sub

    Private Sub btnImage_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnImage.Click
        Dim yahoo As New YahooSearchService

        Dim resultSet As Yahoo.API.ImageSearchResponse.ResultSet
        resultSet = yahoo.ImageSearch("YahooExample", "maddona", "all", 10, 1, "any", True)

        Dim sw As New StringWriter
        For Each result As Yahoo.API.ImageSearchResponse.ResultType In resultSet.Result
            sw.WriteLine("Title: {0}", result.Title)
            sw.WriteLine("Summary: {0}", result.Summary)
            sw.WriteLine("URL: {0}", result.Url)
            sw.WriteLine("===============================================================")
        Next

        txtResult.Text = sw.ToString()
    End Sub

    Private Sub btnTermExtraction_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnTermExtraction.Click
        Dim yahoo As New YahooSearchService

        Dim resultSet As Yahoo.API.TermExtractionResponse.ResultSet
        resultSet = yahoo.TermExtraction("YahooExample", "maddona", "Italian sculptors and painters of the renaissance favored the Virgin Mary for inspiration.")

        Dim sw As New StringWriter
        For Each result As String In resultSet.Result
            sw.WriteLine("{0}", result)
        Next

        txtResult.Text = sw.ToString()
    End Sub
End Class
