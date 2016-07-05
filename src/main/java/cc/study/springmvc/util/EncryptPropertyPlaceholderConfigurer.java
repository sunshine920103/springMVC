package cc.study.springmvc.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by Administrator on 2016/7/4.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private String[] encrptyPropNames={"jdbc.userName","jdbc.password"};
    private static final String KEY="sunshine";
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if(isEncryptProp(propertyName))
        {
            try {
                return DesUtil.decrypt(propertyValue,KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return propertyValue;
    }


    private boolean isEncryptProp(String propertyName)
    {
        for(String s:encrptyPropNames)
        {
            if(s.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }
}
