package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.commons.annotations.AutoValue;
import com.parsdeveloper.shopping.model.commons.helper.SystemVersionGenerator;
import org.hibernate.tuple.GenerationTiming;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EffectiveVersionModel<T extends Number> extends EffectiveModel<T> {
    private Long serviceVersion;

    @Column(name = "SERVICE_VERSION")
    @AutoValue(value = SystemVersionGenerator.class, generationTiming = GenerationTiming.INSERT)
    public Long getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(Long serviceVersion) {
        this.serviceVersion = serviceVersion;
    }


}
