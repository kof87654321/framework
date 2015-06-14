package com.zl.web.app;

/**
 * 常量
 * 
 * @author is_zhoufeng
 */
public interface Consts {

	/**
	 * 资源文件中的key
	 */
	public static interface PropertiesKey {

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

		/**
		 * API文件存放目录
		 */
		public static final String APP_API_DIR = "app.api.dir";

	}

	/**
	 * 上传文件相关常量
	 */
	public static interface Upload {

		/**
		 * 文件所属业务
		 */
		public static enum BizType {
			/**
			 * 头像文件
			 */
			BIZ_TYPE_USERPHOTO((byte) 1, "userphoto"),
			/**
			 * 动态文件
			 */
			BIZ_TYPE_FEDD((byte) 2, "feedfile"),
			/**
			 * 聊天文件
			 */
			BIZ_TYPE_CHAT((byte) 3, "chatfile");
			/**
			 * 业务类型
			 */
			private byte type;

			/**
			 * 文件存放位置
			 */
			private String dir;

			private BizType(byte type, String dir) {
				this.type = type;
				this.dir = dir;
			}

			public byte getType() {
				return type;
			}

			public String getDir() {
				return dir;
			}

			public static BizType getByType(byte type) {
				for (BizType bizType : values()) {
					if (bizType.getType() == type) {
						return bizType;
					}
				}
				return null;
			}
		}

		// --文件类型
		/**
		 * 图片文件
		 */
		public static final byte FILE_TYPE_IMG = 1;
		/**
		 * 语音文件
		 */
		public static final byte FILE_TYPE_VOICE = 2;
		/**
		 * 视频文件
		 */
		public static final byte FILE_TYPE_VIDEO = 3;

	}

	public static interface ERRORCode {
		public static int SERVER_ERROR = -404; //  系统异常
		public static int TOKEN_ERROR = -403; // token异常
		public static int USER_ID_ERROR = -100;// userid 有问题
		public static int BIZ_ID_ERROR = -101;// 业务主键有问题
		public static int PARAM_ERROR = -300;// 参数异常
		public static int VALID_ERROR = -200;//验证码失败
	}

}
