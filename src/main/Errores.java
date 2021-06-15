package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import menu.Menu;

public class Errores {
	public static final String PATH="src\\error.log";
	
	public static void errorLog(Exception e) {
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(PATH)));
			bw.write(e.getMessage());
			bw.newLine();
			bw.flush();
			for (StackTraceElement string : e.getStackTrace()) {
				bw.write(string.toString());
				bw.newLine();
				bw.flush();
			}
		} catch (IOException ex) {
			Menu.errorGrave();
		}
	}
	
}
