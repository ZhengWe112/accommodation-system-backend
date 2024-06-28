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
        //模拟登录
        if (userInfo == null) {
            userInfo = new UserInfoDTO();
            userInfo.setName("模拟用户");
        }

        return userInfo;
    }
}
