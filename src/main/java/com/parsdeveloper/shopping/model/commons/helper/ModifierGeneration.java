package com.parsdeveloper.shopping.model.commons.helper;

import com.parsdeveloper.shopping.model.commons.annotations.Modifier;
import com.parsdeveloper.shopping.model.entity.security.User;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class ModifierGeneration implements AnnotationValueGeneration<Modifier> {

    private ValueGenerator<User> generator;

    @Override
    public void initialize(Modifier annotation, Class<?> propertyType) {
        generator = CurrentUserGenerator.INSTANCE;
    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.ALWAYS;
    }

    @Override
    public ValueGenerator<User> getValueGenerator() {
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
