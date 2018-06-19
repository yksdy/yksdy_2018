import com.yahoo.core.CoreInterface;
/**
 * 
 * @see     CoreInterface	
 */
interface com.yahoo.event.Listenable extends CoreInterface{
	/**
	 * 
	 * @param   e 
	 */
	public function handleEvent(e:com.yahoo.event.Event):Void;
}