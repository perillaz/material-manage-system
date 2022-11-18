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

```
修改图书信息
```sql
```



### DocumentService

添加文档信息

```sql

```

查找文档
```sql

```

