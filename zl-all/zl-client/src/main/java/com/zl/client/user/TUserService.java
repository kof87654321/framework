package com.zl.client.user;

import java.util.List;

import com.zl.pojo.Page;
import com.zl.pojo.TUser;
import com.zl.pojo.TUserProfile;
import com.zl.vo.TUserVO;

public interface TUserService {

    public TUserVO getUserVOById(Long userId, boolean profile, boolean token);

    public TUserVO updateUser(TUserVO tUserVO, boolean profile);

    public Long updateTUserProfile(TUserProfile tUserProfile, long userId, long id);
    
    public Long insertTUserProfile(List<TUserProfile> tUserProfileList, long userId);

    public List<TUserProfile> getTUserProfileList(long userId, long id,Page page);

    public TUserVO insertUser(TUserVO tUserVO);

    public TUser getUserById(Long userId);
    
    public List<TUserVO> getListByAreaAndIndustry(int area,int industry,Page page);
    
    public int deleteTUserProfileByIdAndUserId(Long userId,long id);

}
