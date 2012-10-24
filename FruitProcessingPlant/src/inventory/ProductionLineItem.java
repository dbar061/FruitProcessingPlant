package inventory;

import draw.Drawable;

/**
 * ProductionLineItem.java
 * @author:			Devin Barry
 * @date:			12.10.2012
 * @lastModified:	13.10.2012
 * 
 * Interface shared by all objects that can be processed on the fruit production line
 */
public interface ProductionLineItem extends Drawable {
	
	//Which buffer type object is this ProductionLineItem object in
	//TODO future work
	public void getBuffer();

}
