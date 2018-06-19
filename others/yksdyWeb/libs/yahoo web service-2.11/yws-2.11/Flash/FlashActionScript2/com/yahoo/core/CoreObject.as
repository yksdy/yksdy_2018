import com.yahoo.core.CoreInterface;

/**
 * 
 * @see     CoreInterface	
 */
class com.yahoo.core.CoreObject implements CoreInterface{
	private var $instanceDescription:String;
	/*
	* Sole Constructor
	*/
	public function CoreObject(Void){
		this.setClassDescription('com.yahoo.core.CoreObject');
	}
	/**
	 * 
	 * @param   str 
	 */
	public function trace(str):Void
	{
		trace('['+this.$instanceDescription+']' + str);
	}
	/**
	 * Returns a reference to internal $instanceDescription var
	 */
	public function toString(Void):String{
		return this.$instanceDescription;
	}
	private function setClassDescription(d:String):Void
	{
		this.$instanceDescription = d;
	}
	public function get classType():String
	{
		return this.$instanceDescription.toString().substring(this.$instanceDescription.toString().lastIndexOf('.') + 1);
	}
}
