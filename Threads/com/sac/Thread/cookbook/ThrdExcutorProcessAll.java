package com.sac.Thread.cookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class ThreadExecutorResult {

	private String name;
	private int value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {

		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

class TaskProcessAll implements Callable<ThreadExecutorResult> {
	private String name;

	public TaskProcessAll(String name) {
		this.name = name;
	}

	@Override
	public ThreadExecutorResult call() throws Exception {
		System.out.printf("%s: Staring\n", this.name);

		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results.\n", this.name,
					duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int value = 0;
		for (int i = 0; i < 5; i++) {
			value += (int) (Math.random() * 100);
		}

		ThreadExecutorResult result = new ThreadExecutorResult();
		result.setName(this.name);
		result.setValue(value);
		System.out.println(this.name + ": Ends");

		return result;
	}
}

public class ThrdExcutorProcessAll {
	public static void main(String[] args) {

		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		List<TaskProcessAll> taskList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			TaskProcessAll task = new TaskProcessAll("Thread" + i);
			taskList.add(task);
		}

		List<Future<ThreadExecutorResult>> resultList = null;
		try {
			resultList = executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();

		System.out.println("Main: Printing the results");
		for (int i = 0; i < resultList.size(); i++) {
			Future<ThreadExecutorResult> future = resultList.get(i);
			try {
				ThreadExecutorResult result = future.get();
				System.out.println(result.getName() + ": " + result.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
