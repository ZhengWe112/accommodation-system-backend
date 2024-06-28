package com.example.mybatisplus.common.utls;

import com.example.mybatisplus.model.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityUtils {
    /**
     * 获取当前用户
     *
     */
    public static UserInfoDTO getCurrentUserInfo() {
        UserInfoDTO userInfo = SessionUtils.getCurrentUserInfo();
        // 模拟登录 TODO: 因为现在还没有登录功能 以后要改
        if (userInfo == null) {
            userInfo = new UserInfoDTO();
            userInfo.setId(1L);
            userInfo.setName("模拟用户");
            userInfo.setUserType(1L);
        }

        return userInfo;
    }
}
