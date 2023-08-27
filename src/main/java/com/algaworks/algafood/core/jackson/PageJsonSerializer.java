package com.algaworks.algafood.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(Page<?> page, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("content",page.getContent());
        jsonGenerator.writeObjectField("size", page.getSize());
        jsonGenerator.writeObjectField("totalElements", page.getTotalElements());
        jsonGenerator.writeObjectField("totalPages", page.getTotalPages());
        jsonGenerator.writeObjectField("number", page.getNumber());

        jsonGenerator.writeEndObject();

    }
}
