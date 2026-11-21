-- 用于已经导入 AI 表结构及基础数据字典的 MySQL 库。
-- 如果 ai_write 已有 scene_params，跳过该列的建表变更。
SET @ai_write_ddl = IF(EXISTS (
    SELECT 1 FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'ai_write' AND COLUMN_NAME = 'scene_params'
), 'DO 0', 'ALTER TABLE `ai_write` ADD COLUMN `scene_params` varchar(1000) DEFAULT NULL COMMENT ''场景参数'' AFTER `language`');

PREPARE ai_write_ddl FROM @ai_write_ddl;
EXECUTE ai_write_ddl;
DEALLOCATE PREPARE ai_write_ddl;

UPDATE `system_dict_data`
SET `label` = '文章写作'
WHERE `dict_type` = 'ai_write_type' AND `value` = '1' AND `deleted` = b'0' AND `label` = '撰写';

INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `creator`, `updater`)
SELECT scene.sort, scene.label, scene.value, 'ai_write_type', 0, '1', '1'
FROM (
    SELECT 3 AS sort, '通用写作' AS label, '3' AS value
    UNION ALL SELECT 4, '周报', '4'
    UNION ALL SELECT 5, '小红书', '5'
    UNION ALL SELECT 6, '润色 / 改写', '6'
    UNION ALL SELECT 7, '文本翻译', '7'
) AS scene
WHERE NOT EXISTS (
    SELECT 1 FROM `system_dict_data` AS dict
    WHERE dict.`dict_type` = 'ai_write_type' AND dict.`value` = scene.value AND dict.`deleted` = b'0'
);
