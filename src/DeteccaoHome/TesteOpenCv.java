package DeteccaoHome;

import org.opencv.core.Core; 
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;


import java.awt.Color;


public class TesteOpenCv {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println(Core.VERSION);
		System.out.println("Sucesso");
		
		Mat imagemColorida = imread("src\\DeteccaoHome\\opencv_java.jpg");
	
		Utilitarios ut = new Utilitarios();
		ut.mostraImagem(ut.convertMatToImage(imagemColorida));
		
		// IMAGEM COM COR CINZA
		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
		ut.mostraImagem(ut.convertMatToImage(imagemCinza));
	}
}
