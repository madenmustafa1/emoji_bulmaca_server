# Emoji Bulmaca Server Side
 ###
---

```sh
-> http://localhost:7070/sign-in

-> { username: username, password: password, key: key }

<- {
    "token": "token",
   }

```
 ###
```sh
-> http://localhost:7070/get-song

-> { index: index, categoryId: categoryId }

<- {
    "answer": "answer",
    "singer": "singer",
    "index": index,
    "image": "image",
    "contentOwner": "contentOwner",
    "categoryId": categoryId
   }

```
 ###
```sh
-> http://localhost:7070/category

<- [
    {
    "answer": "answer",
    "singer": "singer",
    "index": index,
    "image": "image",
    "contentOwner": "contentOwner",
    "categoryId": categoryId
   },
  ]
```


```sh
-> http://localhost:7070/add-emoji  -- User request

-> { songName: songName }

<- {
    boolean
   }

```
