package factory;

import network.ObjectSocketClient;
import draw.server.DrawCommandList;

public class AllSegments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create a new draw command list
		DrawCommandList dcl = new DrawCommandList();
		
		dcl.addCommand("clear"); //clear the draw server pane
		//Send clear command to the server
		ObjectSocketClient osc = new ObjectSocketClient("localhost", "55554");
		osc.setSendObject(dcl);
		Thread t = new Thread(osc);
		t.start();
		
		System.out.println("Testing all segments");
		Segment1 seg1 = new Segment1();
		seg1.paint();
		
		Segment2 seg2 = new Segment2();
		seg2.paint();
		
		Segment3 seg3 = new Segment3();
		seg3.paint();
	}

}
