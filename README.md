## FileHasher
FileHasher is a simple Java library (with Maven [pom.xml] project configuration) that I've written, that can be used to generate 
a cryptographic hash value on a single file or all files (recursively, through subdirectories) in a directory.

It is fairly simple, and is a demonstration of my Java skills for a career portfolio. 
The inspiration for this was Ghidra reverse engineering of old games, the object files 
have to be hashed after they are compiled to check if they comply with the original DLLs.

Everything is from the Java SE, but SHA-3 will require Java SE 9 or greater.
I am planning to add JavaDocs integration, and I could possibly add JDBC functionality where you stream-in files to a 
JDBC Driver Connector, a specific algorithm, and a hash-storing table destination, perhaps with an attribute schema.

You could also implement similar functionality by converting network files (in RAM) to FileInputStreams, and hashing them that way.

I'm busy with classes right now though so this will have to wait.
