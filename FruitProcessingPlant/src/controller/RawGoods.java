package controller;

import java.io.Serializable;

public class RawGoods implements Serializable {

	/**
	 * all Serializable classes should declare a serialVersionUID
	 */
	private static final long serialVersionUID = 5656051691569021492L;

	private int numApples;
	private int numPears;
	private int numBananas;

	public RawGoods(int apples, int pears, int bananas) {
		this.numApples = 0;
		this.numPears = 0;
		this.numBananas = 0;
		setNumApples(apples);
		setNumPears(pears);
		setNumBananas(bananas);
	}

	public int getNumApples() {
		return numApples;
	}

	public int getNumPears() {
		return numPears;
	}

	public int getNumBananas() {
		return numBananas;
	}

	public void setNumApples(int apples) {
		if (apples >= 0) {
			this.numApples = apples;
		} else {
			this.numApples = 0;
		}
	}

	public void setNumPears(int pears) {
		if (pears >= 0) {
			this.numPears = pears;
		} else {
			this.numPears = 0;
		}
	}

	public void setNumBananas(int bananas) {
		if (bananas >= 0) {
			this.numBananas = bananas;
		} else {
			this.numBananas = 0;
		}
	}
	
	@Override
	public String toString() {
		return new String("Apples: " + numApples + " Bananas: " + numBananas + " Pears: " + numPears);
	}
}
