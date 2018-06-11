package de.dhpoly.netzwerk.control;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Serialisierer
{
	private Serialisierer()
	{}

	public static String toString(Serializable o) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.close();
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}
	
	public static Serializable toObject(String s) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(s);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Serializable s1 = (Serializable) in.readObject();
		in.close();
		fileIn.close();
		return s1;
	}
}
