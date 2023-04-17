package DeteccaoHome;

import static org.opencv.imgcodecs.Imgcodecs.imread; 
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Exemplo1 {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat imagemColorida = imread("src\\pessoas\\pessoas1.jpg");
		Mat imagemCinza = new Mat();
		Imgproc.cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
		
		CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml"); // usando ocascade padrão do opencv
		MatOfRect facesDetectadas = new MatOfRect(); // conta quantas faces foram detectadas
		classificador.detectMultiScale(imagemCinza, facesDetectadas, 
				2.5, // scale factor
				1, //minNeightbords
				0 // flags
				, new Size(10,10) // tamanho minimo
				, new Size(100,100)); // tamanho máximo
		System.out.println(facesDetectadas.toArray().length); // quantas faces detectadas
		
		for (Rect rect: facesDetectadas.toArray()) {//percorrendo as faces
			System.out.println(rect.x + " " + rect.y + " " + rect.width + " " + rect.height); // devolve o ponto x e y onde a face foi detectada + altura e largura
			//desenhando retangulo em cada face detectada
			Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 5);
		}
		
		Utilitarios ut = new Utilitarios();
		ut.mostraImagem(ut.convertMatToImage(imagemColorida));
	}
}
