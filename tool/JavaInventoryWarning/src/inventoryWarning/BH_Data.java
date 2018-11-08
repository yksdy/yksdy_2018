package inventoryWarning;

import java.util.ArrayList;
import java.util.List;

public class BH_Data {
	String extSkuId;
	int quantity;
	List<BH_DAO> bhDaoList ;
	public BH_Data(String extSkuId ) {
		this.extSkuId = extSkuId;
		this.bhDaoList = new ArrayList<BH_DAO>();
	}
	public String getExtSkuId() {
		return extSkuId;
	}
	public void setExtSkuId(String extSkuId) {
		this.extSkuId = extSkuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public List<BH_DAO> getBhDaoList() {
		return bhDaoList;
	}
	public void setBhDaoList(List<BH_DAO> bhDaoList) {
		this.bhDaoList = bhDaoList;
	}
	

}
