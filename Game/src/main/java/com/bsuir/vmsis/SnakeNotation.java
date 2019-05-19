package com.bsuir.vmsis;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class SnakeNotation {

	private List<FileSystem> listSnakeNotations = new ArrayList<FileSystem>();

	static {
		initList();
	}

	private static void initList() {

		List<FileSystem> listSnakeNotations = new ArrayList<FileSystem>();
	}

	public FileSystem addNotation(FileSystem notation) {

		listSnakeNotations.add(notation);
		return notation;
	}

	public List<FileSystem> getAllNotation() {

		return listSnakeNotations;
	}

	public void sortListNotations() {
		listSnakeNotations = FPQuickSort(listSnakeNotations);
	}

	private List<FileSystem> FPQuickSort(List<FileSystem> elements) {

		if (elements.size() == 0)
			return elements;

		FileSystem baseElement = elements.get(0);

		List<FileSystem> lesser = elements.stream().filter(x -> x.compareTo(baseElement) < 0)
				.collect(Collectors.toList());

		List<FileSystem> greater = elements.stream().filter(x -> x.compareTo(baseElement) > 0)
				.collect(Collectors.toList());

		List<FileSystem> sorted = new ArrayList<FileSystem>();
		sorted.addAll(FPQuickSort(lesser));
		sorted.add(baseElement);
		sorted.addAll(FPQuickSort(greater));

		return sorted;
	}
}
