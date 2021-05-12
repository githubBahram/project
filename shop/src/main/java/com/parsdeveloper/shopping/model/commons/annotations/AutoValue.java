package com.parsdeveloper.shopping.model.commons.annotations;

import com.parsdeveloper.shopping.model.commons.helper.AutoValueGeneration;
import org.hibernate.annotations.ValueGenerationType;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ValueGenerationType(generatedBy = AutoValueGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoValue {

    Class<? extends ValueGenerator> value() ;

    GenerationTiming generationTiming() ;
}
