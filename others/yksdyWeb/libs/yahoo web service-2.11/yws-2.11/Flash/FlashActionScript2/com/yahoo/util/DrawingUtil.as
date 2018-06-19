import com.yahoo.core.CoreObject;

/**
 * This class provides a layer of abstraction for common drawing tasks 
 * @see     CoreObject	
 */
class com.yahoo.util.DrawingUtil extends CoreObject
{
	public static function drawLine(target:MovieClip, stroke:Number, color:Number, x:Number, y:Number)
	{
		target.lineStyle(stroke, color);
		target.lineTo(x, y);
	}
	/**
	 * This method draws a box inside of the target MovieClip instance
	 * 
	 * @usage   DrawingUtil.drawRectangle(target, w, h, 0, 0, .25, 0x666666, 0x999999);
	 * @param   target   (MovieClip)  MovieClip instance to draw box into
	 * @param   w           (Number) width of the box
	 * @param   h           (Number) height of the box
	 * @param   x           (Number) x coordinate position of the box
	 * @param   y           (Number) y coordinate position of the box
	 * @param   stroke      (Number) box stroke line (box outline) weight
	 * @param   fillColor   (Number) box fill color
	 * @param   strokeColor (Number) box stroke (outline) color
	 * @return  MovieClip
	 */
	public static function drawRectangle(target:MovieClip, w:Number, h:Number, x:Number, y:Number, stroke:Number, fillColor:Number, strokeColor:Number):MovieClip
	{
		target.$boxHeight = h;
		target.$boxWidth = w;
		var boxId:String;
		// find target
		var boxTarget:MovieClip = target;
		var depth:Number = boxTarget.getNextHighestDepth();
		if(arguments[8] == undefined)
		{
			boxId = target._name + '_box' + depth.toString();
		}
		else
		{
			boxId = arguments[8];
		}
		// build
		target.$__box = boxTarget.createEmptyMovieClip(boxId, depth);
		// position
		target.$__box._y = y;
		target.$__box._x = x;
		// color 
		target.$__box.lineStyle(stroke, strokeColor, 100);
		// size/fill
		target.$__box.beginFill(fillColor, 100);
		target.$__box.lineTo(target.$boxWidth, 0);
		target.$__box.lineTo(target.$boxWidth, target.$boxHeight);
		target.$__box.lineTo(0, target.$boxHeight);
		target.$__box.lineTo(0, 0);
		target.$__box.endFill();
		
		return target.$__box;
	}
	/**
	 * 
	 * @usage   DrawingUtil.drawMask(myInstance, 100, 200);
	 * @param   target (MovieClip) MovieClip instance to mask
	 * @param   w (Number)     width of the mask
	 * @param   h (Number)     height of the mask
	 */
	public static function drawMask(target:MovieClip, w:Number, h:Number, x:Number, y:Number):MovieClip
	{
		if(isNaN(x))
		{
			x = 0;
		}
		if(isNaN(y))
		{
			y = 0;
		}
		var mask:MovieClip = DrawingUtil.drawRectangle(target, w, h, x, y, .25, 0x666666, 0x999999);
		// make mask
		target.setMask(mask);
		
		return mask;
	}
	/**
	 * Utility method to draw a rounded rectangle
	 * @usage   com.yahoo.util.DrawingUtil.drawRoundedRectangle(myTargetInstance, 100, 20, 3, 0x000000, 0, 6, 0x000000, 66, 'newRoundedRectangle', 3);
	 * @param   target (MovieClip) to attach the newly created instance to
	 * @param   w      (Number) width of the rounded rectangle
	 * @param   h      (Number) height of the rounded rectangle
	 * @param   r      (Number) radius of the corner 
	 * @param   c      (Number) fill color
	 * @param   a      (Number) fill alpha
	 * @param   o      (Number) stroke line width
	 * @param   oc     (Number) stroke line color
	 * @param   oa     (Number) stroke line alpha
	 * @param   n      (String) name of the instance to be created
	 * @param   z      (Number) depth of the instance to be createds
	 * @return  MovieClip
	 */
	public static function drawRoundedRectangle(target,w,h,r,c,a,o,oc,oa,n,z):MovieClip 
	{
		if (n==undefined)
		{
			n = "r"; //name
		}
		if (z==undefined)
		{
			z = target.getNextHighestDepth();
		}
		var returnCandidate:MovieClip = target.createEmptyMovieClip(n, z);
		returnCandidate.lineStyle(o,oc,oa);
		returnCandidate.beginFill(c,a);
		returnCandidate.moveTo(r, 0);
		returnCandidate.lineTo(w - r, 0);
		returnCandidate.curveTo(w, 0, w, r);
		returnCandidate.lineTo(w, h - r);
		returnCandidate.curveTo(w, h, w - r, h);
		returnCandidate.lineTo(r, h);
		returnCandidate.curveTo(0, h, 0, h - r);
		returnCandidate.lineTo(0, r);
		returnCandidate.curveTo(0, 0, r, 0);
		returnCandidate.endFill();
		
		return returnCandidate;
	}
	public static function drawCircle(target:MovieClip, x:Number, y:Number, r:Number, fill:Number, strokeColor:Number, strokeWidth:Number):MovieClip 
	{
		var mc:MovieClip = target.createEmptyMovieClip('circle'+target.getNextHighestDepth(), target.getNextHighestDepth());
		mc.lineStyle(strokeWidth, strokeColor, 100);
		mc.beginFill(fill);
		mc.moveTo(x+r, y);

		mc.curveTo(r+x, Math.tan(Math.PI/8)*r+y, Math.sin(Math.PI/4)*r+x, Math.sin(Math.PI/4)*r+y);
		mc.curveTo(Math.tan(Math.PI/8)*r+x, r+y, x, r+y);
		
		mc.curveTo(-Math.tan(Math.PI/8)*r+x, r+y, -Math.sin(Math.PI/4)*r+x, Math.sin(Math.PI/4)*r+y);
		mc.curveTo(-r+x, Math.tan(Math.PI/8)*r+y, -r+x, y);
		
		mc.curveTo(-r+x, -Math.tan(Math.PI/8)*r+y, -Math.sin(Math.PI/4)*r+x, -Math.sin(Math.PI/4)*r+y);
		mc.curveTo(-Math.tan(Math.PI/8)*r+x, -r+y, x, -r+y);
		
		mc.curveTo(Math.tan(Math.PI/8)*r+x, -r+y, Math.sin(Math.PI/4)*r+x, -Math.sin(Math.PI/4)*r+y);
		mc.curveTo(r+x, -Math.tan(Math.PI/8)*r+y, r+x, y);

		return mc;
	}
	public static function drawCircleSegment(target:MovieClip, x, y, a1, a2, r, dir):Array
	{
		dir = (dir == "CCW") ? -1 : 1;
		var diff = Math.abs(a2-a1);
		var divs = Math.floor(diff/(Math.PI/4))+1;
		var span = dir * diff/(2*divs);
		var rc = r/Math.cos(span);
		target.lineTo(x+Math.cos(a1)*r, y+Math.sin(a1)*r);
		var coords:Array = new Array;
		for (var i=0; i<divs; ++i) 
		{
			a2 = a1+span; a1 = a2+span;
			coords['controlX'] = x+Math.cos(a2)*rc;
			coords['controlY'] = y+Math.sin(a2)*rc;
			coords['anchorX'] = x+Math.cos(a1)*r;
			coords['anchorY'] = y+Math.sin(a1)*r;
			target.curveTo(
				coords['controlX'],
				coords['controlY'],
				coords['anchorX'],
				coords['anchorY']
				);
		};
		
		return coords;
	}
}