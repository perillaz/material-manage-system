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
INSERT INTO Book(b_id,b_title,b_author,b_buyer,b_buytime,b_whereis,b_isonshelf,b_borrowtimes,b_publishtime,b_publisher) VALUES(?,?,?,?,?,?,?,?,?,?)
```

查找图书

By ID
```sql
SELECT * FROM Book WHERE b_id = ?
```

By Title
```sql
SELECT * FROM Book WHERE b_title LIKE %?%
```

By Author
```sql
SELECT * FROM Book WHERE b_author LIKE %?%
```

By Title Or Author
```sql
SELECT * FROM Book WHERE b_title LIKE %?% OR b_author LIKE %?%
```

Get ALl Books
```sql
SELECT * FROM Book
```

修改图书信息

Change Title
```sql
UPDATA Book SET b_title = ? WHERE b_id = ? 
```

Change Author
```sql
UPDATA Book SET b_author = ? WHERE b_id = ? 
```

Change Buyer
```sql
UPDATA Book SET b_buyer = ? WHERE b_id = ?
```

Chnage Buytime
```sql
UPDATA Book SET b_buytime = ? WHERE b_id = ? 
```

Change Whereis
```sql
UPDATA Book SET b_whereis = ? WHERE b_id = ? 
```

Change Isonshelf
```sql
UPDATA Book SET b_isonshelf = ? WHERE b_id = ? 
```

### DocumentService

添加文档信息
```sql
INSERT INTO Document(d_id,d_title,d_author,d_uploader,d_uploadtime,d_filepath,d_downloadtimes) VALUES(?,?,?,?,?,?,?)
```

查找文档
By Id
```sql
SELECT * FROM Document WHERE d_id = ?
```

By Title
```sql
SELECT * FROM Document WHERE d_title LIKE %?%
```

By Author
```sql
SELECT * FROM Document WHERE d_author = ?
```

Get All Documents
```sql
SELECT * FROM Document
```
Change 
```sql

```
修改文档

Change Title
```sql
UPDATA Document SET d_title = ? WHERE d_id = ? 
```

Change Author
```sql
UPDATA Document SET d_author = ? WHERE d_id = ? 
```

Change Uploader
```sql
UPDATA Document SET d_uploader = ? WHERE d_id = ? 
```

Change Filepath
```sql
UPDATA Document SET d_filepath = ? WHERE d_id = ? 
```

Change Downloadtimes
```sql
UPDATA Document SET d_downloadtimes = ? WHERE d_id = ? 
```