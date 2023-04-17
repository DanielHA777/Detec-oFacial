package DeteccaoHome;

import java.awt.BorderLayout ;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import org.opencv.videoio.VideoCapture;

public class WebCam extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
					WebCam frame = new WebCam();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.mostraVideo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mostraVideo() {
		Mat video = new Mat();
		VideoCapture capture = new VideoCapture(0);
		if(capture.isOpened()) {
			while(true) {
				capture.read(video);
				if(!video.empty()) {
					setSize(video.width() + 50, video.height() + 70);
					
					Mat imagemColorida = video;
					Mat imagemCinza = new Mat();
					Imgproc.cvtColor(imagemColorida, imagemCinza, COLOR_BGR2GRAY);
				    CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
					MatOfRect facesdetectadas = new MatOfRect();
					classificador.detectMultiScale(imagemCinza, facesdetectadas, 1.1, 1, 0, new Size(20,20), new Size(20,20));
					
					for(Rect rect: facesdetectadas.toArray()) {
						Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0,0,255),2);
					}
				    
					BufferedImage imagem = new Utilitarios().convertMatToImage(video);
					Graphics g = jPanell.getGraphics();
					g.drawImage(imagem, 10, 10, imagem.getWidth(), imagem.getHeight(), null);
				
					
				}
			}
		}
	}
	/**
	 * Create the frame.
	 */
	public WebCam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	private javax.swing.JPanel jPanell;

}
