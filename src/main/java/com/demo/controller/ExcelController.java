package com.demo.controller;


import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.SysCheckStandard;
import com.demo.entity.SysCheckStdSchedule;
import com.demo.service.ExcelService;
import com.demo.until.Result;
import com.demo.until.ResultUtil;


@RestController
@RequestMapping("excel")
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	@RequestMapping("/insertSysCheckStandard")
	public Result insertSysCheckStandard(@RequestParam("file") MultipartFile file) {
//		readExcelToObj("C:\\Users\\陈回\\Desktop\\批量导入excel\\标准2\\镀锌钢绞线 5.4-1570Mpa A级.xlsx");
//		readExcelToObj("C:\\Users\\陈回\\Desktop\\批量导入excel\\标准2\\电力电缆ZA-RVV 3-6.xlsx");
//		String originalFilename=file.getOriginalFilename();
//         readExcelToObj(originalFilename);
	//	readExcelToObj("d:\\poi.xlsx");

//		return ResultUtil.success();
//	}
//	private void readExcelToObj(String path) {
		Workbook wb = null;
		try {
//			File file = new File(path);
			wb = WorkbookFactory.create(file.getInputStream());
					//WorkbookFactory.create((File)file);
			// 处理sheet的前5行
			readHeadExcel(wb, 0, 0, 0);
			// 处理一个sheet页面的第8行到最后一行
			readExcel(wb, 0, 7, 0);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultUtil.success();
	}

	public void readHeadExcel(Workbook wb, int sheetIndex, int startReadLine, int tailLine) {
		// 获取工作表
		Sheet sheet = wb.getSheetAt(sheetIndex);
		// 创建二维数组，用来存储excel里面的信息
		String[][] excels = new String[5][3];
		// 将合并的单元格填充到数组
		mergeCells2(excels, sheet, startReadLine);

		// 创建行对象
		Row row = null;
		for (int i = startReadLine; i < 5; i++) {
			// 获取某一行的信息
			row = sheet.getRow(i);
			// 遍历某一行的信息
			for (Cell c : row) {
				int dex = c.getColumnIndex();
				System.out.println(dex);
				if (dex <= 4) {
					// 判断某一个单元格是不是合并单元格
					boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());

					if (!isMerge) {
						// c.getRichStringCellValue();
						String value = getCellValue(c);
						excels[i][dex] = value;
						// c.getRichStringCellValue().toString();
					}
				}

			}
		}
		
		// 获取工作表
		Sheet sheet2 = wb.getSheetAt(sheetIndex);
		// 获取最下面一行位置（如果有4行，则为3）
		int lineNum = sheet2.getLastRowNum();
		int maxColoum = sheet.getRow(0).getPhysicalNumberOfCells();
		for (int i = 1; i < lineNum + 1; i++) {
			if (sheet.getRow(i).getPhysicalNumberOfCells() > maxColoum) {
				maxColoum = sheet.getRow(i).getPhysicalNumberOfCells();
			}
		}

		if(maxColoum==9)
		{
			SysCheckStandard sysCheckStandard = new SysCheckStandard();
	    	int i=1;
			sysCheckStandard.setSample_class(excels[0][i]);
			sysCheckStandard.setSample_type(excels[1][i]);
			sysCheckStandard.setCheck_norm(excels[2][i]);
			sysCheckStandard.setCheck_period(excels[3][i]);
 //           excelService.insertChectStandard(sysCheckStandard);
		     System.out.println("插入成功");
		}
		
		if(maxColoum==10)
		{
			SysCheckStandard sysCheckStandard = new SysCheckStandard();
	    	int i=2;
			sysCheckStandard.setSample_class(excels[0][i]);
			sysCheckStandard.setSample_type(excels[1][i]);
			sysCheckStandard.setCheck_norm(excels[2][i]);
			sysCheckStandard.setCheck_period(excels[3][i]);
 //           excelService.insertChectStandard(sysCheckStandard);
		     System.out.println("插入成功");
		}
		
	    

	}




	// 处理一个sheet页面的第8行到最后一行
	private void readExcel(Workbook wb, int sheetIndex, int startReadLine, int tailLine) {

		// 获取工作表
		Sheet sheet = wb.getSheetAt(sheetIndex);
		// 获取最下面一行位置（如果有4行，则为3）
		int lineNum = sheet.getLastRowNum();
		// 获得最大的列数（如果最大列有6列则为6）
		int maxColoum = sheet.getRow(0).getPhysicalNumberOfCells();
		for (int i = 1; i < lineNum + 1; i++) {
			if (sheet.getRow(i).getPhysicalNumberOfCells() > maxColoum) {
				maxColoum = sheet.getRow(i).getPhysicalNumberOfCells();
			}
		}
		// 创建二维数组，用来存储excel里面的信息
		String[][] excels = new String[lineNum + 1 - startReadLine][maxColoum];
		// 将合并的单元格填充到数组
		mergeCell1(excels, sheet, startReadLine);
		// 创建行对象
		Row row = null;
		// sheet.getLastRowNum()获取最后一行的行的下标，从0开始计算
		for (int i = startReadLine; i < sheet.getLastRowNum() - tailLine + 1; i++) {
			// 获取某一行的信息
			row = sheet.getRow(i);
			// 遍历某一行的信息
			for (Cell c : row) {
				int dex = c.getColumnIndex();
				System.out.println(dex);
				// 判断某一个单元格是不是合并单元格
				boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());

				if (!isMerge) {
					// c.getRichStringCellValue();
					String value = getCellValue(c);
					excels[i - startReadLine][dex] = value;
					// c.getRichStringCellValue().toString();
				}
			}
		}
         
		
		if(maxColoum==9)
		{
			for(int i=0;i<sheet.getLastRowNum()-startReadLine;i++)
			{
				SysCheckStdSchedule sysCheckStdSchedule=new SysCheckStdSchedule();
				sysCheckStdSchedule.setOrder_number((int)Float.parseFloat(excels[i][0]));
				sysCheckStdSchedule.setCheck_proj_type("");
				if(excels[i][1].equals(excels[i][2]))
				{
					sysCheckStdSchedule.setCheck_sup_class(excels[i][1]);
					sysCheckStdSchedule.setCheck_class("");
				}
				else
				{
					sysCheckStdSchedule.setCheck_sup_class(excels[i][1]);
					sysCheckStdSchedule.setCheck_class(excels[i][2]);
				}
				
				sysCheckStdSchedule.setCheck_unit(excels[i][3]);
				sysCheckStdSchedule.setStandard_request(excels[i][4]);
				sysCheckStdSchedule.setStandard_day(excels[i][5]);
				sysCheckStdSchedule.setDev_name(excels[i][6]);
				sysCheckStdSchedule.setDev_type(excels[i][7]);
				sysCheckStdSchedule.setDev_code(excels[i][8]);
				excelService.insertChectStdSchedule(sysCheckStdSchedule);
			}
		}
		
		
		if(maxColoum==10)
		{
			for(int i=0;i<sheet.getLastRowNum()-startReadLine;i++)
			{
				SysCheckStdSchedule sysCheckStdSchedule=new SysCheckStdSchedule();
				sysCheckStdSchedule.setOrder_number((int)Float.parseFloat(excels[i][0]));
				sysCheckStdSchedule.setCheck_proj_type(excels[i][1]);
				if(excels[i][2].equals(excels[i][3]))
				{
					sysCheckStdSchedule.setCheck_sup_class(excels[i][2]);
					sysCheckStdSchedule.setCheck_class("");
				}
				else
				{
					sysCheckStdSchedule.setCheck_sup_class(excels[i][2]);
					sysCheckStdSchedule.setCheck_class(excels[i][3]);
				}
				
				sysCheckStdSchedule.setCheck_unit(excels[i][4]);
				sysCheckStdSchedule.setStandard_request(excels[i][5]);
				sysCheckStdSchedule.setStandard_day(excels[i][6]);
				sysCheckStdSchedule.setDev_name(excels[i][7]);
				sysCheckStdSchedule.setDev_type(excels[i][8]);
				sysCheckStdSchedule.setDev_code(excels[i][9]);
				excelService.insertChectStdSchedule(sysCheckStdSchedule);
			}
		}
		
		
		
		
		
		System.out.println(excels);

	}

	public void mergeCells2(String[][] excels, Sheet sheet, int startReadLine) {

		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (lastRow <= 4) {
				Row fRow = sheet.getRow(firstRow);
				Cell fCell = fRow.getCell(firstColumn);
				String value = getCellValue(fCell);
				for (int j = firstRow; j < firstRow + 1; j++) {
					for (int m = firstColumn; m < firstColumn + 1; m++) {
						excels[j][m] = value;
					}
				}
			}

			System.out.println(excels);

		}

	}

	public void mergeCell1(String[][] excels, Sheet sheet, int startReadLine) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (firstRow >= startReadLine) {
				Row fRow = sheet.getRow(firstRow);
				Cell fCell = fRow.getCell(firstColumn);
				String value = getCellValue(fCell);

				for (int j = firstRow; j < lastRow + 1; j++) {
					for (int m = firstColumn; m < lastColumn + 1; m++) {
						excels[j - startReadLine][m] = value;
					}
				}
			}

		}
	}

	private boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	public String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return "";
	}

}
