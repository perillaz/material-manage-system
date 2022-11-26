package org.xzgtemp.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class SqlDateUtil{

    public Date StringtoDate(String str){
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            java.util.Date d = null; 
            d =  ft.parse(str);
            return new java.sql.Date(d.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
