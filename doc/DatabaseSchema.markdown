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
| id(key)       | 编号                     | varchar(20)  | String       |      |
| title         | 标题                     | varchar(100) | String       |      |
| author        | 作者                     | varchar(30) | String       |      |
| buyer(u_id)   | 购买者(上传信息的用户)     | varchar(13)  | String       |      |
| buytime       | 购买时间(上传时间)        | Date         | java.sql.Date |      |
| whereis       | 在架上位置               | varchar(100) | String       |      |
| isonshelf     | 是否在架上               |              | Boolean      |      |
| isbn          |                          |              | String       | yes  |
| publisher     |                          |              |              | yes  |
|               |                          |              |              |      |

#### Document

属性名前缀"d_"

| Attributes    | description              | Type in SQL  | Type in JAVA | 可空 |
| ------------- | ------------------------ | ------------ | ------------ | ---- |
| id(key)       | 编号                     | varchar(20)  | String       |      |
| title         | 标题                     | varchar(100) | String       |      |
| author        | 作者                     | varchar(30)  | String       |      |
| uploaduser(u_id)  | 购买者(上传信息的用户)     | varchar(13)  | String       |      |
| uploadtime    | 购买时间(上传时间)        | Date         | java.sql.Date |      |
| filepath     | 文件路径 | varchar | String |    |
|  |  |  |  | |

#### BorrowBook

属性名前缀“bb_”

| Attributes   | description | Type in SQL | Type in JAVA  | 可空 |
| ------------ | ----------- | ----------- | ------------- | ---- |
| id(key)      | 编号        | varchar(20) | String        |      |
| uid(u_id)    |             |             |               |      |
| bid(b_id)    |             |             |               |      |
| borrowtime   |             | Date        | java.sql.Date |      |
| givebacktime |             | Date        | java.sql.Date |      |
| isdone       |             |             | Boolean       |      |

#### DownloadDocument

属性名前缀“dd_”

| Attributes   | description | Type in SQL | Type in JAVA | 可空 |
| ------------ | ----------- | ----------- | ------------ | ---- |
| id(key)      | 编号        | varchar(20) | String       |      |
| uid(u_id)    |             |             |              |      |
| did(d_id)    |             |             |              |      |
| downloadtime   |             | Date        | java.sql.Date |      |
