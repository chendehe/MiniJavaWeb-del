package com.chendehe.service.sheet;

import com.chendehe.common.MyConstant;
import com.chendehe.dao.StudentDao;
import com.chendehe.entity.StudentEntity;
import com.chendehe.util.IdGenerator;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SheetStudentServiceImpl implements SheetService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SheetStudentServiceImpl.class);

  @Autowired
  private StudentDao studentDao;

  @Override
  public void doParseSheet(Sheet sh) {
    List<StudentEntity> students = Lists.newArrayList();
    StudentEntity student;

    boolean firstIn = true;
    for (Row row : sh) {
      if (firstIn) {
        //去掉标题
        firstIn = false;
        continue;
      }
      if (null == row.getCell(0)) {
        break;
      }

      student = new StudentEntity();
      student.setId(IdGenerator.get());
      student.setSchool(row.getCell(0).getStringCellValue());
      student.setAcademy(row.getCell(1).getStringCellValue());
      student.setMajor(row.getCell(2).getStringCellValue());
      student.setClasses(row.getCell(3).getStringCellValue());
      student.setCreateTime(new Date());
      students.add(student);
    }
    LOGGER.info("[UserServiceImpl] save student in db... :{}", students.size());
    studentDao.saveBatch(students);
  }

  @Override
  public void doInitSheet(Sheet sh) {

    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
    Row row = sh.createRow(0);
    // 第四步，创建单元格，并设置值表头 设置表头居中
    CellStyle style = sh.getWorkbook().createCellStyle();
    // 创建一个居中格式
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

    Cell cell = row.createCell(0);
    cell.setCellValue("school");
    cell = row.createCell(1);
    cell.setCellValue("academy");
    cell = row.createCell(2);
    cell.setCellValue("major");
    cell = row.createCell(3);
    cell.setCellValue("class");
  }

  @Override
  public Workbook parseToSheet(List<?> list, Workbook wb) {

    Sheet sh = wb.getSheet(MyConstant.SHEET_STUDENT);
    List<StudentEntity> students = (List<StudentEntity>) list;
    // 第五步，写入实体数据
    for (int i = 0; i < students.size(); i++) {
      Row row = sh.createRow(i + 1);
      // 第四步，创建单元格，并设置值
      row.createCell(0).setCellValue(students.get(i).getSchool());
      row.createCell(1).setCellValue(students.get(i).getAcademy());
      row.createCell(2).setCellValue(students.get(i).getMajor());
      row.createCell(3).setCellValue(students.get(i).getClasses());
    }
    return sh.getWorkbook();
  }
}
