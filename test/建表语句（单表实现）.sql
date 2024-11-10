

CREATE TABLE `entry_view` (
  `id` VARCHAR(36) COMMENT '主键ID',
  `sequence_number` INT unique default null COMMENT '序号',
  `sample_code` VARCHAR(255) unique default null COMMENT '样品编号',
  `survey_ship` VARCHAR(255) default null COMMENT '调查船',
  `voyage` VARCHAR(255) default null COMMENT '航次',
  `sea_area` VARCHAR(255) default null COMMENT '海域',
  `station` VARCHAR(255) default null COMMENT '站位',
  `coordinate_x` DOUBLE default null COMMENT 'X坐标',
  `coordinate_y` DOUBLE default null COMMENT 'Y坐标',
  `detailed_address` VARCHAR(255) DEFAULT NULL COMMENT '详细地址',
  `end_depth` DOUBLE default null COMMENT '结束深度（m）',
  `heart_length` DOUBLE default null COMMENT '心长（m）',
  `storage_location` VARCHAR(255) default null COMMENT '存放位置',
  `preservation_status` VARCHAR(255) DEFAULT NULL COMMENT '保存状况',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入库单简单实现'
;

create index idx_ship ON entry_view (survey_ship);
create index idx_sample_code ON entry_view (sample_code);

create index idx_sea_area ON entry_view (sea_area);
create index idx_station ON entry_view (station);