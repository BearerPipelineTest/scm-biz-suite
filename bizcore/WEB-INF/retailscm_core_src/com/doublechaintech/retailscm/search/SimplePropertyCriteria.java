package com.doublechaintech.retailscm.search;

import com.doublechaintech.retailscm.BaseEntity;
import com.terapico.utils.TextUtil;

import java.lang.reflect.Array;
import java.util.*;

public class SimplePropertyCriteria implements SearchCriteria {
    private String mProperty;
    private QueryOperator mQueryOperator;
    private Object mValue;

    public SimplePropertyCriteria(String property, QueryOperator pQueryOperator, Object pValue) {
        mProperty = property;
        mQueryOperator = pQueryOperator;
        mValue = pValue;
    }

     public String prepareParameterAndSql(Map<String, Object> parameters) {
        StringBuilder sb = new StringBuilder("(");
        sb.append(TextUtil.propertyToColumnName(mProperty)).append(mQueryOperator.getOperator());
        String parameterName = refineParameterName(parameters, mProperty);
        if (mQueryOperator.hasParameter()) {
            if (!mQueryOperator.containsOperator()) {
                sb.append(mQueryOperator.getPrefix());
            }
            sb.append(":").append(parameterName);
            if (!mQueryOperator.containsOperator()) {
                sb.append(mQueryOperator.getSuffix());
            }
            Object value = cleanValue(mValue);
            if (value == null) {
                return "";
            }
            parameters.put(parameterName, value);
        }
        sb.append(")");
        return sb.toString();
    }

    private Object cleanValue(Object value) {
        if (value == null) {
            return null;
        }
        Set<Object> listValues = new LinkedHashSet<>();
        addValue(listValues, value);
        if (listValues.isEmpty()) {
            return null;
        }
        if (mQueryOperator.hasArrayParameter()) {
            return listValues;
        } else {
            if (mQueryOperator.containsOperator()) {
                return mQueryOperator.getPrefix() + listValues.iterator().next() + mQueryOperator.getSuffix();
            }
            return listValues.iterator().next();
        }
    }

    protected void addValue(Set<Object> listValues, Object value) {
        if (value == null) {
            return;
        }
        if (value.getClass().isArray()) {
            int len = Array.getLength(value);
            for (int i = 0; i < len; i++) {
                Object ele = Array.get(value, i);
                addValue(listValues, ele);
            }
        } else if (value instanceof Collection) {
            for (Object ele : (Collection) value) {
                addValue(listValues, ele);
            }
        } else if (value instanceof BaseEntity) {
            listValues.add(((BaseEntity) value).getId());
        } else {
            listValues.add(value);
        }
    }
}

