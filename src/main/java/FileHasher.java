package MilburnSam.FileUtils;
// Reading Files.
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
// Message digests themselves
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Sam Milburn.
 */
public class FileHasher {
	/**
	 * <b>Algorithm</b>
	 * The Factory Pattern used by MessageDigest.getInstance(String) has a lot of overhead.
	 * For large file batches, we can counteract this by storing instances for the classes lifetime.
	 * <b>Source</b>: 
	 * https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#MessageDigest
	 */
	public static enum Algorithm {
		MD2("MD2"), MD5("MD5"), SHA1("SHA-1"), SHA224("SHA-224"), SHA256("SHA-256"),
		SHA384("SHA-384"),SHA512("SHA-512"), SHA512x224("SHA-512/224"), SHA512x256("SHA-512/256");
		
		private MessageDigest standardDigest;
		public MessageDigest getMessageDigest() {
			return this.standardDigest;
		}
		private Algorithm(String StandardName){
			try {
				this.standardDigest = MessageDigest.getInstance(StandardName);
			} catch(NoSuchAlgorithmException e) {
				throw new ExceptionInInitializerError(); // Because of static initialisation.
			}
		}
	}
	// EOF Algorithm
	
	// FileHasher Section:
	
	/**
	 * getHashFromFile will yield a String object of hexadecimal format, 
	 * with a supplied FileInputStream object and FileHasher.Algorithm object.
	 * 
	 * @param fis The FileInputStream of the File you want to hash.
	 * @return <b>String</b> The hexadecimal file fingerprint.
	 * @throws FileNotFoundException
	 */
	public static String getHashFromFile(FileHasher.Algorithm algo, FileInputStream fis) 
		throws FileNotFoundException {
		if(fis == null || algo == null || !(fis instanceof FileInputStream)) {
			throw new FileNotFoundException("Null/Corrupted FileInputStream or Algorithm object.");
		}
		// Loop through the FileInputStream, 4096 bytes at a time.
		byte[] filebytes = new byte[4096];
		try {
			do {
				filebytes = fis.readNBytes(4096);
				algo.getMessageDigest().update(filebytes);
			} while(fis.available() > 0);
			// Convert the MessageDigest into a Hexadecimal String.
			byte[] hash 			= algo.getMessageDigest().digest();
			StringBuilder builder 	= new StringBuilder();
			for(byte b: hash) {
				builder.append(String.format("%02X", b).toLowerCase());
			}
			return builder.toString();
		} catch(IOException e) {
			throw new FileNotFoundException("Error: Issue reading file.");
		}
	}
	
	/**
	 * getHashFromFile will yield a String object of hexadecimal format, 
	 * with a supplied File object and FileHasher.Algorithm object.
	 * 
	 * @param file The File object, representing the file you want to hash.
	 * @return <b>String</b> The hexadecimal file fingerprint.
	 * @throws FileNotFoundException
	 */
	public static String getHashFromFile(FileHasher.Algorithm algo, File file) 
		throws FileNotFoundException {
		if(file == null || algo == null || !(file instanceof File) || file.isDirectory()) {
			throw new FileNotFoundException("Null/Corrupted File or Algorithm object.");
		}
		// Open a FileInputStream from the provided File.
		FileInputStream fis = new FileInputStream(file);
		// Use the FileInputStream method.
		return FileHasher.getHashFromFile(algo, fis);
	}
	
	/**
	 * getHashFromFile will yield a String object of hexadecimal format, 
	 * with a supplied String object and FileHasher.Algorithm object.
	 * 
	 * @param filename The String of the file you want to hash.
	 * @return <b>String</b> The hexadecimal file fingerprint.
	 * @throws FileNotFoundException
	 */
	public static String getHashFromFile(FileHasher.Algorithm algo, String filename) 
		throws FileNotFoundException {
		if(algo == null || filename =="null" || filename.isEmpty()) {
			throw new FileNotFoundException("Null/Corrupted String or Algorithm object.");
		}
		// Open a FileInputStream from the provided String: 
		// Will throw a FileNotFoundException if it's a directory.
		FileInputStream fis = new FileInputStream(filename);
		// Use the FileInputStream method.
		return FileHasher.getHashFromFile(algo, fis);
	}
	
}
