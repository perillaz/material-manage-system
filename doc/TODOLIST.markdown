- entity层：
    完善各个类
  - bid -> booktitle
  - 改变booktitle 改变对应所有copy的booktitle

- service层
  - 借阅相关//z
    - 预约时
    - 借阅时
    - 归还时
  - 下载文档//x
    -（需要与controller层，html同时考虑）
  - 用户权献相关//g
  - user:
    - isstudent
    - isadmin
    - 设置学生
    - 设置管理员
    - 初始管理员账号（DBINIT）
    - 修改用户权限
      - 判断
  - 批量从文件导入信息//x
    - 使用预编译

- 统计信息
  - book:借阅次数
  - book:copy数量
  - book:可借阅copy数（既没被借阅也没被预约）//触发器
  - document：下载次数
  - user：借阅记录 
  - user: 下载记录
  - user: buycopy记录
  - user: 上传记录
  - 其它：参考校图书馆报告
    - 

- 测试

- controller层


- 界面
  - search:
    - searchby增加id(改接口？)
    - 查找：
    - id title author 可用copy数/总copy数 借阅记录
    - id title author 下载次数
  - userdatial
    - 
  - 新增管理员相关界面
    - 如果可以设置学生
      - 相关的
    - 如果可以设置管理员
  - bookdetial
    - 借阅预约
    - 