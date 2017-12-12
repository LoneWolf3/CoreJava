package com.sac.Thread.cookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class UserValidator {

	private String name;

	public UserValidator(String name) {
		this.name = name;
	}

	public boolean validate(String name, String password) {

		Random random = new Random();

		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("Validator %s: Validating a user during %d seconds\n",
					this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			return false;
		}

		return random.nextBoolean();
	}

	public String getName() {
		return name;
	}
}

class TaskValidator implements Callable<String> {

	private UserValidator validator;

	private String user;
	private String password;

	public TaskValidator(UserValidator validator, String user, String password) {
		this.validator = validator;
		this.user = user;
		this.password = password;
	}

	@Override
	public String call() throws Exception {

		if (!validator.validate(user, password)) {
			System.out.printf("%s: The user has not been found\n", validator.getName());
			throw new Exception("Error validating user");
		}

		System.out.printf("%s: The user has been found\n", validator.getName());
		return validator.getName();
	}
}

public class ThrdExecutorProcessFirstFuture {
	public static void main(String[] args) {

		String username = "test";
		String password = "test";

		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("DataBase");

		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);

		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		String result;

		try {
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n", result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");
	}
}