package myfuel.client;

public class StationInventory {
	private final int fuelN = 3;
	private Station s;
	private float [] fuelQty;
	private float [] minQty;
	
	/**
	 * 
	 * @param s
	 * @param fuelQty
	 * @param minQty
	 */
		public StationInventory(Station s, float [] fuelQty, float [] minQty)
		{
			this.setFuelQty(fuelQty);
			this.setMinQty(minQty);
			this.setS(s);
		}

		public Station getS() {
			return s;
		}

		public void setS(Station s) {
			this.s = s;
		}

		public float [] getFuelQty() {
			return fuelQty;
		}

		public void setFuelQty(float [] fuelQty) {
			this.fuelQty = fuelQty;
		}

		public float [] getMinQty() {
			return minQty;
		}

		public void setMinQty(float [] minQty) {
			this.minQty = minQty;
		}
	
}
