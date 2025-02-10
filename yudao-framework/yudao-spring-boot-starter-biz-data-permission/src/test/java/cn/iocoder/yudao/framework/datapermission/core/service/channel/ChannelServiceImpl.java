package cn.iocoder.yudao.framework.datapermission.core.service.channel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.datapermission.core.dal.dataobject.Channel;
import cn.iocoder.yudao.framework.datapermission.core.dal.dataobject.UserChannel;
import cn.iocoder.yudao.framework.datapermission.core.dal.mapper.ChannelMapper;
import cn.iocoder.yudao.framework.datapermission.core.dal.mapper.UserChannelMapper;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.api.permission.PermissionApi;
import cn.iocoder.yudao.module.system.api.tenant.TenantApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception0;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * ChannelServiceImpl
 *
 * @author dxsc_jhj
 * @date 2025-02-10 11:01
 **/
@Service
@Slf4j
public class ChannelServiceImpl extends BaseDbUnitTest implements ChannelService {
    @Mock
    private PermissionApi permissionApi;
    @Mock
    private TenantApi tenantApi;

    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private UserChannelMapper userChannelMapper;

    CommonResult<Boolean> authUserChannel(Long tenantId, Long deptId, Long userId, Boolean auth, Collection<Long> numbers) {
        if (tenantId == null && deptId == null && userId == null) {
            throw exception0(-1, "用户或部门编码至少选中一个！");
        }
        // 校验
        if (ObjUtil.isNotEmpty(tenantId)) {
            Boolean f = tenantApi.validTenant(tenantId).getCheckedData();
            if(!f){
                throw exception0(-1, "租户传入异常！");
            }
        }
        if (ObjUtil.isNotEmpty(deptId)) {
            /* 校验 部门编号 */
        }
        if (ObjUtil.isNotEmpty(userId)) {
            /* 校验 用户编号 */
        }
        authUserChannel(auth, tenantId, deptId, userId, numbers);
        return success(true);
    }

    public void authUserChannel(Boolean auth, Long tenantId, Long deptId, Long userId, Collection<Long> numbers) {
        if (auth) {
            // 获得部门已拥有的所有通道编号/设备编号
            List<UserChannel> dbList = userChannelMapper.selectList(new LambdaQueryWrapperX<UserChannel>()
                    .eqIfPresent(UserChannel::getUserId, userId)
                    .eqIfPresent(UserChannel::getDeptId, deptId)
                    .eqIfPresent(UserChannel::getTenantId, tenantId)
                    .isNotNull(UserChannel::getChannelId)
            );
            // Map<String, String> dbDeviceIds = convertMap(dbList, UserChannel::getChannelId, UserChannel::getDeviceNumber);
            Set<Long> dbChannels = convertSet(dbList, UserChannel::getChannelId);
            // 计算新增和删除的角色编号
            Set<Long> channelIdList = CollUtil.emptyIfNull(CollectionUtil.newHashSet(numbers));
            Collection<Long> createChannelIds = CollUtil.subtract(channelIdList, dbChannels);
            Collection<Long> updateChannelIds = CollUtil.intersectionDistinct(channelIdList, dbChannels); // 新授权的且在数据库里已经存在的通道/设备数据
            Collection<Long> deleteChannelIds = CollUtil.subtract(dbChannels, channelIdList);
            Collection<Long> deleteDbChannelIds = CollUtil.newHashSet();
            // 执行新增和删除。对于已经授权的通道，不用做任何处理
            // TODO 新增的肯定是数据库里没有的
            //  还需要更新数据库中对应的 channelNumber
            Long targetTenantId = getTenantId(tenantId, userId, deptId);
            if (CollectionUtil.isNotEmpty(createChannelIds)) {
                userChannelMapper.insertBatch(CollectionUtils.convertList(createChannelIds, channelNumber -> {
                    /* 校验原始数据是否存在 */
                    Channel channel = new Channel();
                    if(channel == null){
                        throw new ServiceException(-1, String.format("通道不存在，%s", channelNumber));
                    }
                    UserChannel insertItem = UserChannel.builder()
                            .channelId(channelNumber).userId(userId).deptId(deptId)
                            .build();
                    insertItem.setTenantId(targetTenantId);
                    return insertItem;
                }));
            }
            if (CollectionUtil.isNotEmpty(updateChannelIds)) {
                // 找到 updateChannelIds 这一部分通道对应数据库里的 deviceNumber
                convertSet(dbList, userChannel -> {
                    /* 校验原始数据是否存在 */
                    Channel channel = new Channel();
                    if(channel == null){
                        // 取授权表里的数据发现异常 更新 deleteChannelIds
                        log.error("[ 授权通道 ] 时发现，授权库中 - 原有通道已不存在，向删除数组中新增此通道，{}", userChannel.getChannelId());
                        deleteDbChannelIds.add(userChannel.getChannelId());
                        return null;
                    }
                    UserChannel updateOne = UserChannel.builder()
                            .channelId(userChannel.getChannelId()).userId(userId).deptId(deptId)
                            .build();
                    return updateOne;
                });
                // platformUserChannelMapper.updateListByUserIdAndDeptIdAndTypeAndNumberIds(userId, deptId, targetTenantId, 2, updateChannels);
            }
            if (!CollectionUtil.isEmpty(deleteChannelIds)) {
                userChannelMapper.delete(new LambdaQueryWrapperX<UserChannel>()
                        .eqIfPresent(UserChannel::getUserId, userId)
                        .eqIfPresent(UserChannel::getDeptId, deptId)
                        .eqIfPresent(UserChannel::getTenantId, tenantId)
                        .in(UserChannel::getChannelId, deleteChannelIds)
                );
            }
            if (!CollectionUtil.isEmpty(deleteDbChannelIds)) {
                userChannelMapper.delete(new LambdaQueryWrapperX<UserChannel>()
                        .eqIfPresent(UserChannel::getDeptId, deptId)
                        .eqIfPresent(UserChannel::getUserId, userId)
                        .eqIfPresent(UserChannel::getTenantId, tenantId)
                        .in(UserChannel::getChannelId, deleteChannelIds)
                );
            }
        } else {
            userChannelMapper.delete(new LambdaQueryWrapperX<UserChannel>()
                    .eqIfPresent(UserChannel::getUserId, userId)
                    .eqIfPresent(UserChannel::getDeptId, deptId)
                    .eqIfPresent(UserChannel::getTenantId, tenantId)
                    .inIfPresent(UserChannel::getChannelId, numbers)
            );
        }
    }

    public Long getTenantId(Long tenantId, Long userId, Long deptId) {
        if(tenantId != null){
            return tenantId;
        }
        if(userId != null){
            /* 获取用户 或 部门的租户编号 */
            Long tenantResultId = 1L;
            return tenantResultId;
        }
        if(deptId != null){
            /* 获取用户 或 部门的租户编号 */
            Long tenantResultId = 1L;
            return tenantResultId;
        }
        return 0L;
    }

    @Test
    public void testAuthUserChannel() {
        authUserChannel(true, 1L, 1L, 1L, CollUtil.newArrayList(1L,2L,3L));
        List<UserChannel> userChannels = userChannelMapper.selectList();
        userChannels.forEach(userChannel -> log.info("{}", userChannel));
    }
}
