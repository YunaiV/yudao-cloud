package cn.iocoder.mall.user.biz.convert;

import cn.iocoder.mall.user.api.bo.UserAddressBO;
import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.api.dto.UserAddressUpdateDTO;
import cn.iocoder.mall.user.biz.dataobject.UserAddressDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< Updated upstream
    date = "2019-05-31T17:43:38+0800",
=======
    date = "2019-05-24T11:27:48+0800",
>>>>>>> Stashed changes
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class UserAddressConvertImpl implements UserAddressConvert {

    @Override
    public UserAddressDO convert(UserAddressAddDTO userAddressAddDTO) {
        if ( userAddressAddDTO == null ) {
            return null;
        }

        UserAddressDO userAddressDO = new UserAddressDO();

        userAddressDO.setUserId( userAddressAddDTO.getUserId() );
        userAddressDO.setAreaNo( userAddressAddDTO.getAreaNo() );
        userAddressDO.setName( userAddressAddDTO.getName() );
        userAddressDO.setMobile( userAddressAddDTO.getMobile() );
        userAddressDO.setAddress( userAddressAddDTO.getAddress() );
        userAddressDO.setHasDefault( userAddressAddDTO.getHasDefault() );

        return userAddressDO;
    }

    @Override
    public UserAddressDO convert(UserAddressUpdateDTO userAddressUpdateDTO) {
        if ( userAddressUpdateDTO == null ) {
            return null;
        }

        UserAddressDO userAddressDO = new UserAddressDO();

        userAddressDO.setId( userAddressUpdateDTO.getId() );
        userAddressDO.setUserId( userAddressUpdateDTO.getUserId() );
        userAddressDO.setAreaNo( userAddressUpdateDTO.getAreaNo() );
        userAddressDO.setName( userAddressUpdateDTO.getName() );
        userAddressDO.setMobile( userAddressUpdateDTO.getMobile() );
        userAddressDO.setAddress( userAddressUpdateDTO.getAddress() );
        userAddressDO.setHasDefault( userAddressUpdateDTO.getHasDefault() );

        return userAddressDO;
    }

    @Override
    public UserAddressBO convert(UserAddressDO userAddressDO) {
        if ( userAddressDO == null ) {
            return null;
        }

        UserAddressBO userAddressBO = new UserAddressBO();

        userAddressBO.setId( userAddressDO.getId() );
        userAddressBO.setUserId( userAddressDO.getUserId() );
        userAddressBO.setAreaNo( userAddressDO.getAreaNo() );
        userAddressBO.setName( userAddressDO.getName() );
        userAddressBO.setMobile( userAddressDO.getMobile() );
        userAddressBO.setAddress( userAddressDO.getAddress() );
        userAddressBO.setHasDefault( userAddressDO.getHasDefault() );

        return userAddressBO;
    }

    @Override
    public List<UserAddressBO> convertUserAddressBOList(List<UserAddressDO> userAddressDOList) {
        if ( userAddressDOList == null ) {
            return null;
        }

        List<UserAddressBO> list = new ArrayList<UserAddressBO>( userAddressDOList.size() );
        for ( UserAddressDO userAddressDO : userAddressDOList ) {
            list.add( convert( userAddressDO ) );
        }

        return list;
    }
}
