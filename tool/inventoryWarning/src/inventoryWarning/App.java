package inventoryWarning;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class App {
	Workbook workbook = null;
	Map<String, KJ_Data> kj_data = new HashMap<String, KJ_Data>();;
	Map<String, BH_Data> bh_data = new HashMap<String, BH_Data>();;
	List<String> error_data = new ArrayList<String>();
	List<String> all_extSKuId = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		App app = new App();
		String[] fileNames = { "20181105_inventory.xls" };
		for (String fileName : fileNames) {
			app.readData(fileName);
			app.handelData();
			app.writeData(fileName);
		}
	}

	public void readData(String fileName) throws Exception {
		File xlsFile = new File("resource\\" + fileName);
		workbook = Workbook.getWorkbook(xlsFile);
		Sheet[] sheets = workbook.getSheets();
		error_data = new ArrayList<String>();
		if (sheets != null) {
			readKJ_Data(sheets[0]);
			readBH_Data(sheets[1]);
		}
		workbook.close();

	}

	void readKJ_Data(Sheet sheet) {
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		for (int row = 1; row < rows; row++) {
			String extSkuId = sheet.getCell(0, row).getContents();
			int quantity = Integer.parseInt(sheet.getCell(1, row).getContents().split("\\.")[0]);
			String pn = sheet.getCell(2, row).getContents();
			KJ_Data kjBean = new KJ_Data(extSkuId, pn, quantity);
			kj_data.put(extSkuId, kjBean);
			if (!all_extSKuId.contains(extSkuId)) {
				all_extSKuId.add(extSkuId);
			}
			for (int col = 0; col < cols; col++) {
				System.out.printf(row + "   %10s", sheet.getCell(col, row).getContents());
			}
			System.out.println();
		}
	}

	void readBH_Data(Sheet sheet) {
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		for (int row = 1; row < rows; row++) {
			String hardwareId = sheet.getCell(0, row).getContents();
			String binId = sheet.getCell(1, row).getContents();
			String skuId = sheet.getCell(3, row).getContents();
			String createTime = sheet.getCell(4, row).getContents();
			String modifyTime = sheet.getCell(5, row).getContents();
			String extSkuId = sheet.getCell(6, row).getContents();
			String description = sheet.getCell(7, row).getContents();
			if (sheet.getCell(2, row).getContents().equals("")) {
				break;
			}
			int remainingQty = Integer.parseInt(sheet.getCell(2, row).getContents());
			String barcodeData = sheet.getCell(8, row).getContents();

			BH_DAO bhBean = new BH_DAO(hardwareId, binId, skuId, createTime, modifyTime, extSkuId, description);
			List<BH_Barcode> bhBarcodeBeanList = bhBean.getBarcodeData();
			BH_Barcode bhBarcode = new BH_Barcode(remainingQty, barcodeData);
			bhBarcodeBeanList.add(bhBarcode);

			if (bh_data.containsKey(extSkuId)) {
				BH_Data bhData = bh_data.get(extSkuId);
				List<BH_DAO> bhDaoListtemp = bhData.getBhDaoList();
				if (bhDaoListtemp.contains(bhBean)) {
					int index = bhDaoListtemp.indexOf(bhBean);
					List<BH_Barcode> temp = bhDaoListtemp.get(index).getBarcodeData();
					if (temp.size() == 1) {
						if (temp.get(0).getBarcode().equals(bhBarcode.getBarcode())) {
							temp.get(0).setRemainingQty(remainingQty + temp.get(0).getRemainingQty());
						} else {
							temp.add(bhBarcode);
						}
					} else if (temp.size() == 2) {
						for (int i = 0; i < 2; i++) {
							if (temp.get(i).getBarcode().equals(bhBarcode.getBarcode())) {
								temp.get(i).setRemainingQty(remainingQty + temp.get(i).getRemainingQty());
							}
						}
					} else {
						bhDaoListtemp.add(bhBean);
					}
					bhData.setQuantity(bhData.getQuantity() + remainingQty);
				}
			} else {
				BH_Data bhData = new BH_Data(extSkuId);
				bhData.getBhDaoList().add(bhBean);
				bhData.setQuantity(remainingQty);
				bh_data.put(extSkuId, bhData);
				if (!all_extSKuId.contains(extSkuId)) {
					all_extSKuId.add(extSkuId);
				}
			}

			System.out.print(row);
			for (int col = 0; col < cols; col++) {
				System.out.printf("        " + sheet.getCell(col, row).getContents());
			}
			System.out.println();
		}
	}

	public void handelData() {
		for (int i = 0; i < all_extSKuId.size(); i++) {
			String key = all_extSKuId.get(i);
			KJ_Data kjD = kj_data.get(key);
			BH_Data bhD = bh_data.get(key);
			if (kjD == null || bhD == null) {
				continue;
			}
			if (kjD.getPn().contains(",")) {
				if (kjD.getQuantity() != (bhD.getQuantity() / 2)) {
					error_data.add(key);
				}
			} else {
				if (kjD.getQuantity() != bhD.getQuantity()) {
					error_data.add(key);
				}
			}

		}
	}

	public void writeData(String fileName) throws Exception {
		File xlsWriteFile = new File("resource\\Error_" + fileName);
		WritableWorkbook writeDatabook = Workbook.createWorkbook(xlsWriteFile);
		WritableSheet sheet = writeDatabook.createSheet("error", 0);
		int row = 0;
		
		sheet.addCell(new Label(0, row, "KJ-ExtSkuId"));
		sheet.addCell(new Label(1, row, "KJ-PN"));
		sheet.addCell(new Label(2, row, "KJ-Quantity"));

		sheet.addCell(new Label(6, row, "BH-HardwareId"));
		sheet.addCell(new Label(7, row, "BH-BinId"));
		sheet.addCell(new Label(12, row, "BH-SkuId"));
		sheet.addCell(new Label(13, row, "BH-CreateTime"));
		sheet.addCell(new Label(14, row, "BH-ModifyTime"));
		sheet.addCell(new Label(10, row, "BH-ExtSkuId"));
		sheet.addCell(new Label(11, row, "BH-Description"));
		sheet.addCell(new Label(8, row, "BH-Barcode"));
		sheet.addCell(new Label(9, row, "BH-RemainingQty"));
		sheet.addCell(new Label(4, row, "BH-RemainingQty"));
		
		row++;
		for (String key : error_data) {
			KJ_Data kjD = kj_data.get(key);
			if (kjD == null) {
				sheet.addCell(new Label(0, row, "null"));
				sheet.addCell(new Label(1, row, "null"));
				sheet.addCell(new Label(2, row, "null"));
			} else {

				sheet.addCell(new Label(0, row, kjD.getExtSkuId()));
				sheet.addCell(new Label(1, row, kjD.getPn()));
				sheet.addCell(new Label(2, row, String.valueOf(kjD.getQuantity())));
			}

			BH_Data bhD = bh_data.get(key);
			if (bhD == null) {
				sheet.addCell(new Label(4, row, "null"));
				sheet.addCell(new Label(5, row, "null"));
				sheet.addCell(new Label(6, row, "null"));
				sheet.addCell(new Label(7, row, "null"));
				sheet.addCell(new Label(12, row, "null"));
				sheet.addCell(new Label(13, row, "null"));
				sheet.addCell(new Label(14, row, "null"));
				sheet.addCell(new Label(8, row, "null"));
				sheet.addCell(new Label(9, row, "null"));
				sheet.addCell(new Label(10, row, "null"));
				sheet.addCell(new Label(11, row, "null"));
				row++;
			} else {
				// sheet.addCell(new Label(4, row, bhD.getExtSkuId()));
				// sheet.addCell(new Label(5, row, String.valueOf(bhD.getQuantity())));
				for (BH_DAO bo : bhD.getBhDaoList()) {
					sheet.addCell(new Label(6, row, bo.getHardwareId()));
					sheet.addCell(new Label(7, row, bo.getBinId()));
					sheet.addCell(new Label(12, row, bo.getSkuId()));
					sheet.addCell(new Label(13, row, bo.getCreateTime()));
					sheet.addCell(new Label(14, row, bo.getModifyTime()));
					sheet.addCell(new Label(10, row, bo.getExtSkuId()));
					sheet.addCell(new Label(11, row, bo.getDescription()));
					for (BH_Barcode bhb : bo.getBarcodeData()) {
						sheet.addCell(new Label(8, row, bhb.getBarcode()));
						sheet.addCell(new Label(9, row, String.valueOf(bhb.getRemainingQty())));
						sheet.addCell(new Label(4, row, String.valueOf(bhb.getRemainingQty())));
						row++;
					}
				}
			}

		}
		writeDatabook.write();
		writeDatabook.close();

	}

}
