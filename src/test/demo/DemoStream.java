package test.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DemoStream {

	public static void concatCollection() {

		Collection<String> collectionA = Arrays.asList("First Collection Item 1", "First Collection Item 2  ");
		Collection<String> collectionB = Arrays.asList("Secound Collection Item 1", "Secound Collection Item 2  ");

		Stream.concat(collectionA.stream(), collectionB.stream()).forEach((String item) -> System.out.println(item));
	}

	public static void combineCollectionByFlatMap() {
		Collection<String> collectionA = Arrays.asList("First Collection Item 1", "First Collection Item 2  ");
		Collection<String> collectionB = Arrays.asList("Secound Collection Item 1", "Secound Collection Item 2  ");
		Stream<String> combinedStream = Stream.of(collectionA, collectionB).flatMap((item1) -> {
			System.out.println(item1);
			return collectionA.stream();
		});
		Collection<String> collectionCombined = combinedStream.collect(Collectors.toList());
		collectionCombined.forEach((String item) -> System.out.println(item));
	}

	public static void sortableList() {
		System.out.println("Sort by Age");
		List<Student> students = getStudents();
		Collections.sort(students, (a, b) -> b.age - a.age);
		System.out.println("Sort descending by age of students collections");
		students.forEach(System.out::println);

		students.sort((a, b) -> a.age - b.age);
		System.out.println("Sort ascending by age of students collections");
		students.forEach(System.out::println);
	}

	public static void sortableListOfIterable() {
		System.out.println("Sort by Age");
		List<Student> students = new ArrayList<>();
		getIterableStudents().forEachRemaining(students::add);
		Collections.sort(students, (a, b) -> b.age - a.age);
		System.out.println("Sort descending by age of students collections");
		students.forEach(System.out::println);

		students.sort((a, b) -> a.age - b.age);
		System.out.println("Sort ascending by age of students collections");
		students.forEach(System.out::println);
	}

	@SuppressWarnings("unused")
	public static void sortableListOfStream() {

		System.out.println("Sort by Age");
		Iterator<Student> students = getIterableStudents();

		Spliterator<Student> s = Spliterators.spliteratorUnknownSize(students, 0);
		List<Student> ss = StreamSupport.stream(s, false).collect(Collectors.toList());
		ss.sort((a, b) -> b.age - a.age);
		System.out.println("Sort descending by age of students collections");
		ss.forEach(System.out::println);
		ss.sort((a, b) -> a.age - b.age);
		System.out.println("Sort ascending by age of students collections");
		ss.forEach(System.out::println);
	}
	public static void sortableListOfStreamByDouble() {
		
		System.out.println("Sort by Tall");
		Iterator<Student> students = getIterableStudents();
		
		Spliterator<Student> s = Spliterators.spliteratorUnknownSize(students, 0);
		List<Student> ss = StreamSupport.stream(s, false).collect(Collectors.toList());
		ss.sort((a, b) -> a.tall < b.tall? 1: -1);
		System.out.println("Sort descending by tall of students collections");
		ss.forEach(System.out::println);
		ss.sort((a, b) -> a.tall > b.tall? 1: -1);
		System.out.println("Sort ascending by tall of students collections");
		ss.forEach(System.out::println);
	}

	private static List<Student> getStudents() {
		return new ArrayList<Student>() {

			private static final long serialVersionUID = 1L;

			{
				add(new Student("Max", 35, 1.60));
				add(new Student("Bertha", 25, 1.55));
				add(new Student("Judith", 45, 1.98));
				add(new Student("Jake", 15, 2.14));
				add(new Student("Charles", 85, 1.97));
			}
		};
	}

	private static Iterator<Student> getIterableStudents() {
		return (new ArrayList<Student>() {

			private static final long serialVersionUID = 1L;

			{
				add(new Student("Max", 35, 1.60));
				add(new Student("Bertha", 25, 1.55));
				add(new Student("Judith", 45, 1.98));
				add(new Student("Jake", 15, 2.14));
				add(new Student("Charles", 85, 1.97));
			}
		}).iterator();
	}

	public static class Student {
		private String name;
		private int age;
		private double tall;

		public Student(String name, int age, double tall) {
			this.name = name;
			this.age = age;
			this.tall = tall;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		public double getTall() {
			return this.tall;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + ", tall=" + tall + "]";
		}

		

	}
}
