package myfuel.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private int FuelId;
	private float QtyFuel;
	public Order(int FuelId,float QtyFuel)
	{
		setFuelId(FuelId);
		setQtyFuel(QtyFuel);
	}
	public int getFuelId() {
		return FuelId;
	}
	public void setFuelId(int fuelId) {
		FuelId = fuelId;
	}
	public float getQtyFuel() {
		return QtyFuel;
	}
	public void setQtyFuel(float qtyFuel) {
		QtyFuel = qtyFuel;
	}
}
