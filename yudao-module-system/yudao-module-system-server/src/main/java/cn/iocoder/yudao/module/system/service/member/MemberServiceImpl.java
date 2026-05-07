package cn.iocoder.yudao.module.system.service.member;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.stereotype.Service;

/**
 * Member Service 实现类
 *
 * @author 芋道源码
 */
@Service
public class MemberServiceImpl implements MemberService {

    private static final String MEMBER_USER_API_CLASS_NAME = "cn.iocoder.yudao.module.member.api.user.MemberUserApi";

    private volatile Object memberUserApi;

    @Override
    public String getMemberUserMobile(Long id) {
        Object user = getMemberUser(id);
        if (user == null) {
            return null;
        }
        return ReflectUtil.invoke(user, "getMobile");
    }

    @Override
    public String getMemberUserEmail(Long id) {
        Object user = getMemberUser(id);
        if (user == null) {
            return null;
        }
        return ReflectUtil.invoke(user, "getEmail");
    }

    private Object getMemberUser(Long id) {
        if (id == null) {
            return null;
        }
        return ReflectUtil.invoke(getMemberUserApi(), "getUser", id);
    }

    private Object getMemberUserApi() {
        if (memberUserApi == null) {
            memberUserApi = SpringUtil.getBean(ClassUtil.loadClass(MEMBER_USER_API_CLASS_NAME));
        }
        return memberUserApi;
    }

}
