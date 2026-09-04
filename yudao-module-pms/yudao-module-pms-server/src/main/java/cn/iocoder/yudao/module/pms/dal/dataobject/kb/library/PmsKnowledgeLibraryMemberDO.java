package cn.iocoder.yudao.module.pms.dal.dataobject.kb.library;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.module.pms.enums.kb.library.PmsKnowledgeLibraryMemberLevelEnum;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * PMS 知识库成员 DO
 *
 * @author 芋道源码
 */
@TableName("pms_knowledge_library_member")
@KeySequence("pms_knowledge_library_member_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PmsKnowledgeLibraryMemberDO extends BaseDO {

    /**
     * 成员编号
     */
    @TableId
    private Long id;
    /**
     * 知识库编号
     *
     * 关联 {@link PmsKnowledgeLibraryDO#getId()}
     */
    private Long libraryId;
    /**
     * 后台用户编号
     *
     * 关联 {@link AdminUserRespDTO#getId()}
     */
    private Long userId;
    /**
     * 部门编号
     *
     * 关联 {@link DeptRespDTO#getId()}
     */
    private Long deptId;
    /**
     * 成员等级
     *
     * 枚举 {@link PmsKnowledgeLibraryMemberLevelEnum}
     */
    private Integer level;

}
