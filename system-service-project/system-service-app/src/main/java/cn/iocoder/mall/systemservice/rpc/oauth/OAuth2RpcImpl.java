package cn.iocoder.mall.systemservice.rpc.oauth;

import org.apache.dubbo.config.annotation.Service;

@Service(version = "${dubbo.provider.OAuth2Rpc.version}", validation = "false")
public class OAuth2RpcImpl implements OAuth2Rpc {
}
