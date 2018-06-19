import com.yahoo.core.CoreObject;
import com.yahoo.core.CoreInterface;
import com.yahoo.event.Observable;
import com.yahoo.event.Event;
import com.yahoo.event.EventDispatcher;

/**
 * 
 * @see     CoreObject	
 * @see     Observable	
 */
class com.yahoo.event.ObservableObject extends CoreObject implements Observable{
	private var $eventDispatcher:EventDispatcher;
	public function ObservableObject(Void){
		super();
		this.$instanceDescription = 'com.yahoo.event.ObservableObject';
		this.$eventDispatcher = new EventDispatcher();
	}

	/*	conform to the Observable interface	 and delegate calls to this.$eventDispatcher		*/
	/**
	 * 
	 * @param   observerObject 
	 * @param   messageName    
	 */
	public function addEventObserver(observerObject, messageName:String):Void
	{
		this.$eventDispatcher.addEventObserver(observerObject, messageName);
		if(arguments.length > 2)
		{
			for(var i = 2; i < arguments.length; i++)
			{
				this.$eventDispatcher.addEventObserver(observerObject, arguments[i]);
			}
		}
	}
	/**
	 * 
	 * @param   observerObject 
	 * @param   messageName    
	 */
	public function removeEventObserver(observerObject, messageName:String):Void{
		this.$eventDispatcher.removeEventObserver(observerObject, messageName);
	}
	public function removeAllEventObservers():Void{
		this.$eventDispatcher.removeAllEventObservers();
	}

	/*	private dispatch method. delegates to this.$eventDispatcher	*/
	private function dispatchEvent(e:com.yahoo.event.Event):Void{
		this.$eventDispatcher.dispatchEvent(e, this);
	}

}

