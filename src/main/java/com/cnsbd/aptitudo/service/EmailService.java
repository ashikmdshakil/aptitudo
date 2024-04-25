package com.cnsbd.aptitudo.service;

import com.cnsbd.aptitudo.data.request.EmailRequestData;
import com.cnsbd.aptitudo.data.response.CommonResponseData;

public interface EmailService {
    CommonResponseData sendSubscriptionEmail(EmailRequestData emailRequestData);
}
