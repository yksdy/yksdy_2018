import com.yahoo.movieclip.ObservableMovieClip;
import com.yahoo.table.TableCell;

class com.yahoo.table.Table extends ObservableMovieClip
{
	public static var SymbolName:String = '__Packages.Table';
	public static var SymbolLinked = Object.registerClass(SymbolName, Table);

	public static var CELL_SIZE:Number = 20;
	
	private var $multipleSelection:Boolean;
	private var $cellPadding:Number;
	private var $columns:Number;
	private var $lastSelectedIndex:Number;
	private var $modes:Array;
	private var $items:Array;
	private var $view:MovieClip;
	
	public function Table()
	{
		this.setClassDescription('Table');
		this.$items = new Array;
		this.$modes = new Array;
		this.$cellPadding = 10;
	}
	public function init(columns:Number, items:Number, multipleSelection:Boolean):Void
	{
		if(this.$view != undefined)
		{
			this.$view.removeMovieClip();
		}
		this.$view = this.createEmptyMovieClip('$view', this.getNextHighestDepth());
		
		this.$multipleSelection = multipleSelection;
		this.$columns = columns;
		for(var i:Number = 0; i < items; i++)
		{
			this.addCell();
		}
		this.positionCells();
	}
	public function unloadImages():Void
	{
		this.trace('unloadImages invoked');
		for(var i:Number = 0; i < this.$items.length; i++)
		{
			this.$items[i].unloadImage();
		}
	}
	public function selectCell(index:Number):Boolean
	{
		this.trace('selectCell invoked');
		var exists:Boolean = false;
		var cell:TableCell;
		for(var i:Number = 0; i < this.$items.length; i++)
		{
			cell = TableCell(this.$items[i]);
			
			if(i == index)
			{
				this.$lastSelectedIndex = i;
				exists = true;
				if(cell.selected != true)
				{
					cell.selected = true
				}
			}
			else
			{
				if(cell.selected == true)
				{
					cell.selected = false;
				}
			}
		}
		return exists;
	}
	public function setCellMode(index:Number, mode:String):Void
	{
		this.$items[index].mode = mode;
	}
	public function getCellMode(index:Number):String
	{
		return this.$items[index].mode;
	}
	public function addMode(mode:String, color:Number):Void
	{
		this.$modes[mode] = color;
	}
	private function addCell():TableCell
	{
		var cell:TableCell = TableCell(this.$view.attachMovie(TableCell.SymbolName, 'cell' + this.$items.length, this.$view.getNextHighestDepth()));
		cell.init(this, CELL_SIZE, 0x33CCCC, 0xFFFFFF);
		cell.index = this.$items.length;
		cell.addEventObserver(this, 'onCellSelect');
		this.$items.push(cell);
		
		return cell;
	}
	public function positionCells():Void
	{
		this.trace('positionCells invoked');
		this.trace('this.$items.length: ' + this.$items.length);
		this.trace('this.$columns: ' + this.$columns);
		var cell:TableCell;
		var cellRow:Number;
		var cellColumn:Number;
		var cellX:Number;
		var cellY:Number;
		for(var i:Number = 0; i < this.$items.length; i++)
		{
			cell = TableCell(this.$items[i]);
			cellRow = Math.floor(i / this.$columns);
			cellColumn = Number(i - (this.$columns * cellRow));
			cellX = Number(cellColumn * cell.width);
			cellY = Number(cellRow * cell.height);
			cell._x = cellX;
			cell._y = cellY;
		}
		this.dispatchEvent(new com.yahoo.event.Event('onTableCellsPositioned'));
	}
	public function onCellSelect(e:com.yahoo.event.Event):Void
	{
		this.trace('onCellSelect invoked');
		this.dispatchEvent(e);
	}
	public function deselectAll():Void
	{
		this.trace('deselectAll invoked');
		var cell:TableCell;
		for(var i:Number = 0; i < this.$items.length; i++)
		{
			cell = TableCell(this.$items[i]);
			if(cell.selected != false)
			{
				cell.selected = false;
			}
		}
	}
	public function get view():MovieClip
	{
		return this.$view;
	}
	public function get columns():Number
	{
		return this.$columns;
	}
	public function set columns(arg:Number):Void
	{
		this.$columns = arg;
		this.positionCells();
	}
	public function get cellPadding():Number
	{
		return this.$cellPadding;
	}
	public function set cellPadding(arg:Number):Void
	{
		this.$cellPadding = arg;
	}
	public function get multipleSelection():Boolean
	{
		return this.$multipleSelection;
	}
	public function get selectedCell():TableCell
	{
		return this.$items[this.$lastSelectedIndex];
	}
	public function set multipleSelection(arg:Boolean):Void
	{
		this.$multipleSelection = arg;
	}
	public function getCellColorByMode(mode:String):Number
	{
		return Number(this.$modes[mode]);
	}
	public function getCell(index:Number):TableCell
	{
		return TableCell(this.$items[index]);
	}
	public function loadImage(index:Number, url:String)
	{
		this.$items[index].loadImage(url);
	}
}