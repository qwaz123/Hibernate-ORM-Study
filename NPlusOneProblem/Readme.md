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


<script src="https://gist.github.com/readStudy/c33198daff00b1f56e752c03d7b4f400.js"></script>
The code use one SQL select to load USER entity, then, 
it iterates the user to get Country, it need an additional SELECT.