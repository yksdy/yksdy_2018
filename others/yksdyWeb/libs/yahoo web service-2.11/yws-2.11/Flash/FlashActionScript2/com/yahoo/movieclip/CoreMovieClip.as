import com.yahoo.core.CoreInterface;
	
/**
 * 
 * @see     MovieClip	
 * @see     CoreInterface	
 */
class com.yahoo.movieclip.CoreMovieClip extends MovieClip implements CoreInterface
{
	private var $instanceDescription:String;

	function CoreMovieClip(Void)
	{
		this.setClassDescription('com.yahoo.movieclip.CoreMovieClip');
	}
	/**
	 * 
	 * @param   str 
	 */
	public function trace(str):Void
	{
		trace('['+this.$instanceDescription+']' + str);
	}
	public function get classType():String
	{
		return this.$instanceDescription.toString().substring(this.$instanceDescription.toString().lastIndexOf('.') + 1);
	}
	public function toString(Void):String
	{
		return this.$instanceDescription;
	}
	private function setClassDescription(d:String):Void
	{
		this.$instanceDescription = d;
	}

}

