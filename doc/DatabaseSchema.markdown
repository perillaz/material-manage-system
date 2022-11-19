### Tables:

#### Users

属性名前缀"u_"

| Attributes | description | Type in SQL | Type in JAVA | 可空 |
| ---------- | ----------- | ----------- | ------------ | ---- |
| id(key)    | 学号        | Varchar(13) | String       |      |
| name       | 用户名      | Varchar(30) | String       |      |																																																																																																																																																																																																																																																																																					
| password   | 密码        | Varchar(30) | String       |      |

#### Book

属性名前缀“b_”

| Attributes    | description              | Type in SQL  | Type in JAVA | 可空 |
| ------------- | ------------------------ | ------------ | ------------ | ---- |
| id(key)       | 书籍编号                     | BIGINT | Long |      |
| title         | 标题                     | varchar(100) | String       |      |
| author        | 作者                     | varchar(30) | String       |      |
| buyer(u_id)   | 书籍购买者(上传信息的用户) | varchar(13)  | String       |      |
| buytime       | 购买时间(上传时间)        | Date         | java.sql.Date |      |
| whereis       | 在架上位置               | varchar(100) | String       |      |
| isonshelf     | 是否在架上               |      Bool        | Boolean      |      |
| borrowtimes   | 借阅次数                  |   INTEGER     |      Int        |      |
| publishtime   | 出版时间                 |Date          | java.sql.Date  |  yes     |
| publisher     | 出版商                  |varchar(100)    | String       |    yes   |

#### Document

属性名前缀"d_"

| Attributes    | description              | Type in SQL  | Type in JAVA | 可空 |
| ------------- | ------------------------ | ------------ | ------------ | ---- |
| id(key)       | 文件编号                     | BIGINT       | Long          |      |
| title         | 标题                     | varchar(100) | String       |      |
| author        | 作者                     | varchar(30)  | String       |      |
| uploader(u_id)  | 购买者(上传信息的用户)     | varchar(13)  | String       |      |
| uploadtime    | 购买时间(上传时间)        | Date         | java.sql.Date |      |
| filepath     | 文件路径 | varchar(100) | String |    |
| downloadtimes | 下载次数                 |   INTEGER     |  Int      |      |


#### BorrowBook

属性名前缀“bb_”

| Attributes   | description | Type in SQL | Type in JAVA  | 可空 |
| ------------ | ----------- | ----------- | ------------- | ---- |
| id(key)      | 编号        | BIGINT      | Long          |      |
| uid(u_id)    | 借书用户id  | varchar(13)  | String      |      |
| bid(b_id)    | 被借书籍id    | BIGINT | Long |      |
| borrowtime   | 借出时间     | Date        | java.sql.Date |      |
| sendbacktime | 归还时间    | Date        | java.sql.Date | yes     |
| duetime  | 应归还时间   |Date        | java.sql.Date  |      |
| finished      | 是否归还     |Bool|Boolean       |       |

#### DownloadDocument

属性名前缀“dd_”

| Attributes   | description | Type in SQL | Type in JAVA | 可空 |
| ------------ | ----------- | ----------- | ------------ | ---- |
| id(key)      | 编号        | BIGINT      | Long          |      |
| uid(u_id)    | 下载用户id   | varchar(13) | String    |      |
| did(d_id)    | 论文id      |  Bigint  | Long |      |
| downloadtime | 下载时间   | Date        | java.sql.Date |      |
