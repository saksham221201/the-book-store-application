This is the core java pathway assignment,
List<Book> allBooks = new ArrayList<>();
    	Configuration con = new Configuration().configure("hibernate.cfg.xml");
		
        con.addAnnotatedClass(Book.class);
        
        StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
        												.applySettings(con.getProperties());
        
        
        SessionFactory sf = con.buildSessionFactory(serviceRegistry.build());
        Session session = sf.openSession();
		try 
		{
           //*** Generate a query by using hql *****//			
			String hql = " from Book"; 
   			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			allBooks =query.list();
			
			
		} 
		catch (IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		return allBooks;
