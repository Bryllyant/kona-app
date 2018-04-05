package com.bryllyant.kona.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KUtil {
    private static Logger logger = LoggerFactory.getLogger(KUtil.class);

    private static KUtil instance = null;



    public static KUtil getInstance() {
        if (instance == null) {
            instance = new KUtil();
        }
        return instance;
    }



}
