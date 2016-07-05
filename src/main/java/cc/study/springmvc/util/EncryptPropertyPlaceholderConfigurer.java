package cc.study.springmvc.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by Administrator on 2016/7/4.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private String[] encrptyPropNames={"jdbc.userName","jdbc.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        return super.convertProperty(propertyName, propertyValue);
    }
}
