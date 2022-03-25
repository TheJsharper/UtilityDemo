package test.demo.multithreaded;

import java.util.stream.IntStream;

public class SumOfNumberRunnable {
	public static int[] numbers = IntStream.rangeClosed(0, 5000).toArray();
	public static int sum = 0;
	public static int sumVerification = IntStream.rangeClosed(0, 5000).sum();

	public static void main(String[] args) {
		Worker1 worker1 = new Worker1(numbers);

		Worker2 worker2 = new Worker2(numbers);

		Thread thread1 = new Thread(worker1);
		Thread thread2 = new Thread(worker2);

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
			System.out.println("Summ of 5000 integers in paralell is:" + sum);
			System.out.println("Summ of 5000 integers verification is:" + sumVerification);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void add(int toAdd) {
		sum = sum + toAdd;
	}

}

class Worker1 implements Runnable {

	private int[] numbers;
	private int sum = 0;

	Worker1(int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {
		for (int i = 0; i < numbers.length / 2; i++) {
			sum = sum + numbers[i];
		}

		SumOfNumberRunnable.add(sum);
	}

}

class Worker2 implements Runnable {
	private int[] numbers;
	private int sum = 0;

	Worker2(int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public void run() {
		for (int i = numbers.length / 2; i < numbers.length; i++) {
			sum = sum + numbers[i];

		}
		SumOfNumberRunnable.add(sum);
	}

}
