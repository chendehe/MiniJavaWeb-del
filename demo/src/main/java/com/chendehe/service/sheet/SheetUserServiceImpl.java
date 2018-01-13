package com.chendehe.service.sheet;

import com.chendehe.common.MyConstant;
import com.chendehe.dao.UserDao;
import com.chendehe.entity.UserEntity;
import com.chendehe.util.IdGenerator;
import com.chendehe.util.TimeFormat;
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
public class SheetUserServiceImpl implements SheetService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SheetUserServiceImpl.class);

  @Autowired
  private UserDao userDao;

  @Override
  public void doParseSheet(Sheet sh) {
    List<UserEntity> users = Lists.newArrayList();
    UserEntity user;

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

      user = new UserEntity();
      user.setId(IdGenerator.get());
      user.setName(row.getCell(0).getStringCellValue());
      user.setGender((int) row.getCell(1).getNumericCellValue());
      user.setBirthday(row.getCell(2).getDateCellValue());
      user.setAddress(row.getCell(3).getStringCellValue());
      user.setCreateTime(new Date());
      users.add(user);
    }
    LOGGER.info("[UserServiceImpl] save user in db... :{}", users.size());
    userDao.saveBatch(users);
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
    cell.setCellValue("name");
    cell = row.createCell(1);
    cell.setCellValue("gender");
    cell = row.createCell(2);
    cell.setCellValue("birthday");
    cell = row.createCell(3);
    cell.setCellValue("address");
  }

  @Override
  public Workbook parseToSheet(List<?> list, Workbook wb) {

    Sheet sh = wb.getSheet(MyConstant.SHEET_USER);
    List<UserEntity> users = (List<UserEntity>) list;
    // 第五步，写入实体数据
    for (int i = 0; i < users.size(); i++) {
      Row row = sh.createRow(i + 1);
      // 第四步，创建单元格，并设置值
      row.createCell(0).setCellValue(users.get(i).getName());
      row.createCell(1).setCellValue(users.get(i).getGender());
      row.createCell(2).setCellValue(
          TimeFormat.getDate(users.get(i).getBirthday(), TimeFormat.DEFAULT_TYPE));
      row.createCell(3).setCellValue(users.get(i).getAddress());
    }
    return sh.getWorkbook();
  }
}
