package br.edu.ifg.tads.mtp.eventif.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.smaxe.uv.media.VideoFrameFactory;
import com.smaxe.uv.media.core.VideoFrame;
import com.smaxe.uv.media.swing.JVideoScreen;
import com.smaxe.uv.na.WebcamFactory;
import com.smaxe.uv.na.webcam.IWebcam;

public class LerQRcode extends Object {
	private IWebcam webcam = null;
	private JButton jbtFechar; 
	private static String tipo;
	private static int id;
	
	public JInternalFrame getLerQRcode(String tipo, int id) throws Exception {
		jbtFechar = new JButton("Fechar");
		this.tipo = tipo;
		this.id = id;

		final JComboBox webcamComboBox = new JComboBox();
		final JInternalFrame frame = new JInternalFrame("WebCam", false,false,false,false);
		final JPanel content = new JPanel(new FlowLayout());

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(content);
		frame.setTitle("Escolha a sua Webcam");
		frame.setBounds(280, 110, 630, 480);

		webcamComboBox
				.setModel(new WebcamComboModel(WebcamFactory.getWebcams()));

		content.setBackground(new Color(0, 0, 0,0));
		content.add(new JLabel("Webcam: ", JLabel.RIGHT));
		content.add(webcamComboBox);
		
		jbtFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setClosed(true);
					webcam.close();
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton button = new JButton(new AbstractAction("Open") {
			private final static long serialVersionUID = -4792981545160764997L;

			public void actionPerformed(ActionEvent e) {
				webcam = (IWebcam) webcamComboBox
						.getSelectedItem();
				if (webcam == null)
					return;
				final AtomicReference<JInternalFrame> frameRef = new AtomicReference<JInternalFrame>();
				final JVideoScreen videoScreen = new JVideoScreen();
				
				
				Thread thread1 = new Thread(new Runnable() {
					public void run() {
						final AtomicReference<VideoFrame> lastFrameRef = new AtomicReference<VideoFrame>();

						try {
							webcam.open(new IWebcam.FrameFormat(640, 480),
									new IWebcam.IListener() {
										private VideoFrame lastFrame = new VideoFrame(
												0, 0, null);

										public void onVideoFrame(
												final VideoFrame frame) {
											SwingUtilities
													.invokeLater(new Runnable() {
														public void run() {
															videoScreen
																	.setFrame(frame);
															if (lastFrame.width != frame.width
																	|| lastFrame.height != frame.height) {
																final JInternalFrame frame = frameRef
																		.get();
																if (frame != null)
																	frame.pack();
															}
															lastFrame = frame;
															lastFrameRef
																	.set(lastFrame);
														}
													});
										}
									});

							final IWebcam.FrameFormat[] supportedFormats = webcam
									.getFormats();

							if (supportedFormats != null) {
								System.out.println("Webcam: "
										+ webcam.getName());
								System.out.println("Supported formats: ");

								for (IWebcam.FrameFormat format : supportedFormats) {
									System.out.println("=> " + format);
								}
							}

							webcam.startCapture();

							SwingUtilities.invokeLater(new Runnable() {
								public void run() {

									frameRef.set(frame);
									frame.getContentPane().add(videoScreen,
											BorderLayout.CENTER);

									frame.setSize(new Dimension(100, 100));
									frame.setTitle(webcam.getName());
									frame.setResizable(false);

									final JButton btnCapturar = new JButton(
											"Capturar");
									
									
									content.add(btnCapturar);

									btnCapturar
											.addActionListener(new ActionListener() {

												public void actionPerformed(
														ActionEvent arg0) {
													LerQRcode chama = null;

													try {
														chama = new LerQRcode();
													} catch (Exception e) {
														JOptionPane
																.showMessageDialog(
																		null,
																		"erro ao ler QRcode!");
														e.printStackTrace();
													}
													try {
														File file = new File(
																"ifg2015.png");
														VideoFrameFactory
																.saveAsPng(
																		file,
																		lastFrameRef
																				.get());
														chama.lerqr();
														
													} catch (Throwable t) {
														t.printStackTrace();
														
													}
												}
											});
									frame.pack();
									frame.setVisible(true);
								}
							});
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(frame,
									ex.getMessage(), ex.getMessage(),
									JOptionPane.WARNING_MESSAGE);
						}
					}

				});
				thread1.start();
			}
			
		});
		
		frame.getContentPane().add(content);
		content.add(button);
		content.add(jbtFechar);
		frame.setVisible(true);
		return frame;
	}

	private static class WebcamComboModel extends AbstractListModel implements
			ComboBoxModel {
		private final static long serialVersionUID = -8627944517955777531L;

		// fields
		private final List<IWebcam> devices;
		// state
		private Object selected = null;

		public WebcamComboModel(List<IWebcam> devices) {
			this.devices = devices;
		}

		public void setSelectedItem(final Object item) {
			this.selected = item;
		}

		public Object getSelectedItem() {
			return selected;
		}

		public Object getElementAt(int index) {
			return devices.get(index);
		}

		public int getSize() {
			return devices.size();
		}
	}

	public void lerqr() throws FileNotFoundException, NotFoundException,
			IOException {
		try {
			String filePath = "ifg2015.png";
			String charset = "UTF-8";
			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		
			
			System.out.println("Data read from QR Code: "
					+ readQRCode(filePath, charset, hintMap));
			
			// aqui será chamada a DAO para jogar e evento ou em atividade.
			
			JOptionPane.showMessageDialog(null, "RQcode Lido com Sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO ao Ler o QRcode!");
		}
	}

	public static String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		
		if(tipo.equals("evento")){
			
			// o id vai servir para dizer qual é o id correspondente.
			
			JOptionPane.showMessageDialog(null, "entrei evento");
		}else if(tipo.equals("atividade")){
			JOptionPane.showMessageDialog(null, "entrei Atividade");
		}
		
		return qrCodeResult.getText();
	}
	
	

}