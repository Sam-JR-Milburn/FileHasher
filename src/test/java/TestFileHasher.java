import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import MilburnSam.FileUtils.FileHasher;

/**
 * TestFileHasher is designed to test file inputs (Under src/test/resources) 
 * with the FileHashing Algorithm in FileHasher.java.
 * Unable to currently test: MD2, SHA1, SHA-224, SHA-384, SHA-512, SHA512/224, SHA512/256
 * @author Sam Milburn
 */
public class TestFileHasher {
	// pwd: .../FileHasher/FileHasher/
	private static String resourcedirectory = "src/test/resources/";
	private static String filenameJPG1 		= "Green_plotting.jpg";
	private static String filenameJPG2 		= "labour.jpg";
	private static String filenamePNG1 		= "hollywood_blvd.png";
	private static String filenamePNG2 		= "october-cat.png";
	private static String filenameMP4_1 	= "car.mp4";
	private static String filenameMP4_2		= "lego.mp4";
	private static String filenameGIF1		= "Cheydinhal.gif";
	private static String filenameGIF2		= "SoyMan.gif";
	
	@Test
	public void testMessageDigests() {
		System.out.println("Checking: if the FileHasher.Algorithm enums are statically instanced.");
		for(FileHasher.Algorithm algo: FileHasher.Algorithm.values()) {
			assertTrue(algo instanceof FileHasher.Algorithm);
		}
	}
	
	@Test
	public void testMD5_JPG() {
		System.out.println("Checking: the MD5 algorithm on .jpg's.");
		String filenameJPG1_MD5 = "35ad0a45ca3028a539c4f60056bd8055";
		String filenameJPG2_MD5 = "391ab160314607217bf1ac63eac0756a";
		try {
			String val1 		= null;
			String val2 		= null;
			String val3 		= null;
			FileInputStream fis = null;
			
			fis = new FileInputStream(resourcedirectory+filenameJPG1);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					new File(resourcedirectory+filenameJPG1)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					resourcedirectory+filenameJPG1); // String
			fis.close();
			assertEquals(val1, filenameJPG1_MD5);
			assertEquals(val2, filenameJPG1_MD5);
			assertEquals(val3, filenameJPG1_MD5);
			
			fis = new FileInputStream(resourcedirectory+filenameJPG2);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					new File(resourcedirectory+filenameJPG2)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					resourcedirectory+filenameJPG2); // String
			fis.close();
			assertEquals(val1, filenameJPG2_MD5);
			assertEquals(val2, filenameJPG2_MD5);
			assertEquals(val3, filenameJPG2_MD5);
		} catch(FileNotFoundException e) {
			fail(e.getLocalizedMessage());
		} catch(IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testMD5_PNG() {
		System.out.println("Checking: the MD5 algorithm on .png's.");
		String filenamePNG1_MD5 = "49fe73c6905c722ecdeb56f7bc8de44c";
		String filenamePNG2_MD5 = "78d6da5e36b14f756cb1c232b542dc8e";
		try {
			String val1 		= null;
			String val2 		= null;
			String val3 		= null;
			FileInputStream fis = null;
			
			fis = new FileInputStream(resourcedirectory+filenamePNG1);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					new File(resourcedirectory+filenamePNG1)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					resourcedirectory+filenamePNG1); // String
			fis.close();
			assertEquals(val1, filenamePNG1_MD5);
			assertEquals(val2, filenamePNG1_MD5);
			assertEquals(val3, filenamePNG1_MD5);
			
			fis = new FileInputStream(resourcedirectory+filenamePNG2);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					new File(resourcedirectory+filenamePNG2)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					resourcedirectory+filenamePNG2); // String
			fis.close();
			assertEquals(val1, filenamePNG2_MD5);
			assertEquals(val2, filenamePNG2_MD5);
			assertEquals(val3, filenamePNG2_MD5);
		} catch(FileNotFoundException e) {
			fail(e.getLocalizedMessage());
		} catch(IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testMD5_GIF() {
		System.out.println("Checking: the MD5 algorithm on .gif's.");
		String filenameGIF1_MD5 = "bf35284f35b73c3d4edbaef63b6b17ef";
		String filenameGIF2_MD5 = "c7e50dc5e3e945470685d087195fa960";
		
		try {
			String val1 		= null;
			String val2 		= null;
			String val3 		= null;
			FileInputStream fis = null;
			
			fis = new FileInputStream(resourcedirectory+filenameGIF1);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					new File(resourcedirectory+filenameGIF1)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					resourcedirectory+filenameGIF1); // String
			fis.close();
			assertEquals(val1, filenameGIF1_MD5);
			assertEquals(val2, filenameGIF1_MD5);
			assertEquals(val3, filenameGIF1_MD5);
			
			fis = new FileInputStream(resourcedirectory+filenameGIF2);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					new File(resourcedirectory+filenameGIF2)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					resourcedirectory+filenameGIF2); // String
			fis.close();
			assertEquals(val1, filenameGIF2_MD5);
			assertEquals(val2, filenameGIF2_MD5);
			assertEquals(val3, filenameGIF2_MD5);
		} catch(FileNotFoundException e) {
			fail(e.getLocalizedMessage());
		} catch(IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testMD5_MP4() {
		System.out.println("Checking: the MD5 algorithm on .mp4's.");
		String filenameMP4_1_MD5 = "57e206de572901befdfd2e359fa0f890";
		String filenameMP4_2_MD5 = "6d6a907598ad4d30508d65715e00c00f";
		
		try {
			String val1 		= null;
			String val2 		= null;
			String val3 		= null;
			FileInputStream fis = null;
			
			fis = new FileInputStream(resourcedirectory+filenameMP4_1);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					new File(resourcedirectory+filenameMP4_1)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					resourcedirectory+filenameMP4_1); // String
			fis.close();
			assertEquals(val1, filenameMP4_1_MD5);
			assertEquals(val2, filenameMP4_1_MD5);
			assertEquals(val3, filenameMP4_1_MD5);
			
			fis = new FileInputStream(resourcedirectory+filenameMP4_2);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					new File(resourcedirectory+filenameMP4_2)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.MD5, 
					resourcedirectory+filenameMP4_2); // String
			fis.close();
			assertEquals(val1, filenameMP4_2_MD5);
			assertEquals(val2, filenameMP4_2_MD5);
			assertEquals(val3, filenameMP4_2_MD5);
		} catch(FileNotFoundException e) {
			fail(e.getLocalizedMessage());
		} catch(IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testSHA256_JPG() {
		System.out.println("Checking: the SHA-256 algorithm on .jpg's.");
		String filenameJPG1_SHA256 = "fed1eb663a5cd8d0c19ac5534fa559254140396042bbd05c9d98a8bdd512be10";
		String filenameJPG2_SHA256 = "c8da4c6d5a1adcfd1f4a0e8d1de07aae5b5fd669712149541996655199268c36";
		try {
			String val1 		= null;
			String val2 		= null;
			String val3 		= null;
			FileInputStream fis = null;
			
			fis = new FileInputStream(resourcedirectory+filenameJPG1);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					new File(resourcedirectory+filenameJPG1)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					resourcedirectory+filenameJPG1); // String
			fis.close();
			assertEquals(val1, filenameJPG1_SHA256);
			assertEquals(val2, filenameJPG1_SHA256);
			assertEquals(val3, filenameJPG1_SHA256);
			
			fis = new FileInputStream(resourcedirectory+filenameJPG2);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					new File(resourcedirectory+filenameJPG2)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					resourcedirectory+filenameJPG2); // String
			fis.close();
			assertEquals(val1, filenameJPG2_SHA256);
			assertEquals(val2, filenameJPG2_SHA256);
			assertEquals(val3, filenameJPG2_SHA256);
		} catch(FileNotFoundException e) {
			fail(e.getLocalizedMessage());
		} catch(IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testSHA256_PNG() {
		System.out.println("Checking: the SHA-256 algorithm on .png's.");
		String filenamePNG1_SHA256 = "1ed5dacb9c8606ee69887f8be569c6c1aa57687c17c73801f158def94307feec";
		String filenamePNG2_SHA256 = "7c66fe9acf8a9c7cfc700fcdf93cc5f49c40d0b32f171fcdc2620523031c9e96";
		try {
			String val1 		= null;
			String val2 		= null;
			String val3 		= null;
			FileInputStream fis = null;
			
			fis = new FileInputStream(resourcedirectory+filenamePNG1);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					new File(resourcedirectory+filenamePNG1)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					resourcedirectory+filenamePNG1); // String
			fis.close();
			assertEquals(val1, filenamePNG1_SHA256);
			assertEquals(val2, filenamePNG1_SHA256);
			assertEquals(val3, filenamePNG1_SHA256);
			
			fis = new FileInputStream(resourcedirectory+filenamePNG2);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					new File(resourcedirectory+filenamePNG2)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					resourcedirectory+filenamePNG2); // String
			fis.close();
			assertEquals(val1, filenamePNG2_SHA256);
			assertEquals(val2, filenamePNG2_SHA256);
			assertEquals(val3, filenamePNG2_SHA256);
		} catch(FileNotFoundException e) {
			fail(e.getLocalizedMessage());
		} catch(IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testSHA256_GIF() {
		System.out.println("Checking: the SHA-256 algorithm on .gif's.");
		String filenameGIF1_SHA256 = "46bb918f6c9b1f2ab2e09761ef2edcc81483b8618560f6abc1ea0a2f1762e97f";
		String filenameGIF2_SHA256 = "a557bab68856f0b44474ac91ce456752f1ee91b0002d12a82587674a606c2b9e";
		try {
			String val1 		= null;
			String val2 		= null;
			String val3 		= null;
			FileInputStream fis = null;
			
			fis = new FileInputStream(resourcedirectory+filenameGIF1);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					new File(resourcedirectory+filenameGIF1)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					resourcedirectory+filenameGIF1); // String
			fis.close();
			assertEquals(val1, filenameGIF1_SHA256);
			assertEquals(val2, filenameGIF1_SHA256);
			assertEquals(val3, filenameGIF1_SHA256);
			
			fis = new FileInputStream(resourcedirectory+filenameGIF2);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					new File(resourcedirectory+filenameGIF2)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					resourcedirectory+filenameGIF2); // String
			fis.close();
			assertEquals(val1, filenameGIF2_SHA256);
			assertEquals(val2, filenameGIF2_SHA256);
			assertEquals(val3, filenameGIF2_SHA256);
		} catch(FileNotFoundException e) {
			fail(e.getLocalizedMessage());
		} catch(IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testSHA256_MP4() {
		System.out.println("Checking: the SHA-256 algorithm on .mp4's.");
		String filenameMP4_1_SHA256 = "69bdf9384bae0ff21a76f36202b2a407e64fcaa7b3a108d5bbd5df2ab17d3e71";
		String filenameMP4_2_SHA256 = "b8669de7ca71ae549285d804d35681137018510570a03e64753eddce3be381ce";
		try {
			String val1 		= null;
			String val2 		= null;
			String val3 		= null;
			FileInputStream fis = null;
			
			fis = new FileInputStream(resourcedirectory+filenameMP4_1);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					new File(resourcedirectory+filenameMP4_1)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					resourcedirectory+filenameMP4_1); // String
			fis.close();
			assertEquals(val1, filenameMP4_1_SHA256);
			assertEquals(val2, filenameMP4_1_SHA256);
			assertEquals(val3, filenameMP4_1_SHA256);
			
			fis = new FileInputStream(resourcedirectory+filenameMP4_2);
			val1 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, fis); //FileInputStream
			val2 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					new File(resourcedirectory+filenameMP4_2)); // File
			val3 = FileHasher.getHashFromFile(FileHasher.Algorithm.SHA256, 
					resourcedirectory+filenameMP4_2); // String
			fis.close();
			assertEquals(val1, filenameMP4_2_SHA256);
			assertEquals(val2, filenameMP4_2_SHA256);
			assertEquals(val3, filenameMP4_2_SHA256);
		} catch(FileNotFoundException e) {
			fail(e.getLocalizedMessage());
		} catch(IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
}
