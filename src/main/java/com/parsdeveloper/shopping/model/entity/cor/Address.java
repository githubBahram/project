package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ADDRESS", schema = ApplicationSchema.APPLICATION_SCHEMA)

public class Address extends EffectiveModel<Long> {

    private String region;
    private String fullAddress;
    private Zone city;
    private Zone province;
    private String postalCode;
    private Double latitude;
    private Double longitude;
    private Zone country;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "FULL_ADDRESS", nullable = false, length = 255)
    @NotBlank
    @Length(max = 255)
    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }


    @Column(name = "REGION", length = 100)
    @Length(max = 100)
    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    //bi-directional many-to-one association to Zone
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID", nullable = false)
    @NotNull
    public Zone getCity() {
        return this.city;
    }

    public void setCity(Zone city) {
        this.city = city;
    }


    //bi-directional many-to-one association to Zone
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVINCE_ID", nullable = false)
    @NotNull
    public Zone getProvince() {
        return this.province;
    }

    public void setProvince(Zone zone2) {
        this.province = zone2;
    }

    @Column(name = "POSTAL_CODE", nullable = false, length = 10)
    @NotNull
    @Length(min = 10, max = 10, message = "address.error.length.postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "LATITUDE", precision = 16, scale = 14)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "LONGITUDE", precision = 16, scale = 14)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", nullable = true)
    public Zone getCountry() {
        return country;
    }

    public void setCountry(Zone country) {
        this.country = country;
    }
}