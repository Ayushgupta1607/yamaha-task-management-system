package com.taskmanagementapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementApplication implements CommandLineRunner {
//	@Autowired
//    private JdbcTemplate jdbcTemplate;
//	
//	 @Autowired
//	    private EntityManagerFactory entityManagerFactory;
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		EntityManager session = entityManagerFactory.createEntityManager();
//		
//		System.out.println(session.createNativeQuery("Select * from employee").getSingleResult());
	}
	           
	
	
         
}

//Using generated security password: 20bbe0c7-a4d9-4208-9f87-c91687e673fc
