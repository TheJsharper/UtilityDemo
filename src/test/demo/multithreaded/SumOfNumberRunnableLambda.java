package test.demo.multithreaded;

import java.util.stream.IntStream;

public class SumOfNumberRunnableLambda {
	public static int[] numbers = IntStream.rangeClosed(0, 5000).toArray();
	public static int sum = 0;
	public static int sumVerification = IntStream.rangeClosed(0, 5000).sum();

	public static void main(String[] args) {

		Thread thread1 = new Thread(() -> {
			int sum = 0;
			for (int i = 0; i < numbers.length / 2; i++) {
				sum = sum + numbers[i];
			}
			add(sum);
		});
		Thread thread2 = new Thread(() -> {
			int sum = 0;
			for (int i = numbers.length / 2; i < numbers.length; i++) {
				sum = sum + numbers[i];
			}
			add(sum);
		});
		thread1.start();
		thread2.start();

		try {
			thread2.join();
			thread1.join();
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
