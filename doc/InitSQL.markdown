### Tables

#### User

```sql
CREATE TABLE IF NOT EXISTS User(
    u_id VARCHAR(13) PRIMARY KEY,
    u_name VARCHAR(30) NOT NULL,
    u_password VARCHAR(30) NOT NULL
)
```

#### Book
```sql
CREATE TABLE IF NOT EXISTS Book(
    b_id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    b_title VARCHAR(100) NOT NULL,
    b_author VARCHAR(100) NOT NULL,
    b_buyer VARCHAR(13) NOT NULL,
    b_whereis VARCHAR(100) NOT NULL,
    b_isonshelf BOOLEAN NOT NULL,
    b_borrowtimes INTEGER NOT NULL,
    b_publishtime Date,
    b_publisher VARCHAR(100)
)
```

#### Document

```sql
CREATE TABLE IF NOT EXISTS Document(
    d_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    d_title VARCHAR(30) NOT NULL,
    d_author VARCHAR(30) NOT NULL,
    d_uploaduser VARCHAR(13) NOT NULL,
    d_uploadtime DATE NOT NULL,
    d_filepath VARCHAR(100) NOT NULL,
    d_downloadtimes INTEGER NOT NULL
)

```

#### BorrowBook

```sql
CREATE TABLE IF NOT EXISTS BorrowBook(
    bb_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bb_uid VARCHAR(20) NOT NULL,
    bb_bid BIGINT NOT NULL,
    bb_borrowtime DATE NOT NULL,
    bb_sendbacktime DATE NOT NULL,
    bb_duetime DATE NOT NULL,
    bb_finished BOOLEAN NOT NULL
)
```

#### DownloadDocument

```sql
CREATE TABLE IF NOT EXISTS DownloadDocument(
    dd_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dd_uid VARCHAR(13) NOT NULL,
    dd_did BIGINT NOT NULL,
    dd_downloadtime DATE NOT NULL
)
```