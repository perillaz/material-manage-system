### UserService

添加用户(id,name,password)

```sql
INSERT INTO User(u_id,u_name,u_password) VALUES(?,?,?)
```

查找用户(id)

```sql
SELECT * FROM User WHERE u_id = ?
```

改用户密码(password,id)

```sql
UPDATE User SET u_password = ? WHERE u_id = ?
```

改用户名(name,id)

```sql
UPDATE User SET u_name = ? WHERE u_id = ? 
```

### BookService

添加图书

```sql
INSERT INTO Book(title,author,copyamount,borrowedcopys,allborrowtimes,isbn,edition,publishtime,publisher,lang,briefinfo) VALUES(?,?,?,?,?,?,?,?,?,?,?)
```

查找图书

By ID
```sql
SELECT * FROM Book WHERE b_id = ?
```

By Title
```sql
SELECT * FROM Book WHERE title LIKE CONCAT('%',?,'%') ORDER BY allborrowtimes DESC
```

By Author
```sql
SELECT * FROM Book WHERE author LIKE CONCAT('%',?,'%') ORDER BY allborrowtimes DESC
```

By Title Or Author
```sql
SELECT * FROM Book WHERE title LIKE CONCAT('%',?,'%') OR author LIKE CONCAT('%',?,'%') ORDER BY allborrowtimes DESC
```

Get ALl Books
```sql
SELECT * FROM Book
```

修改图书信息

Change 'attribute'
```sql
UPDATA Book SET attribute = ? WHERE id = ? 
```

### CopyService

添加图书副本
```sql
INSERT INTO Copy(bid,btitle,loc,buyerid,buyername,buytime,borrowtimes,canbeborrow,canbereserve,reserver) VALUES(?,?,?,?,?,?,?,?,?,?)
```

删除图书副本
```sql
DELETE FROM Copy WHERE id = ?
```

查询副本

By id
```sql
SELECT * FROM COPY WHERE id=?
```

By bid
```sql
SELECT * FROM Copy WHERE bid = ?
```

By buyer
```sql
SELECT * FROM Copy WHERE buyerid = ?
```

修改副本信息
```sql
UPDATE Copy SET attribute = ? WHERE id = ? 
```

添加借阅记录
```sql
INSERT INTO BorrowCopy(uid,cid,bid,btitle,borrowtime,sendbacktime,duetime,finished) VALUES(?,?,?,?,?,?,?,?)
```

查询借阅记录

By id
```sql
SELECT * FROM BorrowCopy WHERE id =?
```

查询用户未归还副本
```sql
SELECT * FROM BorrowCopy WHERE uid = ? AND  finished = false
```

查询用户已归还副本
```sql
SELECT * FROM BorrowCopy WHERE uid = ? AND  finished = true
```

修改借阅记录信息
```sql
UPDATE BorrowCopy SET ttribute = ? WHERE id = ? 
```

### DocumentService

添加文档信息
```sql
INSERT INTO Document(title,author,uploaderid,uploadername,uploadtime,filepath,downloadtimes,doi,literature) VALUES(?,?,?,?,?,?,?,?,?)
```

删除文档
```sql
DELETE FROM Document WHERE id = ?
```

查找文档
By Id
```sql
SELECT * FROM Document WHERE id = ?
```

By Title
```sql
SELECT * FROM Document WHERE title LIKE CONCAT('%',?,'%') ORDER BY downloadtimes DESC
```

By Author
```sql
SELECT * FROM Document WHERE author LIKE CONCAT('%',?,'%') ORDER BY downloadtimes DESC
```

By TitleOrAuthor
```sql
SELECT * FROM Document WHERE title LIKE CONCAT('%',?,'%') OR author LIKE CONCAT('%',?,'%') ORDER BY downloadtimes DESC
```

By Uploaderid
```sql
SELECT * FROM Document WHERE uploaderid = ?
```

Get All Documents
```sql
SELECT * FROM Document
```

修改文档

Change attributes

```sql
UPDATA Document SET attribute = ? WHERE id = ? 
```
#### DownloadDocument

添加下载记录
```sql
INSERT INTO DownloadDocument(uid,did,dtitle,downloadtime) VALUES(?,?,?,?)
```
查询下载记录

By id
```sql
SELECT * FROM DownloadDocument WHERE uid = ?
```

### StatisticService

UserStatistic
```sql
SELECT * FROM UserStatistics
```

WeekBorrowRank
借阅次数排行前10的书
```sql
SELECT bid AS bookid,btitle AS booktitle, COUNT(*) AS borrowtimes FROM BorrowCopy WHERE borrowtime >= ? GROUP BY bid ORDER BY borrowtimes DESC LIMIT 10
```

WeekDownloadRank
下载次数排行前10的文档
```sql
SELECT did AS documentid,dtitle AS documenttitle, COUNT(*) AS downloadtimes FROM DownloadDocument WHERE downloadtime >= ? GROUP BY did ORDER BY downloadtimes DESC LIMIT 10
```