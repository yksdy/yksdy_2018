import com.yahoo.movieclip.ObservableMovieClip;
import com.yahoo.table.Table;

class com.yahoo.table.TableCell extends ObservableMovieClip
{
	public static var SymbolName:String = '__Packages.TableCell';
	public static var SymbolLinked = Object.registerClass(SymbolName, TableCell);
	
	private var $table:Table;
	private var $selected:Boolean;
	private var $size:Number;
	private var $row:Number;
	private var $column:Number;
	private var $index:Number;
	private var $selectColor:Number;
	private var $deselectColor:Number;
	private var $mode:String;
	private var $bg:MovieClip;
	private var $view:MovieClip;
	private var $data:Object;
	
	public function TableCell()
	{
		this.setClassDescription('TableCell');
		this.addEventObserver(this, 'onCellSelect');
		this.addEventObserver(this, 'onCellDeselect');
	}
	public function init(table:Table, size:Number, selectColor:Number, deselectColor:Number):Void
	{
		trace('init invoked')
		this.$table = table;
		this.$size = size + table.cellPadding;
		this.$selected = false;
		this.$selectColor = selectColor;
		this.$deselectColor = deselectColor;
		this.$view = this.createEmptyMovieClip('$view', this.getNextHighestDepth());
		this.paint();
	}
	private function paint():Void
	{
		//this.trace('painting '+ this.$selected)
		if(this.$selected)
		{
			this.$bg = com.yahoo.util.DrawingUtil.drawRectangle(this, this.$size, this.$size, 0, 0, 0, this.$selectColor, 0x999999);
		}
		else
		{
			if(this.$mode == undefined)
			{
				this.$bg = com.yahoo.util.DrawingUtil.drawRectangle(this, this.$size, this.$size, 0, 0, 0, this.$deselectColor, 0x999999);
			}
			else
			{
				this.$bg = com.yahoo.util.DrawingUtil.drawRectangle(this, this.$size, this.$size, 0, 0, 0, this.$table.getCellColorByMode(this.$mode), 0x999999);
			}
		}
		
		this.decorate();
		this.bringViewToFront();
	}
	private function decorate():Void
	{
		this.$bg.onRollOver = function():Void
		{
			this._alpha = 25;
		}
		this.$bg.onRollOut = function():Void
		{
			this._alpha = 100;
		}
	}
	public function onCellSelect():Void
	{
		this.trace('onCellSelect invoked');
	}
	public function onCellDeselect():Void
	{
		this.trace('onCellDeselect invoked');		
	}
	public function get selected():Boolean
	{
		return this.$selected;
	}
	public function set selected(arg:Boolean):Void
	{
		this.$selected = arg;
		var e:com.yahoo.event.Event = new com.yahoo.event.Event;
		e.addArgument('cell', this);
		if(this.$selected)
		{
			e.setType('onCellSelect');
			this.dispatchEvent(e);
		}
		else
		{
			e.setType('onCellDeselect');
			this.dispatchEvent(e);
		}
		this.paint();
	}
	public function get row():Number
	{
		return this.$row;
	}
	public function get column():Number
	{
		return this.$column;
	}
	public function get index():Number
	{
		return this.$index;
	}
	public function get table():Table
	{
		return this.$table;
	}
	/*
	public function set row(arg:Number):Void
	{
		this.$row = arg;
	}
	public function set cell(arg:Number):Void
	{
		this.$cell = arg;
	}
	*/
	public function set mode(arg:String):Void
	{
		this.$mode = arg;
		this.paint();
	}
	public function set index(arg:Number):Void
	{
		this.$index = arg;
	}
	public function onRelease():Void
	{
		this.$table.selectCell(this.$index);
	}
	public function get width():Number
	{
		return this.$size;
	}
	public function get height():Number
	{
		return this.$size;
	}
	public function get view():MovieClip
	{
		return this.$view;
	}
	/**
	* Accessor for private $data var.
	* @return Object.
	**/
	public function get data():Object
	{
		return this.$data;
	}
	/**
	* Mutator for private $data var.
	* @param arg Object
	**/
	public function set data(arg:Object) 
	{
		this.$data = arg;
	}

	public function unloadImage():Void
	{
		this.trace('unloadImage invoked');
		this.$view.removeMovieClip();
		this.$view = this.createEmptyMovieClip('$view', this.getNextHighestDepth());
		this.bringViewToFront();
	}
	public function loadImage(url:String):Void
	{
		var mcLoader:MovieClipLoader = new MovieClipLoader();
		mcLoader.addListener(this);
		mcLoader.loadClip(url+'?'+new Date().getTime(), this.$view);
		this.bringViewToFront();
	}
	public function bringViewToFront():Void
	{
		this.$view.swapDepths(this.getNextHighestDepth());
	}
	
	public function onLoadInit (image:MovieClip, httpStatus:Number):Void 
	{
		
		var widthOffset:Number = this.$size / image._width;
		var heightOffset:Number = this.$size / image._height;
		var scaleOffset:Number = 0;
		(widthOffset < heightOffset) ? (scaleOffset = widthOffset) : (scaleOffset = heightOffset);
		trace('image._width: ' + image._width);						
		trace('this.$size : ' + this.$size );						
		trace('widthOffset: ' + widthOffset);
		trace('heightOffset: ' + heightOffset);
		trace('scaleOffset: ' + scaleOffset);
		
		if(scaleOffset < 1)
		{
			image._width = image._width * scaleOffset - 5;
			image._height = image._height * scaleOffset - 5;
		}
		centerInsideClip(this, this.$view)
	}
	public function centerInsideClip(parentClip:MovieClip, clipToBeCentered:MovieClip, vertical:Boolean, horiz:Boolean):Void
	{
		if(horiz != false)
		{
			trace('parentClip._width: ' + parentClip._width);
			trace('clipToBeCentered._width: ' + clipToBeCentered._width);
			clipToBeCentered._x = (parentClip._width - clipToBeCentered._width) / 2;
		}
		if(vertical != false)
		{
			clipToBeCentered._y = (parentClip._height- clipToBeCentered._height) / 2;
		}
	}
}