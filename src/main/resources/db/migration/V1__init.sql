
-- 社員マスタ
create table if not exists `employee` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`name` varchar(100) NOT NULL,
	`code` varchar(20) NOT NULL,
	`last_update_datetime` datetime NOT NULL,
	`last_update_employee_id` int(11) unsigned NOT NULL,
	PRIMARY KEY (`id`)
);

-- 商品マスタ
create table if not exists `item` (
	`code` varchar(10) NOT NULL,
	`name` varchar(50) NOT NULL,
	`basic_tanka` decimal(7,2) NOT NULL,
	`last_update_datetime` datetime NOT NULL,
	`last_update_employee_id` int(11) unsigned NOT NULL,
	PRIMARY KEY (`code`)	
);

-- 伝票
create table if not exists `denpyo` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`denpyo_no` int(11) unsigned NOT NULL,
	`eda_no` int(2) NOT NULL,
	`denpyo_date` date,
	`last_update_datetime` datetime NOT NULL,
	`last_update_employee_id` int(11) unsigned NOT NULL,
	PRIMARY KEY (`id`)
);

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
	CONSTRAINT `FK_DENPYO_DETAIL_ITEM` FOREIGN KEY (`item_code`) REFERENCES `item` (`code`) ON DELETE NO ACTION ON UPDATE CASCADE
);

