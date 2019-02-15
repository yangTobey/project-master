-------------------2019-1-11编写------------------

系统使用技术：springboot、mysql、redis、shiro、mybatis、前端使用layui+iquery
系统集成：swagger在线接口文档、Valid实体字段校验
大屏数据显示的来源：redis缓存，大屏的数据为实时数据，通过websocket进行长连接实现实时更新，在后台通过修改或者新增数据，大屏的数据都会实时的更新

数据更新规则：每月的10号，作为每月数据显示的更新时间，例如，3月9号，显示的月数据为1月份的数据，而到了3月10号，月数据则显示为2月份的数据，如需更改规则，请修改数据库表sys_updateData_rules

linux 安装redis注意事项：
可参考：https://www.cnblogs.com/codersay/p/4301677.html
http://www.runoob.com/redis/redis-install.html
其中：如果make 失败，需要执行命令：yum install gcc
参考：https://www.cnblogs.com/wyy123/p/6141236.html
或者：make MALLOC=libc

特别注意：
1.财务管理模块，全部数据中，每月的数据都为累计算法，即当月实际数据=当月填写累加数据-上月填写累加数据，计算全年数据，直接获取当前时间月份数据或者12月份
2.工程能耗模块：遗留问题，已处理遗留问题，每月数据都为累加算法，即当月实际数据=当月填写累加数据-上月填写累加数据，计算全年数据，直接获取当前时间月份数据或者12月份