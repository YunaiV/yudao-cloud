package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.dto.systemlog.AccessLogAddDTO;
import cn.iocoder.mall.admin.api.dto.systemlog.ExceptionLogAddDTO;
import cn.iocoder.mall.admin.dataobject.AccessLogDO;
import cn.iocoder.mall.admin.dataobject.ExceptionLogDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:10:14+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class AccessLogConvertImpl implements AccessLogConvert {

    @Override
    public AccessLogDO convert(AccessLogAddDTO accessLogAddDTO) {
        if ( accessLogAddDTO == null ) {
            return null;
        }

        AccessLogDO accessLogDO = new AccessLogDO();

        accessLogDO.setTraceId( accessLogAddDTO.getTraceId() );
        accessLogDO.setUserId( accessLogAddDTO.getUserId() );
        accessLogDO.setUserType( accessLogAddDTO.getUserType() );
        accessLogDO.setApplicationName( accessLogAddDTO.getApplicationName() );
        accessLogDO.setUri( accessLogAddDTO.getUri() );
        accessLogDO.setQueryString( accessLogAddDTO.getQueryString() );
        accessLogDO.setMethod( accessLogAddDTO.getMethod() );
        accessLogDO.setUserAgent( accessLogAddDTO.getUserAgent() );
        accessLogDO.setIp( accessLogAddDTO.getIp() );
        accessLogDO.setStartTime( accessLogAddDTO.getStartTime() );
        accessLogDO.setResponseTime( accessLogAddDTO.getResponseTime() );
        accessLogDO.setErrorCode( accessLogAddDTO.getErrorCode() );
        accessLogDO.setErrorMessage( accessLogAddDTO.getErrorMessage() );

        return accessLogDO;
    }

    @Override
    public ExceptionLogDO convert(ExceptionLogAddDTO exceptionLogAddDTO) {
        if ( exceptionLogAddDTO == null ) {
            return null;
        }

        ExceptionLogDO exceptionLogDO = new ExceptionLogDO();

        exceptionLogDO.setTraceId( exceptionLogAddDTO.getTraceId() );
        exceptionLogDO.setUserId( exceptionLogAddDTO.getUserId() );
        exceptionLogDO.setUserType( exceptionLogAddDTO.getUserType() );
        exceptionLogDO.setApplicationName( exceptionLogAddDTO.getApplicationName() );
        exceptionLogDO.setUri( exceptionLogAddDTO.getUri() );
        exceptionLogDO.setQueryString( exceptionLogAddDTO.getQueryString() );
        exceptionLogDO.setMethod( exceptionLogAddDTO.getMethod() );
        exceptionLogDO.setUserAgent( exceptionLogAddDTO.getUserAgent() );
        exceptionLogDO.setIp( exceptionLogAddDTO.getIp() );
        exceptionLogDO.setExceptionTime( exceptionLogAddDTO.getExceptionTime() );
        exceptionLogDO.setExceptionName( exceptionLogAddDTO.getExceptionName() );
        exceptionLogDO.setExceptionMessage( exceptionLogAddDTO.getExceptionMessage() );
        exceptionLogDO.setExceptionRootCauseMessage( exceptionLogAddDTO.getExceptionRootCauseMessage() );
        exceptionLogDO.setExceptionStackTrace( exceptionLogAddDTO.getExceptionStackTrace() );
        exceptionLogDO.setExceptionClassName( exceptionLogAddDTO.getExceptionClassName() );
        exceptionLogDO.setExceptionFileName( exceptionLogAddDTO.getExceptionFileName() );
        exceptionLogDO.setExceptionMethodName( exceptionLogAddDTO.getExceptionMethodName() );
        exceptionLogDO.setExceptionLineNumber( exceptionLogAddDTO.getExceptionLineNumber() );

        return exceptionLogDO;
    }
}
