package com.zl.web.app;

/**
 * 常量
 * @author is_zhoufeng
 */
public interface Consts {
	
	/**
	 * 资源文件中的key 
	 */
	public static interface PropertiesKey{
		
		/**
		 * 上传文件根目录
		 */
		public static final String APP_UPLOAD_DIR = "app.upload.dir";
		/**
		 * 项目地址
		 */
		public static final String APP_CONTEXT_PATH = "app.context.path";
		/**
		 * 静态资源服务器地址
		 */
		public static final String APP_STATICSERVER_PATH = "app.staticserver.path";
		/**
		 * 静态文件服务器地址
		 */
		public static final String APP_FILESERVER_PATH = "app.fileserver.path";
		
	}
	

}
