# 作业
## 用户、订单、详情表

#### 建表说明
* 1、user和order是一对多关系，其中user是一，order是多，即每个user可拥有多个order，而每个order只能属于一个user。一对多关系的两张表建立关联，不需要额外创建中间表。
* 2、order和product是多对多关系，多对多关系的两张表建立关联,新建订单项表order_detail记录订单项，每行记录一个订单项。
* 最终设计：
建立四张表user(主键uid)、product_info(主键product_id)、order(主order_id，uid)、order_detail(主键detail_id，order_id和product_id)

#### 问题
* 问题一：关于优化，我使用的是普通索引，但是针对商品的查询来说，感觉还应该对商品表加名称索引
* 问题二：这几张表只是简单的设计了一下，像详情表中的折扣、类目表等并没有设计，不足的地方，希望助教在给一些思路
