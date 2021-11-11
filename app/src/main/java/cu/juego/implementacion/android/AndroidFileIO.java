package cu.juego.implementacion.android;

import java.io.*;

import android.content.res.AssetManager;
import android.os.Environment;

import cu.juego.implementacion.*;

public class AndroidFileIO implements FileIO{
AssetManager assets;
String rutaAlmacenamientoExterno;
	

public AndroidFileIO(AssetManager assets){
	this.assets = assets;
	this.rutaAlmacenamientoExterno = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	
}


	@Override
	public InputStream leerAsset(String nombreArchivo) throws IOException {
		// TODO Auto-generated method stub
		return assets.open(nombreArchivo);
	}

	@Override
	public InputStream leerArchivo(String nombreArchivo) throws IOException {
		// TODO Auto-generated method stub
		return new FileInputStream(rutaAlmacenamientoExterno + nombreArchivo);
	}

	@Override
	public OutputStream escreibirArchivo(String nombreArchivo)
			throws IOException {
		// TODO Auto-generated method stub
		return new FileOutputStream(rutaAlmacenamientoExterno + nombreArchivo);
	}

}































