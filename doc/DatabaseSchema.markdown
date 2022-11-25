### Tables:

#### Users

| Attributes | description      | Type in SQL | Type in JAVA |           |
| ---------- | ---------------- | ----------- | ------------ | --------- |
| id(key)    | 账号             | Varchar(13) | String       | 学号      |
| name       | 用户名           | Varchar(30) | String       |           |
| password   | 密码             | Varchar(30) | String       |           |
| usertype   | 用户             | Varchar(10) | String       | "visiter" |
| setadmini  | 能否设置新管理员 | BOOLEAN     | Boolean      | false     |
| setstudent | 能否设置新学生   | BOOLEAN     | Boolean      | false     |

#### Book

| Attributes    | description              | Type in SQL  | Type in JAVA |  |
| ------------- | ------------------------ | ------------ | ------------ | ---- |
| id(key)       | 书籍编号                     | BIGINT | Long |      |
| title         | 标题                     | VARCHAR(50) | String       |      |
| author        | 作者                     | VARCHAR(50) | String       |      |
| copyamount     | 单册数量    | INTEGER       | Integer       |      |
| borrowedcopys  |             | INTEGER       | Integer       |      |
| allborrowtimes | 总借阅次数  | INTEGER       | Integer       |      |
| isbn           | ISBN        | VARCHAR(13)   | String        |      |
| edition        | 版本        | VARCHAR(30)  | String        |      |
| publishtime | 出版时间 |DATE | java.sql.Date | 可空 |
| publisher | 出版商 |VARCHAR(30) | String | 可空 |
| briedinfo | 简介 |VARCHAR(1000) | String | 可空 |

#### Copy

| Attributes    | description              | Type in SQL  | Type in JAVA |  |
| ------------- | ------------------------ | ------------ | ------------ | ---- |
| id(key)       | 编号               | BIGINT | Long |      |
| bid | 书籍编号 | BIGINT | Long | |
| btitle | 书籍名称 | VARCHAR(50) | String        | |
| loc | 在架上位置 | varchar(50) | String | |
| isborrowed | 是否被借走 | BOOLEAN | Boolean | |
| isreserved | 是否被预约 | BOOLEAN | Boolean | |
| buyerid(u_id) | 单册购买者id(上传信息的用户) | VARCHAR(13) | String       |      |
| buyername | 单册购买者姓名 | VARCHAR(30) | String | |
| buytime       | 购买时间(上传时间)        | Date         | java.sql.Date |      |
| borrowtimes   | 借阅次数                  |   INTEGER     |      Integer |      |

#### Document

| Attributes    | description              | Type in SQL  | Type in JAVA |  |
| ------------- | ------------------------ | ------------ | ------------ | ---- |
| id(key)       | 文件编号                     | BIGINT       | Long          |      |
| title         | 标题                     | VARCHAR(50)  | String        |      |
| author        | 作者                     | varchar(50) | String       |      |
| uploaderid(u_id) | 上传id(上传信息的用户) | varchar(13)  | String       |      |
| uploadertitle | 上传姓名 | VARCHAR(30) | String | |
| uploadtime    | 上传时间        | Date         | java.sql.Date |      |
| filepath     | 文件路径 | varchar(100) | String |    |
| downloadtimes | 下载次数                 |   INTEGER     |  Integer  | 0 |
| doi | doi | VARCHAR(20) | String | 可空 |
| literature | 期刊 | VARCHAR(30) | String | 可空 |

#### BorrowCopy

| Attributes   | description | Type in SQL | Type in JAVA  |  |
| ------------ | ----------- | ----------- | ------------- | ---- |
| id(key)      | 编号        | BIGINT      | Long          |      |
| uid | 借书用户id  | varchar(13)  | String      |      |
| cid | 被借副本id | BIGINT | Long |      |
| bid | 被借书籍id | BIGINT | Long | |
| btitle | 被借书籍名称 | VARCHAR(50) | String        | |
| borrowtime   | 借出时间     | Date        | java.sql.Date |      |
| sendbacktime | 归还时间    | Date        | java.sql.Date |  |
| duetime  | 应归还时间   |Date        | java.sql.Date  |  |
| finished      | 是否归还     |BOOLEAN|Boolean       |  |

#### DownloadDocument

| Attributes   | description | Type in SQL | Type in JAVA |  |
| ------------ | ----------- | ----------- | ------------ | ---- |
| id(key)      | 编号        | BIGINT      | Long          |      |
| uid    | 下载用户id   | varchar(13) | String    |      |
| did    | 论文id      |  Bigint  | Long |      |
| dtitle | 论文标题 | VARCHAR(50) | String        | |
| downloadtime | 下载时间   | Date        | java.sql.Date |      |
