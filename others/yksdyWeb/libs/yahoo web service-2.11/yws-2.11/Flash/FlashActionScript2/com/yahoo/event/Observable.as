import com.yahoo.core.CoreInterface;
/**
 * 
 * @see     CoreInterface	
 */
interface com.yahoo.event.Observable extends CoreInterface{
	/**
	 * 
	 * @param   observerObject 
	 * @param   messageName    
	 */
	public function addEventObserver(observerObject, messageName:String):Void;
	/**
	 * 
	 * @param   observerObject 
	 * @param   messageName    
	 */
	public function removeEventObserver(observerObject, messageName:String):Void;
	public function removeAllEventObservers():Void;
}
