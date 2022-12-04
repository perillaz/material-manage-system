
#VIEW

#### UserStatistics

```sql
CREATE VIEW UserStatistics(userid,username,uploadtimes,addcopytimes) AS 
    WITH dcounts AS (SELECT uploaderid AS id, COUNT(*) AS dcount FROM document GROUP BY uploaderid), 
         ccounts AS (SELECT buyerid AS id, COUNT(*) AS ccount FROM copy GROUP BY buyerid) 
    SELECT id AS userid,name AS username,dcount as uploadtimes,ccount AS addcopytimes 
    FROM User NATURAL LEFT JOIN ccounts NATURAL LEFT JOIN dcounts 
    WHERE ccount IS NOT NULL  or dcount IS NOT NULL
```

#TRIGGER


### UserNameSynchronization

```sql
CREATE TRIGGER UserNameSynchronization 
    AFTER UPDATE ON User 
    FOR EACH ROW 
        IF NEW.name<>OLD.name 
        THEN BEGIN 
            UPDATE Copy SET buyername = NEW.name WHERE buyerid = NEW.id;
            UPDATE Document SET uploadername = NEW.name WHERE uploaderid = NEW.id;
        END ;
        END IF
```

### BookTitleSynchronization

```sql
CREATE TRIGGER BookTitleSynchronization 
    AFTER UPDATE ON Book 
    FOR EACH ROW 
        IF NEW.title<>OLD.title 
            THEN BEGIN 
                UPDATE Copy SET btitle = NEW.title WHERE bid = NEW.id;
                UPDATE BorrowCopy SET btitle = NEW.title WHERE bid = NEW.id;
            END ;
        END IF
```

### DocumentTitleSynchronization

```sql
CREATE TRIGGER DocumentTitleSynchronization 
    AFTER UPDATE ON Document 
    FOR EACH ROW 
        IF NEW.title<>OLD.title 
        THEN 
            UPDATE DownloadDocument SET dtitle = NEW.title WHERE did = NEW.id;
        END IF
```
### BorrowCopyCount

```sql
CREATE TRIGGER BorrowCopyCount 
    AFTER INSERT ON BorrowCopy 
    FOR EACH ROW 
    BEGIN 
        UPDATE Book SET allborrowtimes = allborrowtimes + 1 WHERE id = NEW.bid; 
        UPDATE Copy SET borrowtimes = borrowtimes + 1 WHERE id = NEW.cid; 
    END
```

### DownloadDocumentCount

```sql
CREATE TRIGGER DownloadDocumentCount 
    AFTER INSERT ON DownloadDocument
    FOR EACH ROW 
        UPDATE Document SET downloadtimes = downloadtimes + 1 WHERE id = NEW.did
```

### DeleteBookTrigger

```sql
CREATE TRIGGER DeleteBookTrigger 
    AFTER DELETE ON Book
    FOR EACH ROW 
        DELETE FROM Copy WHERE bid = OLD.id
```

###  BorrowCopyTrigger

```sql
CREATE TRIGGER BorrowCopyTrigger 
    AFTER INSERT ON BorrowCopy 
    FOR EACH ROW 
    UPDATE Book SET borrowedcopys = borrowedcopys + 1 WHERE id = NEW.bid
```

### ReturnCopyTrigger

```sql
CREATE TRIGGER ReturnCopyTrigger 
    AFTER UPDATE ON BorrowCopy 
    FOR EACH ROW 
        IF NEW.finished = TRUE AND OLD.finished = FALSE 
            THEN UPDATE Book 
            SET borrowedcopys = borrowedcopys - 1 WHERE id = NEW.bid; 
        END IF
```

### AddCopyTrigger

```sql
CREATE TRIGGER AddCopyTrigger 
    AFTER INSERT ON Copy 
    FOR EACH ROW 
        UPDATE Book SET copyamount = copyamount + 1 WHERE id = NEW.bid
```
