package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.dto.BannerAddDTO;
import cn.iocoder.mall.promotion.api.dto.BannerUpdateDTO;
import cn.iocoder.mall.promotion.biz.dataobject.BannerDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:38:52+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class BannerConvertImpl implements BannerConvert {

    @Override
    public BannerBO convertToBO(BannerDO banner) {
        if ( banner == null ) {
            return null;
        }

        BannerBO bannerBO = new BannerBO();

        bannerBO.setId( banner.getId() );
        bannerBO.setTitle( banner.getTitle() );
        bannerBO.setUrl( banner.getUrl() );
        bannerBO.setPicUrl( banner.getPicUrl() );
        bannerBO.setSort( banner.getSort() );
        bannerBO.setStatus( banner.getStatus() );
        bannerBO.setMemo( banner.getMemo() );
        bannerBO.setCreateTime( banner.getCreateTime() );

        return bannerBO;
    }

    @Override
    public List<BannerBO> convertToBO(List<BannerDO> bannerList) {
        if ( bannerList == null ) {
            return null;
        }

        List<BannerBO> list = new ArrayList<BannerBO>( bannerList.size() );
        for ( BannerDO bannerDO : bannerList ) {
            list.add( convertToBO( bannerDO ) );
        }

        return list;
    }

    @Override
    public BannerDO convert(BannerAddDTO bannerAddDTO) {
        if ( bannerAddDTO == null ) {
            return null;
        }

        BannerDO bannerDO = new BannerDO();

        bannerDO.setTitle( bannerAddDTO.getTitle() );
        bannerDO.setUrl( bannerAddDTO.getUrl() );
        bannerDO.setPicUrl( bannerAddDTO.getPicUrl() );
        bannerDO.setSort( bannerAddDTO.getSort() );
        bannerDO.setMemo( bannerAddDTO.getMemo() );

        return bannerDO;
    }

    @Override
    public BannerDO convert(BannerUpdateDTO bannerUpdateDTO) {
        if ( bannerUpdateDTO == null ) {
            return null;
        }

        BannerDO bannerDO = new BannerDO();

        bannerDO.setId( bannerUpdateDTO.getId() );
        bannerDO.setTitle( bannerUpdateDTO.getTitle() );
        bannerDO.setUrl( bannerUpdateDTO.getUrl() );
        bannerDO.setPicUrl( bannerUpdateDTO.getPicUrl() );
        bannerDO.setSort( bannerUpdateDTO.getSort() );
        bannerDO.setMemo( bannerUpdateDTO.getMemo() );

        return bannerDO;
    }
}
