package br.com.coffeeandit.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static graphql.ErrorType.ValidationError;

public class NotFoundException extends RuntimeException implements GraphQLError {
    private Map<String, Object> parameters = new HashMap();

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Map<String, Object> additionParams) {
        this(message);
        if (additionParams != null) {
            parameters = additionParams;
        }
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ValidationError;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return this.parameters;
    }
}