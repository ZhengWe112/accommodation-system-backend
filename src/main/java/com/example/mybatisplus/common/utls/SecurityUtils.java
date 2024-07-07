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
        /*
        if (userInfo == null) {
            userInfo = new UserInfoDTO();
            userInfo.setId(1L);
            userInfo.setName("模拟用户");
            userInfo.setUserType(1);
        }
        */
        return SessionUtils.getCurrentUserInfo();
    }
}
