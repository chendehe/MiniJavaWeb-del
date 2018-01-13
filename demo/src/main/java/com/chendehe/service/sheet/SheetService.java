package com.chendehe.service.sheet;

import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface SheetService {

  void doParseSheet(Sheet sh);

  void doInitSheet(Sheet sh);

  Workbook parseToSheet(List<?> list, Workbook wb);

}
