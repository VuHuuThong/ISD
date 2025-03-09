package com.example.teachermanagement.components;

import com.example.teachermanagement.Utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class LocalizationUtils {
    //Lớp LocalizationUtils này là một tiện ích giúp lấy thông điệp đa ngôn ngữ trong ứng dụng Spring Boot
   @Autowired
    private  MessageSource messageSource;
   @Autowired
    private LocaleResolver localeResolver;
    //messageSource: Dùng để lấy thông điệp từ các file messages.properties theo ngôn ngữ tương ứng.
    //localeResolver: Dùng để xác định Locale hiện tại dựa trên request.
    public String getLocalizedMessage(String messageKey, Object... params) {//spread operator
        HttpServletRequest request = WebUtils.getCurrentRequest();
        Locale locale = localeResolver.resolveLocale(request);

        return messageSource.getMessage(messageKey, params, locale);
    }
}

