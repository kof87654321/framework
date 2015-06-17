package com.zl.client.device;

import com.zl.pojo.TDeviceUser;

/**
 * 设备信息服务
 * 
 * @author zhangxianjun
 * @version $Id: DeviceService.java, v 0.1 2015年6月17日 上午11:15:59 zhangxianjun Exp $
 */
public interface DeviceService {

    /**
     * 添加设备信息
     * 同一设备对应一个userid,同一个userID可以对应多个设备ID（later）
     * @param device
     * @param token
     * @return
     */
    public boolean addDevice(TDeviceUser device, String token);
}
