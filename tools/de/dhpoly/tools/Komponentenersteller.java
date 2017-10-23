package de.dhpoly.tools;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Komponentenersteller
{

	public static void main(String[] args) throws Exception
	{
		System.out.println("Klassenname eingeben:");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		String startDir = System.getProperty("user.dir");
		erstellePackages(startDir + "\\src\\de\\dhpoly\\", name, "de.dhpoly." + name.toLowerCase());
	}

	private static void erstellePackages(String pfad, String name, String strPackage) throws Exception
	{
		File filePackage = new File(pfad + name.toLowerCase());
		filePackage.mkdirs();

		System.out.println("Package " + name + " erstellt");

		// Interface erstellen
		File fileInterface = new File(pfad + name.toLowerCase() + "\\" + name + ".java");
		if (fileInterface.exists())
		{
			throw new Exception("Datei schon vorhanden");
		}
		else
		{
			fileInterface.createNewFile();

			FileWriter writer = new FileWriter(fileInterface);
			writer.write("package " + strPackage + ";\n");
			writer.write("public interface " + name + " \n");
			writer.write("{" + "\n\n");
			writer.write("}" + "\n");

			writer.close();

			System.out.println("Interface " + name + " erstellt");
		}

		// Implementierung erstellen
		File fileImplPackage = new File(pfad + name.toLowerCase() + "\\control\\");
		fileImplPackage.mkdirs();

		File fileImpl = new File(pfad + name.toLowerCase() + "\\control\\" + name + "Impl.java");
		if (fileImpl.exists())
		{
			throw new Exception("Datei schon vorhanden");
		}
		else
		{
			fileImpl.createNewFile();

			FileWriter writer = new FileWriter(fileImpl);
			writer.write("package " + strPackage + ".control;\n");
			writer.write("import " + strPackage + "." + name + ";\n");
			writer.write("public class " + name + "Impl implements " + name + "\n");
			writer.write("{" + "\n\n");
			writer.write("}" + "\n");

			writer.close();

			System.out.println("Implementierungsklasse " + name + "Impl erstellt");
		}
	}
}
