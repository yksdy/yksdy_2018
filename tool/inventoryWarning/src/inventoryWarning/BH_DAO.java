package inventoryWarning;

import java.util.ArrayList;
import java.util.List;

public class BH_DAO {

	String hardwareId ;
	String binId ;
	String skuId;
	String createTime;
	String modifyTime;
	String extSkuId;
	String description;
	List<BH_Barcode> barcodeData = new ArrayList<BH_Barcode>();
	
	public BH_DAO(String hardwareId ,String binId ,String skuId, String createTime,	String modifyTime,
	String extSkuId,String description) {
		this.hardwareId = hardwareId ;
		this.binId = binId ;
		this.skuId = skuId;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.extSkuId = extSkuId;
		this.description = description;
	}
	public boolean equals(Object obj){
		if(obj != null){
			if(obj == this){
				return true;
			}
			if(obj instanceof BH_DAO){
				BH_DAO bhDao = (BH_DAO)obj;
				if( bhDao.skuId == bhDao.skuId && bhDao.extSkuId == bhDao.extSkuId ){
					return true;
				}
			}
		}
		return false;
	}
	public List<BH_Barcode> getBarcodeData() {
		return barcodeData;
	}
	public void setBarcodeData(List<BH_Barcode> barcodeData) {
		this.barcodeData = barcodeData;
	}
	public String getHardwareId() {
		return hardwareId;
	}
	public void setHardwareId(String hardwareId) {
		this.hardwareId = hardwareId;
	}
	public String getBinId() {
		return binId;
	}
	public void setBinId(String binId) {
		this.binId = binId;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getExtSkuId() {
		return extSkuId;
	}
	public void setExtSkuId(String extSkuId) {
		this.extSkuId = extSkuId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
