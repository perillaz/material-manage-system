### Tables:

#### Users

| Attributes | iskey | description | Datatype | Default value |
| ---------- | ----- | ----------- | -------- | ------------- |
| uid        |       |             |          | 递增          |
| name       |       |             |          |               |
| email      |       |             |          |               |
| password   |       |             |          |               |

#### Book

| Attributes | iskey | description | Datatype | Default value |
| ---------- | ----- | ----------- | -------- | ------------- |
| bid        |       |             |          |               |
| title      |       |             |          |               |
| buyer      |       |             |          |               |
| buytime    |       |             |          |               |
| isonshelf  |       |             |          |               |

#### Document

| Attributes | iskey | description | Datatype | Default value |
| ---------- | ----- | ----------- | -------- | ------------- |
| did        |       |             |          |               |
| title      |       |             |          |               |
| filepath   |       |             |          |               |
| uploadtime |       |             |          |               |
| uploaduser |       |             |          |               |

#### BorrowBook

| Attributes | iskey | description | Datatype | Default value |
| ---------- | ----- | ----------- | -------- | ------------- |
| id         |       |             |          |               |
| uid        |       |             |          |               |
| bid        |       |             |          |               |

#### DownloadDocument

| Attributes | iskey | description | Datatype | Default value |
| ---------- | ----- | ----------- | -------- | ------------- |
| uid        |       |             |          |               |
| bid        |       |             |          |               |
| time       |       |             |          |               |
