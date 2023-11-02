# FileHasher

"Simple library to read disk files, and generate hash digests from them."

**FileHasher** is a lightweight library for retrieving a variety of hashes from disk files, with algorithms (there are more) like SHA-512, SHA-256, and MD5.
It's structure conforms to Maven package hierarchy, but isn't part of the central repo right now.

You can generate the JavaDocs source file with 
  **mvn source:jar**: FileHasher-source.jar
and the bytecode with 
  **mvn package**: FileHasher.jar. 

Using the package **MilburnSam.FileUtils.FileHasher.Algorithm** declares a set of Enums and pairs them to <b>java.security.MessageDigest</b> instances, for the lifetime of the FileHasher class.

Using one of those Algorithms, and a **FileInputStream**, **File**, or **String** (filename) - you can very quickly process the hash digest of a given file.
Tests are defined in **src/test/java/TestFileHasher.java**, and mostly compare (in a fairly static, verbose way) against a set of files defined in **src/test/resources**, with known hashes (using Linux's sha256sum and md5sum CLI commands).
