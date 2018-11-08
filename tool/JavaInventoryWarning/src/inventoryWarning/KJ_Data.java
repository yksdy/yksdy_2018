package inventoryWarning;

public class KJ_Data {
	String extSkuId = null;
	String pn = null;
	int  quantity = 0;
	
	public KJ_Data(String extSkuId ,String pn ,int  quantity ) {
		this.extSkuId = extSkuId;
		this.pn = pn;
		this.quantity = quantity;
	}

	public String getExtSkuId() {
		return extSkuId;
	}

	public void setExtSkuId(String extSkuId) {
		this.extSkuId = extSkuId;
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
