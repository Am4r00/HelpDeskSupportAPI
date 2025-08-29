package com.helpdesk.supportapi.shared.validation;

import com.helpdesk.supportapi.exception.business.InvalidArgumentsException;
import com.helpdesk.supportapi.exception.business.ListNullException;
import java.util.List;

public class ValidationUtils {
    public static void validateNotNull(Object o) {
        if (o == null)
            throw new InvalidArgumentsException("The field Cannot be null ");
    }

    public static void validateNotNull(Object o, Object o2) {
        if (o == null || o2 == null)
            throw new InvalidArgumentsException("invalid or null arguments ");
    }

    public static void validateNotNull(Object o, Object o2,Object o3) {
        if (o == null || o2 == null || o3 == null)
            throw new InvalidArgumentsException("invalid or null arguments ");
    }

    public static void validateListNotEmpty(List<?> list){
        if(list == null || list.isEmpty())
            throw new ListNullException("The list is empty");
    }
}
