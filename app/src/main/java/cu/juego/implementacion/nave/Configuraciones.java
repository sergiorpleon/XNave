package cu.juego.implementacion.nave;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

import android.content.SharedPreferences;
import cu.juego.implementacion.FileIO;

public class Configuraciones {
public static SharedPreferences miPrefer;
public static ArrayList<Integer> lisP;

public static float musicLevel = (float)0.3;
public static float soundLevel = (float)0.2;
public static boolean soundEnabled = true;
public static int[] highscores = new int[]{500,400,300,200,100};
public static void load(FileIO files){
	BufferedReader in=null;
	try {
		in = new BufferedReader(new InputStreamReader(files.leerArchivo(".nave")));
		soundEnabled = Boolean.parseBoolean(in.readLine());
		for (int i = 0; i < 5; i++) {
			highscores[i] = Integer.parseInt(in.readLine());
		}
		
	} catch (IOException e) {
		// TODO: handle exception
	}catch (NumberFormatException e) {
		// TODO: handle exception
	}finally{
		if(in!=null)
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

public static void saved(FileIO files){
	BufferedWriter out=null;
	try {
		out = new BufferedWriter(new OutputStreamWriter(files.escreibirArchivo(".nave")));
		out.write(Boolean.toString(soundEnabled));
		out.write("\n");
		for (int i = 0; i < 5; i++) {
			out.write(Integer.toString(highscores[i]));
			out.write("\n");
		}
		
	} catch (IOException e) {
		// TODO: handle exception
	}catch (NumberFormatException e) {
		// TODO: handle exception
	}finally{
		if(out!=null)
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

public static void addScore(int score){
	for (int i = 0; i < 5; i++) {
		if(highscores[i]<score){
			for (int j = 4; j > i; j--) {
				highscores[j] = highscores[j-1];
			}
			highscores[i]=score;
			break;
		}
	}
}

//**************************************************************

public static void adicionar(Integer p) {
	// TODO Auto-generated method stub
	lisP.add(p);
	Collections.sort(lisP);
	lisP.remove(0);

}

//carga y ordena
public static void cargar(){
	String nomb1;
	int tiemp1;
	int mov1;
	Integer p;
	lisP = new ArrayList<Integer>();
	
	soundEnabled = miPrefer.getBoolean("sound", true);
	for (int i = 0; i < 5; i++) {
		mov1 = miPrefer.getInt("personaM"+i, i*20+20);
		p = mov1;
		lisP.add(p);
	}
	Collections.sort(lisP);
}

public static void guardar(){
	//--- Creacion del editor donde se salvara los valores
	SharedPreferences.Editor editor = miPrefer.edit();
	
	String nomb1;
	int tiemp1;
	int mov1;
	int p;
	//--- Guardado de los valores
	editor.putBoolean("sound", soundEnabled);
	for (int i = 0; i < 5; i++) {
		mov1 = lisP.get(i);
		editor.putInt("personaM"+i, mov1);

	}
	editor.commit();
}

}
