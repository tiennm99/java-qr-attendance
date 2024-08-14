package io.github.tiennm99.qrattendance.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;

@Service
public class QRCodeService {

  public String generateQRCode(String text) {
    try {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      var bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 256, 256);
      BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(qrImage, "png", baos);
      byte[] imageBytes = baos.toByteArray();
      return Base64.getEncoder().encodeToString(imageBytes);
    } catch (Exception e) {
      throw new RuntimeException("Failed to generate QR code", e);
    }
  }
}
