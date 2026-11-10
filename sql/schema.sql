-- 建数据库。utf8mb4 能存所有中文和表情符号
CREATE DATABASE IF NOT EXISTS seckill DEFAULT CHARACTER SET utf8mb4;
USE seckill;

-- 商品表：商品本身的资料
CREATE TABLE IF NOT EXISTS product (
                                       id             BIGINT         NOT NULL AUTO_INCREMENT,  -- 主键，插入时自动 +1
                                       product_name   VARCHAR(100)   NOT NULL,                 -- 商品名
    original_price DECIMAL(10, 2) NOT NULL,                 -- 原价
    status         TINYINT        NOT NULL DEFAULT 1,       -- 0-下架 1-上架
    create_time    DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,                              -- 插入时自动填当前时间
    update_time    DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 修改时自动更新
    PRIMARY KEY (id)
    );

-- 秒杀活动表：某个商品的一场秒杀
CREATE TABLE IF NOT EXISTS seckill_activity (
                                                id             BIGINT         NOT NULL AUTO_INCREMENT,
                                                product_id     BIGINT         NOT NULL,                 -- 卖的是哪个商品（product 表的 id）
                                                seckill_price  DECIMAL(10, 2) NOT NULL,                 -- 秒杀价
    seckill_stock  INT            NOT NULL DEFAULT 0,       -- 剩余库存，每卖一件减 1
    total_stock    INT            NOT NULL DEFAULT 0,       -- 总库存，创建后不变，用来算"已抢 xx%"
    start_time     DATETIME       NOT NULL,                 -- 开抢时间
    end_time       DATETIME       NOT NULL,                 -- 结束时间
    status         TINYINT        NOT NULL DEFAULT 0,       -- 0-未开始 1-进行中 2-已结束 3-已下架
    create_time    DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time    DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

-- 演示数据。INSERT IGNORE：id 已存在就跳过，所以重复执行不会报错
INSERT IGNORE INTO product (id, product_name, original_price, status)
VALUES (1, '秒杀测试手机', 6999.00, 1);

-- 活动 1：库存 5 件，1 小时前开始、30 天后结束，这段时间里一直是"进行中"
INSERT IGNORE INTO seckill_activity
    (id, product_id, seckill_price, seckill_stock, total_stock, start_time, end_time, status)
VALUES (1, 1, 4999.00, 5, 5, NOW() - INTERVAL 1 HOUR, NOW() + INTERVAL 30 DAY, 1);
