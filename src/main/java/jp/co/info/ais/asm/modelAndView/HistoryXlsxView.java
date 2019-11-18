package jp.co.info.ais.asm.modelAndView;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import jp.co.info.ais.asm.domain.History;

public class HistoryXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")

        List<History> history = (List<History>) model.get("history");

        Sheet sheet = workbook.createSheet("貸与履歴");

        // create header

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("管理番号");
        row.createCell(1).setCellValue("Maker");
        row.createCell(2).setCellValue("Model");
        row.createCell(3).setCellValue("状態");
        row.createCell(4).setCellValue("貸与番号");
        row.createCell(5).setCellValue("用途");
        row.createCell(6).setCellValue("場所（Site名）");
        row.createCell(7).setCellValue("貸出者");
        row.createCell(8).setCellValue("使用者");
        row.createCell(9).setCellValue("BP社名");
        row.createCell(10).setCellValue("貸与日");
        row.createCell(11).setCellValue("返却日");

		// create body

        for (int i=0; i<history.size(); i++) {
            History aHistory = history.get(i);
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(aHistory.getAssetNumber());
            row.createCell(1).setCellValue(aHistory.getMakerName());
            row.createCell(2).setCellValue(aHistory.getModelName());
            row.createCell(3).setCellValue(aHistory.getStatusCode());
            row.createCell(4).setCellValue(aHistory.getRentalNo());
            row.createCell(5).setCellValue(aHistory.getPurpose());
            row.createCell(6).setCellValue(aHistory.getStorageLocation());
            row.createCell(7).setCellValue(aHistory.getApplicantId());
            row.createCell(8).setCellValue(aHistory.getRentalUserId());
            row.createCell(9).setCellValue(aHistory.getBpName());

			String rentalDay = aHistory.getRentalDayS();
			rentalDay = rentalDay.trim();
			if(rentalDay.length()>7) {
				row.createCell(10).setCellValue(rentalDay.substring(0, 4) + "-" + rentalDay.substring(4, 6) + "-" + rentalDay.substring(6, 8));
			} else {
				row.createCell(10).setCellValue(aHistory.getRentalDayS());
			}

			String returnDay = aHistory.getReturnDayS();
			returnDay = returnDay.trim();
			if(returnDay.length()>7) {
				row.createCell(11).setCellValue(returnDay.substring(0, 4) + "-" + returnDay.substring(4, 6) + "-" + returnDay.substring(6, 8));
			} else {
				row.createCell(11).setCellValue(aHistory.getReturnDayS());
			}

        }

        // enable auto filter
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 11));

        // adjust column width
        for (int i=0; i<12; i++) {
            sheet.autoSizeColumn(i);
        }

	}
}
