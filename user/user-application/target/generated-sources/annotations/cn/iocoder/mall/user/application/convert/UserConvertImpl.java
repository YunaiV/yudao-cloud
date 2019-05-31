package cn.iocoder.mall.user.application.convert;

import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.bo.UserPageBO;
import cn.iocoder.mall.user.application.vo.admins.AdminsUserPageVO;
import cn.iocoder.mall.user.application.vo.admins.AdminsUserVO;
import cn.iocoder.mall.user.application.vo.users.UsersUserVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:10+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public AdminsUserPageVO convert(UserPageBO result) {
        if ( result == null ) {
            return null;
        }

        AdminsUserPageVO adminsUserPageVO = new AdminsUserPageVO();

        adminsUserPageVO.setList( userBOListToAdminsUserVOList( result.getList() ) );
        adminsUserPageVO.setTotal( result.getTotal() );

        return adminsUserPageVO;
    }

    @Override
    public UsersUserVO convert2(UserBO result) {
        if ( result == null ) {
            return null;
        }

        UsersUserVO usersUserVO = new UsersUserVO();

        usersUserVO.setId( result.getId() );
        usersUserVO.setMobile( result.getMobile() );
        usersUserVO.setNickname( result.getNickname() );
        usersUserVO.setAvatar( result.getAvatar() );

        return usersUserVO;
    }

    protected AdminsUserVO userBOToAdminsUserVO(UserBO userBO) {
        if ( userBO == null ) {
            return null;
        }

        AdminsUserVO adminsUserVO = new AdminsUserVO();

        adminsUserVO.setId( userBO.getId() );
        adminsUserVO.setMobile( userBO.getMobile() );
        adminsUserVO.setNickname( userBO.getNickname() );
        adminsUserVO.setAvatar( userBO.getAvatar() );
        adminsUserVO.setStatus( userBO.getStatus() );
        adminsUserVO.setCreateTime( userBO.getCreateTime() );

        return adminsUserVO;
    }

    protected List<AdminsUserVO> userBOListToAdminsUserVOList(List<UserBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsUserVO> list1 = new ArrayList<AdminsUserVO>( list.size() );
        for ( UserBO userBO : list ) {
            list1.add( userBOToAdminsUserVO( userBO ) );
        }

        return list1;
    }
}
