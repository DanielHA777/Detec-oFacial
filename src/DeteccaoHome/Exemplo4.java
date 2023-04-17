/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeteccaoHome;

import org.opencv.core.Core; 
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
//import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import org.opencv.objdetect.CascadeClassifier;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class Exemplo4 {

    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat imagem = imread("src\\outros\\banana1.jpg" /*CV_LOAD_IMAGE_COLOR*/);
        Mat imagemCinza = new Mat();
        Imgproc.cvtColor(imagem, imagemCinza, COLOR_BGR2GRAY);      


        CascadeClassifier classificador = 
                new CascadeClassifier("src\\cascades\\banana_classifier.xml");

        MatOfRect detectado = new MatOfRect();
        classificador.detectMultiScale(imagemCinza, detectado);
               
        for (Rect rect : detectado.toArray()) {      
            Imgproc.rectangle(imagem, new Point(rect.x, rect.y), 
                    new Point(rect.x + rect.width, rect.y + rect.height), 
                    new Scalar(0, 0, 255), 2);
        }

        Utilitarios ut = new Utilitarios();
        ut.mostraImagem(ut.convertMatToImage(imagem));
    }
}
