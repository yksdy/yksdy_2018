﻿//------------------------------------------------------------------------------
// <autogenerated>
//     This code was generated by a tool.
//     Runtime Version: 1.1.4322.2032
//
//     Changes to this file may cause incorrect behavior and will be lost if 
//     the code is regenerated.
// </autogenerated>
//------------------------------------------------------------------------------

// 
// This source code was auto-generated by xsd, Version=1.1.4322.2032.
// 
namespace Yahoo.API.NewsSearchResponse {
    using System.Xml.Serialization;
    
    
    /// <remarks/>
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:yahoo:yn")]
    [System.Xml.Serialization.XmlRootAttribute(Namespace="urn:yahoo:yn", IsNullable=false)]
    public class ResultSet {
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("Result")]
        public ResultType[] Result;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute(DataType="integer")]
        public string totalResultsAvailable;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute(DataType="integer")]
        public string totalResultsReturned;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute(DataType="integer")]
        public string firstResultPosition;
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:yahoo:yn")]
    public class ResultType {
        
        /// <remarks/>
        public string Title;
        
        /// <remarks/>
        public string Summary;
        
        /// <remarks/>
        public string Url;
        
        /// <remarks/>
        public string ClickUrl;
        
        /// <remarks/>
        public string NewsSource;
        
        /// <remarks/>
        public string NewsSourceUrl;
        
        /// <remarks/>
        public string Language;
        
        /// <remarks/>
        public string PublishDate;
        
        /// <remarks/>
        public string ModificationDate;
        
        /// <remarks/>
        public ImageType Thumbnail;
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:yahoo:yn")]
    public class ImageType {
        
        /// <remarks/>
        public string Url;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="integer")]
        public string Height;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="integer")]
        public string Width;
    }
}
