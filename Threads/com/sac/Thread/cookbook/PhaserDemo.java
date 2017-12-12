package com.sac.Thread.cookbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

class FileSearch implements Runnable {

	private String initPath;

	private String end;

	private List<String> results;

	private Phaser phaser;

	public FileSearch(String initPath, String end, Phaser phaser) {
		this.initPath = initPath;
		this.end = end;
		this.phaser = phaser;
		results = new ArrayList<String>();
	}

	private void directoryProcess(File file) {
		File list[] = file.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {

				if (list[i].isDirectory()) {
					directoryProcess(list[i]);
				} else {
					fileProcess(list[i]);
				}
			}
		}
	}

	private void fileProcess(File file) {
		if (file.getName().endsWith(end)) {
			results.add(file.getAbsolutePath());
		}
	}

	private void filterResults() {
		List<String> newResults = new ArrayList<String>();
		long actualDate = new Date().getTime();

		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));
			long fileDate = file.lastModified();

			if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
				newResults.add(results.get(i));
			}
		}
		results = newResults;
	}

	private boolean checkResults() {

		if (results.isEmpty()) {
			System.out.println("Thread = "+Thread.currentThread().getName() + ", Phase = "
					+ phaser.getPhase() + ", Result = " + results.size());
			System.out.println("Thread = "+Thread.currentThread().getName() + ", Phase = "
					+ phaser.getPhase() + ", Result = " + results.size()+" End.");
			phaser.arriveAndDeregister();
			return false;

		} else {
			System.out.println("Thread = "+Thread.currentThread().getName() + ", Phase = "
					+ phaser.getPhase() + ", Result = " + results.size());
			phaser.arriveAndAwaitAdvance();
			return true;
		}
	}

	private void showInfo() {
		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));
			System.out.printf("%s: %s\n", Thread.currentThread().getName(),
					file.getAbsolutePath());
		}
		phaser.arriveAndAwaitAdvance();
	}

	@Override
	public void run() {
		phaser.arriveAndAwaitAdvance();

		System.out.println("Starting " + Thread.currentThread().getName());

		File file = new File(initPath);
		if (file.isDirectory()) {
			directoryProcess(file);
		}

		if (!checkResults()) {
			return;
		}
		filterResults();
		if (!checkResults()) {
			return;
		}

		showInfo();
		phaser.arriveAndDeregister();
		System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
	}
}

public class PhaserDemo {
	public static void main(String[] args) {

		Phaser phaser = new Phaser(3);

		FileSearch d1 = new FileSearch("C:\\d1", "log", phaser);
		FileSearch d2 = new FileSearch("C:\\d2", "log", phaser);
		FileSearch d3 = new FileSearch("C:\\d3", "log", phaser);

		Thread systemThread = new Thread(d1, "d1");
		systemThread.start();
		Thread appsThread = new Thread(d2, "d2");
		appsThread.start();
		Thread documentsThread = new Thread(d3, "d3");
		documentsThread.start();
		try {
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Terminated: " + phaser.isTerminated());
	}
}