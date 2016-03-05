package com.wolf.dao.base;

import java.util.HashMap;

/**
 * Created by wolf on 16/02/16.
 */
public class LowerCaseMap extends HashMap {
    @Override
    public Object put(Object key, Object value) {
        if(key instanceof String){
           key =  ((String) key).toLowerCase();
        }
        return super.put(key, value);
    }
}
