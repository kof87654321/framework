package com.zl.web.app.vo;

import java.io.Serializable;

/**
 * Ajax返回对象
 * @author is_zhoufeng@163.com 
 * 2015年1月25日 下午5:00:26
 */
public class AjaxResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9107114652498812085L;

	/**
	 * 类型，是否成功
	 * @see com.zl.web.app.vo.AjaxResultType
	 */
	private int success ;
	
	/**
	 * 返回的数据
	 */
	private Object data ;
	
	/**
	 * 返回消息
	 */
	private String msg ;
	
	/**
	 * 用于作为Error Code
	 */
	private int code ;
	
	/**
	 * 创建一个成功的返回值
	 * @param data
	 * @return
	 */
	public static AjaxResult newSuccessResult(Object data){
		AjaxResult result = new AjaxResult();
		result.setSuccess(AjaxResultType.SUCCESS.getValue());
		result.setData(data); 
		return result;
	}

	/**
	 * 创建一个失败的返回值
	 * @param data
	 * @param msg
	 * @param code
	 * @return
	 */
	public static AjaxResult newFailResult(Object data , String msg , int code){
		AjaxResult result = new AjaxResult();
		result.setSuccess(AjaxResultType.FAIL.getValue());
		result.setData(data); 
		result.setMsg(msg); 
		result.setCode(code);
		return result;
	}
	
	/**
	 * 创建一个异常返回值
	 * @param e
	 * @param msg
	 * @param code
	 * @return
	 */
	public static AjaxResult newExceptionResult(Exception e , String msg , int code){
		AjaxResult result = new AjaxResult();
		result.setSuccess(AjaxResultType.ERROR.getValue());
		result.setMsg(msg); 
		result.setCode(code);
		return result;
	}
	
	public int getSuccess() {
		return success;
	}

	public void setSuccess(int type) {
		this.success = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
