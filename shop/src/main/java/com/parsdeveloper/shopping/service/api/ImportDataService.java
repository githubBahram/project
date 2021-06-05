package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.ProductExcelDto;

import java.util.List;

public interface ImportDataService {

    void importData();

    Long saveData(List<ProductExcelDto> productExcelDtoList);


}
