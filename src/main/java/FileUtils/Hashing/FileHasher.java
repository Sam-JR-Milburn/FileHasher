package FileUtils.Hashing;

// File-reading.
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

// MessageDigest: Provides Cryptographic-Hash-Functions to consumers. 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Map/HashMap: Associate filenames to hashes.
import java.util.Map;
import java.util.HashMap;

/**
 * FileHasher: 
 * Provide static methods to yield String object results from 
 * cryptographic hashes of files, either by filename or File objects.
 * @author Sam Milburn
 */
public class FileHasher {
	/**
	 * MDAlgorithm: 
	 * Because MessageDigest.getInstance(String) yields new implementations
	 * from the security.Provider, we don't want to instantiate for every file 
	 * when you can simply call .reset() on the instance for the next file.
	 * 
	 * Therefore, we'll store enums with an instance reference for the 
	 * FileHasher class lifetime (static). 
	 */
	public static enum MDAlgorithm {
		// Reference: https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#messagedigest-algorithms
		// MD2, MD5, SHA1 - essentially the same class of algorithm, with varying bit output.
		MD2("MD2"), MD5("MD5"), SHA1("SHA-1"),
		// SHA-2 Family. 
		SHA2_224("SHA-224"), SHA2_256("SHA-256"), SHA2_384("SHA-384"), SHA2_512_224("SHA-512/224"), SHA2_512_256("SHA-512/256"),
		// SHA-3 Family.
		SHA3_224("SHA3-224"), SHA3_256("SHA3-256"), SHA3_384("SHA3-384"), SHA3_512("SHA3-512");
		
		// Associate an instance to an enum.
		private MessageDigest digestInstance;
		public MessageDigest getInstance() {
			return this.digestInstance;
		}
		private MDAlgorithm(String algoStandardName) {
			try {
				this.digestInstance = MessageDigest.getInstance(algoStandardName);
			}catch(NoSuchAlgorithmException e) {
				System.out.println("Failed to initialise: "+algoStandardName);
				// Because of static initialisation, we must throw a specific static exception.
				throw new ExceptionInInitializerError();
			}
		}
	}
	
	private static final String MISSINGALGORITHM = "[ALGONOTFOUND]";
	
	/**
	 * getHashFromFile: Grab the hash of a file in hexadecimal format.
	 * 
	 * Will throw null if the algorithm doesn't exist, but the class
	 * shouldn't initialise anyway if any algorithm isn't found. 
	 * 
	 * @param filestream a file-reading stream that we want the hash value of.
	 * @param algorithm one of the FileHasher.MDAlgorithm algorithms.
	 * @return the hash value from this file.
	 * @throws FileNotFoundException
	 */
	public static String getHashFromFile(FileInputStream filestream, MDAlgorithm algorithm) 
		throws FileNotFoundException {
		// Safety checks on filestream.
		if(filestream == null) {
			throw new FileNotFoundException("Error: nonexistent FileInputStream object.");
		} else {
			try {
				if(filestream.available() == 0) 
					throw new FileNotFoundException("Error: EOF FileInputStream reached.");
			} catch(IOException e) {
				throw new FileNotFoundException("Error: FileInputStream is either closed or corrupted.");
			}
		}
		// Safety checks on algorithm.
		if(algorithm == null) { return MISSINGALGORITHM; }
		
		// Loop through the FileInputStream, 4096 bytes at a time.
		byte[] filebytes = new byte[4096];
		try {
			do {
				filebytes = filestream.readNBytes(4096);
				algorithm.getInstance().update(filebytes);
			} while(filestream.available() > 0);
			
			
			// Now that we've fed the filestream to the algorithm, we can yield the result.
			StringBuilder hashSB 	= new StringBuilder();
			byte[] filehash 		= algorithm.digestInstance.digest();
			for(byte b: filehash) {
				hashSB.append(String.format("%02X", b).toLowerCase());
			}
			// Reset the algorithm, return the hash value.
			algorithm.getInstance().reset();
			return hashSB.toString();
		} catch(IOException e) {
			throw new IllegalArgumentException("Error: Issue reading file.");
		}
	}
	
	/**
	 * getHashFromFile: Grab the hash of a file in hexadecimal format.
	 * 
	 * Will throw a special string if the algorithm 
	 * doesn't exist, but the class shouldn't initialise anyway 
	 * if any algorithm isn't found. 
	 * 
	 * @param file a File that we want the hash value of.
	 * @param algorithm one of the FileHasher.MDAlgorithm algorithms.
	 * @return the hash value from this file.
	 * @throws FileNotFoundException
	 */
	public static String getHashFromFile(File file, MDAlgorithm algorithm) 
		throws FileNotFoundException {
		// Safety checks on file.
		if(file == null || !file.exists() || !file.isFile() || !file.canRead()) {
			throw new FileNotFoundException("Error: Couldn't find or read a file in this path location.");
		}
		// Safety checks on algorithm.
		if(algorithm == null) { return MISSINGALGORITHM; }
		
		// Open a FileInputStream from the provided File, pass it to the FileInputStream method.
		FileInputStream filestream = new FileInputStream(file);
		return FileHasher.getHashFromFile(filestream, algorithm);
	}
	
	/**
	 * getHashFromFile: Grab the hash of a file in hexadecimal format.
	 * 
	 * @param filename the path to a file that we want the hash value of.
	 * @param algorithm one of the FileHasher.MDAlgorithm algorithms.
	 * @return the hash value from this file.
	 * @throws FileNotFoundException
	 */
	public static String getHashFromFile(String filename, MDAlgorithm algorithm) 
		throws FileNotFoundException {
		// Safety checks on filename.
		if(filename == null || filename.isEmpty() || filename.isBlank()) {
			throw new FileNotFoundException("Error: Couldn't find or read a file in this path location.");
		}
		// Safety checks on algorithm.
		if(algorithm == null) { return MISSINGALGORITHM; }
		
		// Open a FileInputStream from the provided String, pass it to the FileInputStream method.
		FileInputStream filestream = new FileInputStream(filename);
		return FileHasher.getHashFromFile(filestream, algorithm);
	}
	
	/**
	 * GetHashesFromDirectory: 
	 * Recursively traverse a directory and get the absolute paths, 
	 * and hashes of all its children.
	 * 
	 * @param directory a directory containing files that we want to hash.
	 * @param algorithm one of the FileHasher.MDAlgorithm algorithms.
	 * @return a map of filenames to hashes.
	 * @throws FileNotFoundException
	 */
	public static HashMap<String, String> getHashesFromDirectory(File directory, MDAlgorithm algorithm)
		throws FileNotFoundException {
		// Safety checks on directory name.
		if(		directory == null || !directory.exists() || 
				!directory.isDirectory() || !directory.canRead()) {
			throw new FileNotFoundException("Error: Can't locate or read this directory.");
		}
		// Safety checks on algorithm.
		if(algorithm == null) {
			return null;
		}
		
		// Recursively yield the absolute paths and hashes of all file/folder children.
		HashMap<String, String> childHashes = new HashMap<String, String>();
		
		// Children of a directory.
		File[] children = directory.listFiles();
		for(File childFile: children) {
			// Recurse if we have to, otherwise resolve.
			if(childFile.isFile() && childFile.canRead()) {
				// Get the absolute path.
				childHashes.put(
						childFile.getAbsolutePath(), 
						FileHasher.getHashFromFile(childFile, algorithm));
			} else if(childFile.isDirectory() && childFile.canRead()) {
				// Union of results.
				childHashes.putAll(
						FileHasher.getHashesFromDirectory(childFile, algorithm));
			}
		}
		
		// Finally, return.
		return childHashes;
	}
	
}
