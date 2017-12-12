package com.sac.network.programing.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

class SearchGlobal implements FileVisitor {
	private final PathMatcher matcher;

	public SearchGlobal(String glob) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
	}

	void search(Path file) throws IOException {
		Path name = file.getFileName();
		if (name != null && matcher.matches(name)) {
			System.out.println("Searched file was found: " + name + " in "
					+ file.toRealPath().toString());
		}
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc)
			throws IOException {
		System.out.println("Visited: " + (Path) dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs)
			throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs)
			throws IOException {
		search((Path) file);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc)
			throws IOException {
		// report an error if necessary
		return FileVisitResult.CONTINUE;
	}
}

public class SearchingFilesbyGlobPattern {
	public static void main(String[] args) throws IOException {

		String glob = "*.jpg";
		Path fileTree = Paths.get("C:/rafaelnadal/");
		SearchGlobal walk = new SearchGlobal(glob);
		EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		Files.walkFileTree(fileTree, opts, Integer.MAX_VALUE, walk);
	}
}