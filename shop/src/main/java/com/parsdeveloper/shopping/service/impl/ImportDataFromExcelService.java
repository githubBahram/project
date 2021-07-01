package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.commons.util.FileUtils;
import com.parsdeveloper.shopping.model.dto.ProductExcelDto;
import com.parsdeveloper.shopping.model.entity.shop.Brand;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.model.entity.shop.ProductImage;
import com.parsdeveloper.shopping.repository.BrandRepository;
import com.parsdeveloper.shopping.repository.CategoryRepository;
import com.parsdeveloper.shopping.repository.ProductImageRepository;
import com.parsdeveloper.shopping.repository.ProductRepository;
import com.parsdeveloper.shopping.service.api.AWSS3Service;
import com.parsdeveloper.shopping.service.api.ImportDataService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportDataFromExcelService implements ImportDataService {

    @Value("aws.s3.bucket.image.product")
    private String imageProductBucketName;
    @Value("aws.s3.bucket.image.product.thumbnail")
    private String imageProductThumbnailBucketName;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private AWSS3Service awss3Service;

    @Autowired
    private ImportDataService importDataService;

    @Override
    @Transactional
    public void importData() {
        Long count = 0L;
        FileInputStream excelFile = null;
        List<ProductExcelDto> productExcelDtoList = new ArrayList<>();

        try {
//            File file = ResourceUtils.getFile("classpath:nush.xlsx");
            File file = new File("/home/bahram/Documents/doc/product info/V01-Update-02/protein.xlsx");
            excelFile = new FileInputStream(file);

            Workbook wb = WorkbookFactory.create(excelFile);
            Sheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum()  <1900) {
                    continue;
                }
                if (count == 0L) {
                    productExcelDtoList = new ArrayList<>();
                }
                count++;
                try {
                    ProductExcelDto productExcelDto;
                    productExcelDto = new ProductExcelDto(row);
                    productExcelDtoList.add(productExcelDto);
                } catch (Throwable e) {
                    continue;
                }
                System.out.println("-----------row number------------");
                System.out.println(row.getRowNum());
                if (count == 100L ||   row.getRowNum()==1982) {
                    System.out.println("-----------start row number commit------------");
                    System.out.println(row.getRowNum());
                    count = importDataService.saveData(productExcelDtoList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long saveData(List<ProductExcelDto> productExcelDtoList) {

        for (ProductExcelDto productExcelDto : productExcelDtoList) {
            try {


                String brandFixName = productExcelDto.getBrandName().replaceAll("[\\s]+", "");
                Brand brand = brandRepository.findBrandByFixName(brandFixName);
                if (brand == null) {
                    brand = new Brand();
                    brand.setName(productExcelDto.getBrandName());
                    brand.setFixName(brandFixName);
                    brand = brandRepository.save(brand);
                }

                String categoryParentFixName = productExcelDto.getParentCategory().replaceAll("[\\s]+", "");
                Category categoryParent = categoryRepository.findCategoryByFixName(categoryParentFixName);
                if (categoryParent == null) {
                    categoryParent = new Category();
                    categoryParent.setName(productExcelDto.getParentCategory());
                    categoryParent.setFixName(categoryParentFixName);
                    categoryParent = categoryRepository.save(categoryParent);

                    categoryParent.setRoot(categoryParent);
                    categoryParent.setParent(categoryParent);

                    categoryParent = categoryRepository.save(categoryParent);
                }

                String categoryFixName = productExcelDto.getCategory().replaceAll("[\\s]+", "");
                Category category = categoryRepository.findCategoryByFixName(categoryFixName);
                if (category == null) {
                    category = new Category();
                    category.setName(productExcelDto.getCategory());
                    category.setFixName(categoryFixName);
                    category.setRoot(categoryParent);
                    category.setParent(categoryParent);
                    category = categoryRepository.save(category);
                }

                String productFixName = productExcelDto.getProductName().replaceAll("[\\s]+", "");
                Product product = new Product();
                product.setCategory(category);
                product.setBrand(brand);
                product.setBarcode(productExcelDto.getBarcode());
                product.setName(productExcelDto.getProductName());
                product.setFixName(productFixName);
                product = productRepository.save(product);

                if (!productExcelDto.getImage1().trim().equals("")) {
                    String nameImage = uploadImageFile(productExcelDto.getImage1());
                    uploadThumbnailImageFile(productExcelDto.getImage1(), nameImage);
                    if (nameImage != null) {
                        ProductImage productImage = new ProductImage();
                        productImage.setLocation(nameImage);
                        productImage.setProduct(product);
                        productImageRepository.save(productImage);
                    }
                }
                if (!productExcelDto.getImage2().trim().equals("")) {
                    String nameImage = uploadImageFile(productExcelDto.getImage2());
                    uploadThumbnailImageFile(productExcelDto.getImage2(), nameImage);
                    if (nameImage != null) {
                        ProductImage productImage = new ProductImage();
                        productImage.setLocation(nameImage);
                        productImage.setProduct(product);
                        productImageRepository.save(productImage);
                    }
                }
                if (!productExcelDto.getImage3().trim().equals("")) {
                    String nameImage = uploadImageFile(productExcelDto.getImage3());
                    uploadThumbnailImageFile(productExcelDto.getImage3(), nameImage);
                    if (nameImage != null) {
                        ProductImage productImage = new ProductImage();
                        productImage.setLocation(nameImage);
                        productImage.setProduct(product);
                        productImageRepository.save(productImage);
                    }
                }

            } catch (Throwable e) {
                continue;
            }
        }
        return 0L;
    }

    private String uploadImageFile(String fileName) {
        if (fileName.trim().equals("")) {
            return null;
        }
        String fileUploadName;
        try {
            File file = new File("/home/bahram/Documents/doc/product info/V01-Update-02/protein/Pic/" + fileName);
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "jpg/plain", IOUtils.toByteArray(input));
            fileUploadName = awss3Service.uploadFileByRandomName(multipartFile, "image-product");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileUploadName;
    }

    private String uploadThumbnailImageFile(String fileName, String uploadName) {
        if (fileName.trim().equals("")) {
            return null;
        }
        String fileUploadName;
        try {
            File file = new File("/home/bahram/Documents/doc/product info/V01-Update-02/protein/Pic/" + fileName);
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = FileUtils.createImageThumbnail(file, 300);
            fileUploadName = awss3Service.uploadFile(multipartFile, "image-product-thumbnail", uploadName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileUploadName;
    }
}
