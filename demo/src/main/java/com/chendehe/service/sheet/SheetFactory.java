package com.chendehe.service.sheet;

import com.chendehe.common.MyConstant;
import com.chendehe.entity.StudentEntity;
import com.chendehe.entity.UserEntity;
import com.chendehe.util.SpringContextUtils;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SheetFactory {

  private static final SheetService SH_USER_SERVICE =
      SpringContextUtils.getBean(SheetUserServiceImpl.class);

  private static final SheetService SH_STUDENT_SERVICE =
      SpringContextUtils.getBean(SheetStudentServiceImpl.class);

  /**
   * Excel类型转化成数据.
   *
   * @param sh sheet
   */
  public void parseSheet(Sheet sh) {
    String sheetName = sh.getSheetName();
    if (MyConstant.SHEET_USER.equals(sheetName.trim())) {
      SH_USER_SERVICE.doParseSheet(sh);
    } else if (MyConstant.SHEET_STUDENT.equals(sheetName.trim())) {
      SH_STUDENT_SERVICE.doParseSheet(sh);
    }
  }

  /**
   * 初始化SHEET.
   * @param wb Workbook
   */
  public void initSheet(Workbook wb) {

    // 第二步，创建userSheet
    Sheet userSheet = wb.getSheet(MyConstant.SHEET_USER);
    if (null == userSheet) {
      userSheet = wb.createSheet();
      wb.setSheetName(0, MyConstant.SHEET_USER);
      SH_USER_SERVICE.doInitSheet(userSheet);
    }

    // 第二步，创建stuSheet
    Sheet stuSheet = wb.getSheet(MyConstant.SHEET_STUDENT);
    if (null == stuSheet) {
      stuSheet = wb.createSheet();
      wb.setSheetName(1, MyConstant.SHEET_STUDENT);
      SH_STUDENT_SERVICE.doInitSheet(stuSheet);
    }
  }

  /**
   * 数据转化成Excel类型.
   *
   * @param list 数据类型
   * @param wb   wb
   * @return Workbook
   */
  public Workbook parseToSheet(List<?> list, Workbook wb) {
    if (CollectionUtils.isNotEmpty(list)) {
      if (list.get(0) instanceof UserEntity) {
        return SH_USER_SERVICE.parseToSheet(list, wb);
      } else if (list.get(0) instanceof StudentEntity) {
        return SH_STUDENT_SERVICE.parseToSheet(list, wb);
      }
    }
    return null;
  }
}
