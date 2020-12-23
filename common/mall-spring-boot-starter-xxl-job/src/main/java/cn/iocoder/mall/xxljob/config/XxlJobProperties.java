package cn.iocoder.mall.xxljob.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * XXL-Job 配置类
 */
@ConfigurationProperties("xxl.job")
public class XxlJobProperties {

    /**
     * 是否开启，默认为 true 关闭
     */
    private Boolean enabled = true;
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 控制器配置
     */
    private AdminProperties admin;
    /**
     * 执行器配置
     */
    private ExecutorProperties executor;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        if (enabled != null) {
            this.enabled = enabled;
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        if (accessToken != null && accessToken.trim().length() > 0) {
            this.accessToken = accessToken;
        }
    }

    public AdminProperties getAdmin() {
        return admin;
    }

    public void setAdmin(AdminProperties admin) {
        this.admin = admin;
    }

    public ExecutorProperties getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorProperties executor) {
        this.executor = executor;
    }

    /**
     * XXL-Job 调度器配置类
     */
    public static class AdminProperties {

        /**
         * 调度器地址
         */
        private String addresses;

        public String getAddresses() {
            return addresses;
        }

        public void setAddresses(String addresses) {
            this.addresses = addresses;
        }

        @Override
        public String toString() {
            return "AdminProperties{" +
                    "addresses='" + addresses + '\'' +
                    '}';
        }

    }

    /**
     * XXL-Job 执行器配置类
     */
    public static class ExecutorProperties {

        /**
         * 默认端口
         *
         * 这里使用 -1 表示随机
         */
        private static final Integer PORT_DEFAULT = -1;

        /**
         * 默认日志保留天数
         *
         * 默认为 -1，不清理，永久保留
         */
        private static final Integer LOG_RETENTION_DAYS_DEFAULT = -1;

        /**
         * 应用名
         */
        private String appName;
        /**
         * 执行器的 IP
         */
        private String ip;
        /**
         * 执行器的 Port
         */
        private Integer port = PORT_DEFAULT;
        /**
         * 日志地址
         */
        private String logPath;
        /**
         * 日志保留天数
         */
        private Integer logRetentionDays = LOG_RETENTION_DAYS_DEFAULT;

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getLogPath() {
            return logPath;
        }

        public void setLogPath(String logPath) {
            this.logPath = logPath;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public Integer getLogRetentionDays() {
            return logRetentionDays;
        }

        public void setLogRetentionDays(Integer logRetentionDays) {
            this.logRetentionDays = logRetentionDays;
        }
    }

}
