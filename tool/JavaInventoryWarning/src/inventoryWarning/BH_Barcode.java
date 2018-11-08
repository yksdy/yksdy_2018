package inventoryWarning;

public class BH_Barcode {
	int remainingQty;
	String barcode;
	public BH_Barcode(int remainingQty, String barcode) {
		this.remainingQty = remainingQty;
		this.barcode= barcode;
	}
	public int getRemainingQty() {
		return remainingQty;
	}
	public void setRemainingQty(int remainingQty) {
		this.remainingQty = remainingQty;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	
}
