package com.example.jpa.sort.querydsl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TblCodec {
    public static List<String> encode(String... tableNames) {
        return Arrays.stream(tableNames).map(TblCodec::encodeOne).collect(Collectors.toList());
    }
    
    public static List<String> decode(String... tableCodes) {
        return Arrays.stream(tableCodes).map(TblCodec::decodeOne).collect(Collectors.toList());
    }
    
    public static String encodeOne(String tableName) {
        return tableName;
    }
    
    public static String decodeOne(String tableCode) {
        return tableCode;
    }
}
