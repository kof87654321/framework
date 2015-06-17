package com.zl.service.device.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.client.device.DeviceService;
import com.zl.client.user.TUserService;
import com.zl.common.util.token.TokenUtils;
import com.zl.dao.mapper.TDeviceUserMapperExt;
import com.zl.pojo.TDeviceUser;
import com.zl.pojo.TDeviceUserExample;
import com.zl.pojo.TDeviceUserExample.Criteria;
import com.zl.pojo.TUser;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private TDeviceUserMapperExt tDeviceUserMapperExt;

    @Autowired
    private TUserService         tUserService;

    @Override
    public boolean addDevice(TDeviceUser device, String token) {
        if (device == null || StringUtils.isBlank(device.getDeviceToken())) {
            return false;
        }
        Long userId = device.getUserId();
        //有用户ID的情况下，需要做权限校验
        if (userId != null) {
            if (StringUtils.isBlank(token)) {
                return false;
            }
            TUser user = tUserService.getUserById(userId);
            if (user == null) {
                return false;
            }
            boolean flag = TokenUtils.checkToken(user.getId(), user.getPassword(), token);
            if (!flag) {
                return false;
            }
        }
        Date now = new Date();
        TDeviceUserExample example = new TDeviceUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andDeviceTokenEqualTo(device.getDeviceToken());
        List<TDeviceUser> deviceList = tDeviceUserMapperExt.selectByExample(example);
        int result = 0;
        if (deviceList != null && !deviceList.isEmpty()) {
            device.setId(deviceList.get(0).getId());
            device.setModifyTime(now);
            result = tDeviceUserMapperExt.updateByPrimaryKey(device);
        } else {
            device.setCreateTime(now);
            device.setModifyTime(now);
            result = tDeviceUserMapperExt.insert(device);
        }
        return result > 0;
    }
}
