package com.example.mybatisplus.common.utls;

import com.example.mybatisplus.model.dto.UserInfoDTO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    private static final String USERKEY = "sessionUser";

    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    public static UserInfoDTO getCurrentUserInfo() {
        return (UserInfoDTO) session().getAttribute(USERKEY);
    }

    public static void saveCurrentUserInfo(UserInfoDTO userInfo) {
        session().setAttribute(USERKEY, userInfo);
    }
}
