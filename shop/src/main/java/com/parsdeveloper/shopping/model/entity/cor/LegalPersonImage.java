package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "LEGAL_PERSON_IMAGE")
public class LegalPersonImage extends EffectiveModel<Long> {

    public final static String DEFAULT_FORMAT = "image/png";
    public static final Long UNKNOWN = 6L;

    private String format;
    private byte[] image;
    private String imageSize;
    private String economyCode;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "FORMAT", length = 20)
    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Lob
    @Column(name = "IMAGE")
    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "IMAGE_SIZE", length = 20)
    public String getImageSize() {
        return this.imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    @Column(name = "ECONOMY_CODE", length = 20, nullable = false)
    public String getEconomyCode() {
        return economyCode;
    }

    public void setEconomyCode(String economyCode) {
        this.economyCode = economyCode;
    }
}
