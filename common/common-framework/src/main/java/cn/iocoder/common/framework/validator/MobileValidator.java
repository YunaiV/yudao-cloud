package cn.iocoder.common.framework.validator;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.util.ValidationUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public void initialize(Mobile annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果手机号为空，默认不校验，即校验通过
        if (!StringUtils.hasText(value)) {
            return true;
        }
        // 校验手机
        return ValidationUtil.isMobile(value);
    }

}
