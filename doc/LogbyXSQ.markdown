# 22-11-15

成功连接上docker里的opengauss，开始看java

# 22-11-16下午

java速成，看的东西如下

    |----计院java课程的ppt
    |   |---Lecture 1 Java Basic
    |   |---Lecturn 3 Spring Core
    |
    |---- [廖雪峰廖雪峰java教程](https://www.liaoxuefeng.com/wiki/1252599548343744)
        |---- 面向对象教程
        |   |----面向对象基础    
        |   |----java核心类
        |       |--javabean
        |    
        |----注解
        |----单元测试
        |----Maven基础
        |----网络编程
        |   |----网络编程基础
        |   |----TCP编程
        |   |----HTTP编程
        |
        |----XML与JSON
        |----JDBC编程
        |----Web开发
        |----Spring开发

# 22-11-16晚上，22-11-17下午

把工程的文件结构理清了

边看教程边试，把需要用的东西和设置写好了，能成功运行并通过浏览器访问

# 22-11-17晚上

html速成
    [HTML 教程 | 菜鸟教程](https://www.runoob.com/html/html-tutorial.html)

写了一点接口

# 22-11-18中午

接口+一点功能实现示例

# 22-11-18晚上

改正一些bug

user相关所有功能正常使用

实现注册，登陆相关的网页的基础功能和跳转

# 22-11-19

test模块，未完成

# 22-11-20

一些测试，修bug

[pebbble引擎模板语法](https://pebbletemplates.io/wiki/guide/basic-usage/)

map(string,List\<Book\>)需要Book类存在一个无参数的构造函数

# 22-11-21

修bugs

关于BeanPropertyRowMapper的使用要求

- 类与数据库中表的属性名称对应
- 类中的所有属性有set方法（“set” + 属性名，不能是Set）
- 
否则对应的属性值设为null

MYSQL中用CONCAT拼接字符串
例：
"SELECT * FROM Document WHERE title LIKE CONCAT(\'%\',?,\'%\')"
解决jdbcTemplate无法识别引号包裹的“?”的问题

# 22-11-22

修bugs

增加一些小功能

配置测试类

Polardb和Mysql8.0可以互换，openGauss不行


# 22-11-23

第一阶段初步完成，写第二阶段TODO list；

配置依赖：
[如何用注解把XML配置文件转成Java配置类](https://blog.csdn.net/u013586483/article/details/109761709)

文件的上传功能完成:使用 MultipartFile

文件存放在项目目录下的files文件夹中

# 22-11-24

将修改属性的功能合并：使用java的反射

# 22-11-25

根据第二阶段的数据库schema写初始化语句和触发器

# 22-11-26

实现文档下载

增加重定向以解决刷新上传成功界面会导致重复上传文件问题：
在刷新界面时，浏览器会向web服务器重新发送一遍post或get请求

批量从文件中导入数据到数据库

[Java 逐行读取文本文件的几种方式以及效率对比](https://blog.diqigan.cn/posts/java-read-file-by-line.html)

使用批处理时要在url后加入参数："?rewriteBatchedStatements=true"

使用本地mysql与batch

读入数据时间  :0.64s
行数 : 10000
程序运行时间  :1.176s

读入数据时间  :0.573s
行数 : 10000
程序运行时间  :1.312s

读入数据时间  :0.212s
行数 : 1000
程序运行时间  :0.303s

读入数据时间  :1.353s
行数 : 100000
程序运行时间  :7.163s

读入数据时间  :13.987s
行数 : 1200000
程序运行时间  :93.593s

polardb:

读入数据时间  :0.5s
行数 : 10000
程序运行时间  :0.852s

读入数据时间  :1.378s
行数 : 100000
程序运行时间  :5.502s

读入数据时间  :13.285s
行数 : 1200000
程序运行时间  :109.557s


读入测试：
getDocumentsByapacheCommonsIO()

读入数据时间  :0.294s
行数 : 1000

读入数据时间  :0.508s
行数 : 10000

读入数据时间  :13.024s
行数 : 1200000

晚上20：24
Book
读入数据时间  :0.572s
行数 : 10000
程序运行时间  :0.946s


# 22-11-27

增加上传图书功能

增加通过id查找功能