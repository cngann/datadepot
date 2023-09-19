package com.circron.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Compresses a file or list of files into a single zip file.
 * 
 * @author Unknown
 * 
 */
public class Compressor {

	/**
	 * Compresses the given file into the given zip file. Any Subfolders are not
	 * retained.
	 */
	public static void doCompress(File inputFiles, File outputFile) throws IOException {
		doCompress(new File[] {
			inputFiles
		}, outputFile, false);
	}

	/**
	 * Compresses the given file into the given zip file.
	 */
	public static void doCompress(File inputFiles, File outputFile, boolean retainFolders) throws IOException {
		doCompress(new File[] {
			inputFiles
		}, outputFile, retainFolders);
	}

	/**
	 * Compresses the given files into the given zip file. Any Subfolders are
	 * not retained.
	 */
	public static void doCompress(File[] inputFiles, File outputFile) throws IOException {
		doCompress(inputFiles, outputFile, false);
	}

	/**
	 * Compresses the given files into the given zip file.
	 */
	public static void doCompress(File[] inputFiles, File outputFile, boolean retainFolders) throws IOException {
		byte[] buf = new byte[1024];
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
		for (File file : inputFiles) {
			FileInputStream in = new FileInputStream(file.getPath());
			out.putNextEntry(new ZipEntry(retainFolders ? file.getPath() : file.getName()));
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.closeEntry();
			in.close();
		}
		out.close();
	}
}