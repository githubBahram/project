package com.parsdeveloper.shopping.model.dto;

import com.parsdeveloper.shopping.model.entity.shop.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private Long id;
    private String name;
    private byte[] imageFile;
    private String imageName;
    private Long imageId;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(String name, byte[] imageFile,String imageName) {
        this.name = name;
        this.imageFile = imageFile;
        this.imageName=imageName;
    }

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

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public CategoryDTO map(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setImageId(category.getImage().getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }
}
