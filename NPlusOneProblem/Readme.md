N+1 SELECT problem

IMAGE             |            
------------------| 
ID\<\<PK\>\>      |
USER_ID\<\<FK\>\> |
TYPE              | 


USER                |
--------------------|
ID \<\<PK\>\>       |
COUNTRYID \<\<FK\>\>|
USERNAME            |

COUNTRY      |
-------------|
ID \<\<PK\>\>| 
NAME         |
CAPITAL      |



```java
List<User> users = em.createQuery("select u from User u").getResultList();
// select * from USER  ---------- (1)
for (User user : users) {
  System.out.println(user.getCountry().getName());
  // select * from COUNTRY where ID = ? ----------- (2)
}
```
The code use one SQL select to load USER entity, then, 
it iterates the user to get Country, it need an additional SELECT.