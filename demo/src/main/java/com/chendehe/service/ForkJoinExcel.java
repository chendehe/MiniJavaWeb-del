package com.chendehe.service;

import com.chendehe.common.ErrorCode;
import com.chendehe.common.MyConstant;
import com.chendehe.entity.StudentEntity;
import com.chendehe.entity.UserEntity;
import com.chendehe.exception.ValidationException;
import com.chendehe.util.TimeFormat;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ForkJoinExcel extends RecursiveTask<Workbook> {

  private List<UserEntity> users;
  private List<StudentEntity> students;
  private Workbook wb;

  private ForkJoinExcel(List<UserEntity> users, List<StudentEntity> students, Workbook wb) {
    this.users = users;
    this.students = students;
    this.wb = wb;
  }

  ForkJoinExcel(List<UserEntity> users, List<StudentEntity> students) {
    this.users = users;
    this.students = students;

    // 第一步，创建一个 Workbook，对应一个Excel文件
    this.wb = new XSSFWorkbook();
    // 第二步，创建sheet
    Sheet sheetUser = wb.getSheet(MyConstant.SHEET_USER);
    if (null == sheetUser) {
      sheetUser = wb.createSheet();
      wb.setSheetName(0, MyConstant.SHEET_USER);
      initUserWorkbook(sheetUser);
    }
    Sheet sheetStu = wb.getSheet(MyConstant.SHEET_STUDENT);
    if (null == sheetStu) {
      sheetStu = wb.createSheet();
      wb.setSheetName(1, MyConstant.SHEET_STUDENT);
      initStudentWorkbook(sheetStu);
    }
  }

  @Override
  protected Workbook compute() {

    if (CollectionUtils.isNotEmpty(users) && CollectionUtils.isNotEmpty(students)) {

      //分裂
      ForkJoinExcel excel1 = new ForkJoinExcel(users, null, wb);
      ForkJoinExcel excel2 = new ForkJoinExcel(null, students, wb);
      //执行
      excel1.fork();
      excel2.fork();
      //待结束
      excel1.join();
      excel2.join();

      return wb;
    } else if (CollectionUtils.isNotEmpty(users)) {
      return getUserWorkbook(users, wb.getSheet(MyConstant.SHEET_USER));
    } else if (CollectionUtils.isNotEmpty(students)) {
      return getStudentWorkbook(students, wb.getSheet(MyConstant.SHEET_STUDENT));
    }
    throw new ValidationException(ErrorCode.EXCEL_DOWNLOAD_FAILED);
  }

  private Workbook getStudentWorkbook(List<StudentEntity> students, Sheet sheet) {
    //Sheet sheet = wb.getSheet(MyConstant.SHEET_USER);
    // 第五步，写入实体数据
    System.out.println("2sheet" + sheet);
    for (int i = 0; i < students.size(); i++) {
      Row row = sheet.createRow(i + 1);
      // 第四步，创建单元格，并设置值
      row.createCell(0).setCellValue(students.get(i).getSchool());
      row.createCell(1).setCellValue(students.get(i).getAcademy());
      row.createCell(2).setCellValue(students.get(i).getMajor());
      row.createCell(3).setCellValue(students.get(i).getClasses());
    }
    return sheet.getWorkbook();
  }

  private Workbook getUserWorkbook(List<UserEntity> users, Sheet sheet) {
    //Sheet sheet = wb.getSheet(MyConstant.SHEET_USER);
    // 第五步，写入实体数据
    System.out.println("1sheet" + sheet);
    for (int i = 0; i < users.size(); i++) {
      Row row = sheet.createRow(i + 1);
      // 第四步，创建单元格，并设置值
      row.createCell(0).setCellValue(users.get(i).getName());
      row.createCell(1).setCellValue(users.get(i).getGender());
      row.createCell(2).setCellValue(
          TimeFormat.getDate(users.get(i).getBirthday(), TimeFormat.DEFAULT_TYPE));
      row.createCell(3).setCellValue(users.get(i).getAddress());
    }
    return sheet.getWorkbook();
  }

  private void initStudentWorkbook(Sheet sheet) {
    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
    Row row = sheet.createRow(0);
    // 第四步，创建单元格，并设置值表头 设置表头居中
    CellStyle style = sheet.getWorkbook().createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

    Cell cell = row.createCell(0);
    cell.setCellValue("school");
    cell = row.createCell(1);
    cell.setCellValue("academy");
    cell = row.createCell(2);
    cell.setCellValue("major");
    cell = row.createCell(3);
    cell.setCellValue("class");
  }

  private void initUserWorkbook(Sheet sheet) {
    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
    Row row = sheet.createRow(0);
    // 第四步，创建单元格，并设置值表头 设置表头居中
    CellStyle style = sheet.getWorkbook().createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

    Cell cell = row.createCell(0);
    cell.setCellValue("name");
    cell = row.createCell(1);
    cell.setCellValue("gender");
    cell = row.createCell(2);
    cell.setCellValue("birthday");
    cell = row.createCell(3);
    cell.setCellValue("address");
  }
}
