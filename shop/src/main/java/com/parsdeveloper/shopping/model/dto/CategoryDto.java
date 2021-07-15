package com.parsdeveloper.shopping.model.dto;

import com.parsdeveloper.shopping.model.entity.shop.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

    private Long id;
    private String name;
    private Long imageId;
    private Long rootId;
    private Long parentId;
    private String description;
    private MultipartFile image;
    private String imageLocation;
    private List<CategoryDto> categoryDtoList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getRootId() {
        return rootId;
    }

    public void setRootId(Long rootId) {
        this.rootId = rootId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDto map(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        if (category.getImage() != null) {
            categoryDto.setImageId(category.getImage().getId());
            categoryDto.setImageLocation(category.getImage().getLocation());
        }
        categoryDto.setName(category.getName());
        categoryDto.setId(category.getId());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    public void setCategoryDtoList(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }
}
