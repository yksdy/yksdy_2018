/*
Copyright (c) 2007, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 2.2.0
*/
package com.yahoo.webapis.search
{
	/**
	 * Artist is a Value Object for the Search API.
	 * @langversion ActionScript 3.0
	 * @playerversion Flash 9
	 * @author Alaric Cole 02/22/07
	 */
	public class Artist
	{
		
		private var _name:String;
		private var _id:String;
		
		/**
		 * Construct a new Artist instance.
		 */
		public function Artist()
		{
			// Do Nothing.
		}
		
		/**
		 * The name of the Artist.
		 */
		public function get name():String
		{
			return _name;
		}
		
		public function set name( value:String ):void
		{
			_name = value;
		} 
		
		/**
		 * The id of the Artist.
		 */
		public function get id():String
		{
			return _id;
		}
		
		public function set id( value:String ):void
		{
			_id = value;
		}
		
		
		/**
		 * The label of the Artist.
		 */
		public function toString():String
		{
			return _name;
		}
	}
}