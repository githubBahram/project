package com.parsdeveloper.shopping.model.commons.helper;

import com.parsdeveloper.shopping.model.commons.annotations.AutoValue;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class AutoValueGeneration implements AnnotationValueGeneration<AutoValue> {

    private ValueGenerator<?> generator;
    private GenerationTiming generationTiming;

    @Override
    public void initialize(AutoValue annotation, Class<?> propertyType) {
        Class<?> value = annotation.value();
        try {
            generator = (ValueGenerator<?>) value.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        generationTiming = annotation.generationTiming();
    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return generationTiming;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return generator;
    }

    @Override
    public boolean referenceColumnInSql() {
        return false;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }


}
