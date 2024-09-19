package com.teamx.retroStore.util;

import org.springframework.ui.Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomModel implements Model {

    private final Map<String, Object> attributes = new HashMap<>();

    @Override
    public Model addAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
        return this;
    }

    @Override
    public Model addAttribute(Object attributeValue) {
        // Assuming the attribute name should be the simple class name of the attribute value
        return addAttribute(attributeValue.getClass().getSimpleName(), attributeValue);
    }

    @Override
    public Model addAllAttributes(Collection<?> attributeValues) {
        for (Object attributeValue : attributeValues) {
            addAttribute(attributeValue);
        }
        return this;
    }

    @Override
    public Model addAllAttributes(Map<String, ?> attributes) {
        this.attributes.putAll(attributes);
        return this;
    }

    @Override
    public Model mergeAttributes(Map<String, ?> attributes) {
        this.attributes.putAll(attributes);
        return this;
    }

    @Override
    public boolean containsAttribute(String attributeName) {
        return attributes.containsKey(attributeName);
    }

    @Override
    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    @Override
    public Map<String, Object> asMap() {
        return attributes;
    }
}
