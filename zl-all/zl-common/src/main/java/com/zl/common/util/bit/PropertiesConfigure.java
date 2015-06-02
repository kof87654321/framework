package com.zl.common.util.bit;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
  将properties文件内容缓存到内存中
 * @author is_zhoufeng@163.com 
 * 2014年12月15日 上午12:34:26
 */
public class PropertiesConfigure extends PropertyPlaceholderConfigurer {

	private static Properties properties = new Properties() ;
	
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		properties.putAll(props);
	}

	public String getProperties(String key){
		return properties.getProperty(key); 
	}

}
