package com.qagile.configurator.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * User: ful
 * Date: 30.04.12
 * Time: 22:21
 */
public class LanguageSupportFactory {

    public static String getInfoSheetName(){
        return "Info";
    }

    public static LanguageSupport getLanguageSupport(String language) {
        if (language.equals("en")){
           return new LanguageSupportEnglish();
        }else{
           return new LanguageSupportEnglish();
        }
    }

}
