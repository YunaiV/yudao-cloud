package cn.iocoder.yudao.module.infra.api.file;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.infra.api.file.dto.FileCreateReqDTO;
import cn.iocoder.yudao.module.infra.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 文件")
public interface FileApi {

    String PREFIX = ApiConstants.PREFIX + "/file";

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param content 文件内容
     * @return 文件路径
     */
    default String createFile(byte[] content) {
        return createFile(content, null, null, null);
    }

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param content 文件内容
     * @param name 文件名称，允许空
     * @return 文件路径
     */
    default String createFile(byte[] content, String name) {
        return createFile(content, name, null, null);
    }

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param content 文件内容
     * @param name 文件名称，允许空
     * @param directory 目录，允许空
     * @param type 文件的 MIME 类型，允许空
     * @return 文件路径
     */
    default String createFile(@NotEmpty(message = "文件内容不能为空") byte[] content,
                              String name, String directory, String type) {
        return createFile(new FileCreateReqDTO().setName(name).setDirectory(directory).setType(type).setContent(content)).getCheckedData();
    }

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "保存文件，并返回文件的访问路径")
    CommonResult<String> createFile(@Valid @RequestBody FileCreateReqDTO createReqDTO);

}
