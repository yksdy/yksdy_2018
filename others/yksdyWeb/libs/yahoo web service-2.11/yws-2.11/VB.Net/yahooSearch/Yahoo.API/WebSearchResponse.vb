﻿'------------------------------------------------------------------------------
' <autogenerated>
'     This code was generated by a tool.
'     Runtime Version: 1.1.4322.2032
'
'     Changes to this file may cause incorrect behavior and will be lost if 
'     the code is regenerated.
' </autogenerated>
'------------------------------------------------------------------------------

Option Strict Off
Option Explicit On

Imports System.Xml.Serialization

'
'This source code was auto-generated by xsd, Version=1.1.4322.2032.
'
Namespace Yahoo.API.WebSearchResponse
    
    '<remarks/>
    <System.Xml.Serialization.XmlTypeAttribute([Namespace]:="urn:yahoo:srch"),  _
     System.Xml.Serialization.XmlRootAttribute([Namespace]:="urn:yahoo:srch", IsNullable:=false)>  _
    Public Class ResultSet
        
        '<remarks/>
        <System.Xml.Serialization.XmlElementAttribute("Result")>  _
        Public Result() As ResultType
        
        '<remarks/>
        <System.Xml.Serialization.XmlAttributeAttribute(DataType:="integer")>  _
        Public totalResultsAvailable As String
        
        '<remarks/>
        <System.Xml.Serialization.XmlAttributeAttribute(DataType:="integer")>  _
        Public totalResultsReturned As String
        
        '<remarks/>
        <System.Xml.Serialization.XmlAttributeAttribute(DataType:="integer")>  _
        Public firstResultPosition As String
    End Class
    
    '<remarks/>
    <System.Xml.Serialization.XmlTypeAttribute([Namespace]:="urn:yahoo:srch")>  _
    Public Class ResultType
        
        '<remarks/>
        Public Title As String
        
        '<remarks/>
        Public Summary As String
        
        '<remarks/>
        Public Url As String
        
        '<remarks/>
        Public ClickUrl As String
        
        '<remarks/>
        Public ModificationDate As String
        
        '<remarks/>
        Public MimeType As String
        
        '<remarks/>
        Public Cache As CacheType
    End Class
    
    '<remarks/>
    <System.Xml.Serialization.XmlTypeAttribute([Namespace]:="urn:yahoo:srch")>  _
    Public Class CacheType
        
        '<remarks/>
        Public Url As String
        
        '<remarks/>
        Public Size As String
    End Class
End Namespace