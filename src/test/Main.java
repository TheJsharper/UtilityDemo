package test;

import test.demo.streams.DemoStream;

public class Main {

	public static void main(String[] args) {
		DemoStream.concatCollection();
		DemoStream.combineCollectionByFlatMap();
		DemoStream.sortableList();
		DemoStream.sortableListOfIterable();
		DemoStream.sortableListOfStream();
		DemoStream.sortableListOfStreamByDouble();
	}

}
