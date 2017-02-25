<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type"
              content="text/html; charset=UTF-8">
        <title>Solution</title>
    </head>
    <body>
    
    <pre>
    	Solution 1:  Change to FetchType.EAGER
    		If you know you will access all the user in the imgs,
    		you may change the fetch type to EAGER.
    		But this will be leaded another problem, 
    		Cartesian product problem.	    	
    </pre>
    <pre>
    	Solution 2: Pre-fetching data
    		
			<strong>Batch Fetching: A  blind-guess optimization way.</strong>
				When Hibernate initialize a User proxy, 
				it will initialize several with same SELECT.
				Example:
					@Entity
					@org.hibernate.annotations.BatchSize(size = 5)
					@Table(name = "USER")
					public class User {
					    // ...
					}
					Hibernate will load up to 5 User proxies in a same SELECT.
						
			<strong>Prefetching collections with subselects:</strong>
				For now, only for lazy collections, not for entity proxies.
				Change  Entity class to: 
					@Entity
					public class User {
					    @OneToMany(mappedBy = "user")
					    @org.hibernate.annotations.Fetch(
					       org.hibernate.annotations.FetchMode.SUBSELECT
					    )
					    protected List&lt;Image&gt; images;
					    // ...
					}
			<strong>Dynamic eager fetching:</strong>
				Example code:
					List&lt;User&gt; users =
					    em.createQuery("select u from User u <strong>join fetch</strong> u.country")
					        .getResultList();
					em.close();  // --------> Detach all
					for (User user : users) {
					    assertNotNull(user.getCountry().getName());
					}		
				The join fetch in JPQL query, tells Hibernate 
				to use a SQL JOIN (an INNER JOIN, actually) 
				to retrieve the country of each User in the same query.
				
				Also work for collection:
					List&lt;User&gt; users =
					    em.createQuery("select u from User u <strong>left join fetch</strong> u.images")
					        .getResultList();
					em.close(); // --------> Detach all
					for (User user : users) {
					    System.out.println(user.getImages().size());
					}
				<strong>Note:</strong>
				  For collection fetching, a LEFT  OUTER  JOIN is necessary,
				  because you also want rows from the ITEM table if there are no bids		
    </pre>    
 
	<a href="${pageContext.servletContext.contextPath}/index">back to index</a>
    
    </body>
</html>