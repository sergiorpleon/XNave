package cu.juego.implementacion;
import java.io.*;
public interface FileIO {

	public InputStream leerAsset(String nombreArchivo) throws IOException;
	
	public InputStream leerArchivo(String nombreArchivo) throws IOException;
	
	public OutputStream escreibirArchivo(String nombreArchivo) throws IOException;
	
}
