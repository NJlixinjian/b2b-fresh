package cn.sigo.sigocloudprovider.config;

import com.alibaba.fastjson.serializer.NameFilter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * 功能描述:FastJson自定义
 *
 * @author wangcong
 * @date $date$ $time$
 */
public class FastJsonLowCaseFilter implements NameFilter {
    public FastJsonLowCaseFilter() {
    }

    @Override
    public String process(Object source, String name, Object value) {
        if (name != null && name.length() != 0) {
           return  name.toLowerCase();
        } else {
            return name;
        }
    }
}
