package com.chendehe.service.sheet;

import com.chendehe.entity.StudentEntity;
import com.chendehe.entity.UserEntity;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ForkJoinExcel extends RecursiveTask<Workbook> {

  private List<UserEntity> users;
  private List<StudentEntity> students;
  private volatile List<?> list;

  private Workbook wb;

  private ForkJoinExcel(List<?> list, Workbook wb) {
    this.list = list;
    this.wb = wb;
  }

  /**
   * 初始化ForkJoinExcel.
   * @param users users
   * @param students students
   */
  public ForkJoinExcel(List<UserEntity> users, List<StudentEntity> students) {
    this.users = users;
    this.students = students;
    // 第一步，创建一个 Workbook，对应一个Excel文件
    this.wb = new XSSFWorkbook();
    new SheetFactory().initSheet(wb);
  }

  @Override
  protected Workbook compute() {

    if (CollectionUtils.isNotEmpty(users) && CollectionUtils.isNotEmpty(students)) {

      //分裂
      ForkJoinExcel userExcel = new ForkJoinExcel(users, wb);
      ForkJoinExcel stuExcel = new ForkJoinExcel(students, wb);
      //执行
      userExcel.fork();
      stuExcel.fork();
      //待结束
      userExcel.join();
      stuExcel.join();

      return wb;
    } else if (CollectionUtils.isNotEmpty(users)) {
      return new SheetFactory().parseToSheet(users, wb);
    } else if (CollectionUtils.isNotEmpty(students)) {
      return new SheetFactory().parseToSheet(students, wb);
    } else {
      return new SheetFactory().parseToSheet(list, wb);
    }
  }

}
