package com.hapi.invoice;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InvoiceUnmarshaller {

    private ObjectMapper jacksonMapper;

    public InvoiceUnmarshaller() {
        this.jacksonMapper = new ObjectMapper();
        this.jacksonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        this.jacksonMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        this.jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Invoice unmarshall(String input) throws Exception {

        InputStream stream = new ByteArrayInputStream(input.getBytes("UTF-8"));
        Invoice result = this.jacksonMapper.readValue(stream, Invoice.class);

        return result;
    }
}
