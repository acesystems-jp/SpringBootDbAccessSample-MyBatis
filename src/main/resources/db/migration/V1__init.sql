drop table if exists `employee_history`;

drop table if exists `denpyo_detail`;
drop table if exists `denpyo`;
drop table if exists `item`;
drop table if exists `employee`;


-- 社員マスタ
create table if not exists `employee` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	`code` varchar(20) NOT NULL,
	`last_update_datetime` datetime NOT NULL,
	`last_update_employee_id` int(11) unsigned NOT NULL,
	PRIMARY KEY (`id`),
	-- 一意制約 社員番号
	CONSTRAINT `uq_employee_code` UNIQUE (`code`)
);

COMMENT ON COLUMN `employee`.`id` IS 'ID';
COMMENT ON COLUMN `employee`.`name` IS '社員名';
COMMENT ON COLUMN `employee`.`code` IS '社員コード';
COMMENT ON COLUMN `employee`.`last_update_datetime` IS '最終更新日時';
COMMENT ON COLUMN `employee`.`last_update_employee_id` IS '最終更新者ID';

-- 商品マスタ
create table if not exists `item` (
	`code` varchar(10) NOT NULL,
	`name` varchar(50) NOT NULL,
	`basic_tanka` decimal(7,2) NOT NULL,
	`last_update_datetime` datetime NOT NULL,
	`last_update_employee_id` int(11) unsigned NOT NULL,
	PRIMARY KEY (`code`)	
);

COMMENT ON COLUMN `item`.`code` IS '商品コード';
COMMENT ON COLUMN `item`.`name` IS '商品名';
COMMENT ON COLUMN `item`.`basic_tanka` IS '基本単価';
COMMENT ON COLUMN `item`.`last_update_datetime` IS '最終更新日時';
COMMENT ON COLUMN `item`.`last_update_employee_id` IS '最終更新者ID';

-- 伝票
create table if not exists `denpyo` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`denpyo_no` int(11) unsigned NOT NULL,
	`eda_no` int(2) NOT NULL,
	`denpyo_date` date,
	`last_update_datetime` datetime NOT NULL,
	`last_update_employee_id` int(11) unsigned NOT NULL,
	PRIMARY KEY (`id`),
	-- 一意制約 伝票No＋枝No
	CONSTRAINT `uq_denpyo_denpyo_no` UNIQUE (`denpyo_no`, `eda_no`)
);

COMMENT ON COLUMN `denpyo`.`id` IS 'ID';
COMMENT ON COLUMN `denpyo`.`denpyo_no` IS '伝票番号';
COMMENT ON COLUMN `denpyo`.`eda_no` IS '伝票枝番';
COMMENT ON COLUMN `denpyo`.`denpyo_date` IS '伝票日付';
COMMENT ON COLUMN `denpyo`.`last_update_datetime` IS '最終更新日時';
COMMENT ON COLUMN `denpyo`.`last_update_employee_id` IS '最終更新者ID';

-- 伝票明細
create table if not exists `denpyo_detail` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`denpyo_id` int(11) unsigned NOT NULL,
	`row_no` int(3) NOT NULL,
	`item_code` varchar(10) NOT NULL,
	`suryo` int(3) NOT NULL,
	`tanka` decimal(7,2) NOT NULL,
	`last_update_datetime` datetime NOT NULL,
	`last_update_employee_id` int(11) unsigned NOT NULL,
	PRIMARY KEY (`id`),
	-- 伝票の削除・更新には自動追従
	CONSTRAINT `FK_DENPYO_DETAIL_DENPYO` FOREIGN KEY (`denpyo_id`) REFERENCES `denpyo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	-- 商品マスタの削除時はリアクションなし。更新は自動追従
	CONSTRAINT `FK_DENPYO_DETAIL_ITEM` FOREIGN KEY (`item_code`) REFERENCES `item` (`code`) ON DELETE NO ACTION ON UPDATE CASCADE,
	-- 一意制約 伝票ID＋行No
	CONSTRAINT `uq_denpyo_detail_row` UNIQUE (`denpyo_id`, `row_no`)
);

COMMENT ON COLUMN `denpyo_detail`.`id` IS 'ID';
COMMENT ON COLUMN `denpyo_detail`.`denpyo_id` IS '伝票ID';
COMMENT ON COLUMN `denpyo_detail`.`row_no` IS '伝票行番号';
COMMENT ON COLUMN `denpyo_detail`.`item_code` IS '商品コード';
COMMENT ON COLUMN `denpyo_detail`.`suryo` IS '数量';
COMMENT ON COLUMN `denpyo_detail`.`tanka` IS '単価';
COMMENT ON COLUMN `denpyo_detail`.`last_update_datetime` IS '最終更新日時';
COMMENT ON COLUMN `denpyo_detail`.`last_update_employee_id` IS '最終更新者ID';

-- 社員マスタ履歴テーブル
create table if not exists `employee_history` (
	`history_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`id` int(11) unsigned NOT NULL,
	`name` varchar(100) NOT NULL,
	`code` varchar(20) NOT NULL,
	`last_update_datetime` datetime NOT NULL,
	`last_update_employee_id` int(11) unsigned NOT NULL,
	`operation_datetime` datetime,
	`operation` varchar(10) NOT NULL,
	PRIMARY KEY (`history_id`)
);

COMMENT ON COLUMN `employee_history`.`history_id` IS '履歴ID';
COMMENT ON COLUMN `employee_history`.`id` IS 'ID';
COMMENT ON COLUMN `employee_history`.`name` IS '社員名';
COMMENT ON COLUMN `employee_history`.`code` IS '社員コード';
COMMENT ON COLUMN `employee_history`.`last_update_datetime` IS '最終更新日時';
COMMENT ON COLUMN `employee_history`.`last_update_employee_id` IS '最終更新者ID';
COMMENT ON COLUMN `employee_history`.`operation_datetime` IS '操作日時';
COMMENT ON COLUMN `employee_history`.`operation` IS 'CRUD操作';
