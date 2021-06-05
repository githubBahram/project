package com.parsdeveloper.shopping.model.commons.util;

import net.coobird.thumbnailator.Thumbnails;
import org.imgscalr.Scalr;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class FileUtils {

    public static MultipartFile createImageThumbnail(File file, Integer width) {

        BufferedImage thumbImg = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            BufferedImage img = readImage(file.getPath());

            Thumbnails.of(img)
                    .size(300,300)
                    .outputQuality(1f)
                    .outputFormat("JPG")
                    .toOutputStream(baos);

//            BufferedImage img = readImage(file.getPath());
//            thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
//            ImageIO.write(thumbImg, "jpg", baos);
//            baos.flush();

            MultipartFile multipartFileThumb = new MockMultipartFile("file", file.getName(),
                    "jpg/plan", baos.toByteArray());

            return multipartFileThumb;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static BufferedImage convert(BufferedImage src, int bufImgType) {
        BufferedImage img = new BufferedImage(src.getWidth(), src.getHeight(), bufImgType);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(src, 0, 0, null);
        g2d.dispose();
        return img;
    }

    public static BufferedImage convertCMYK2RGB(BufferedImage image) throws IOException {
        BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        ColorConvertOp op = new ColorConvertOp(null);
        op.filter(image, rgbImage);
        return rgbImage;
    }

    public static MultipartFile createImageThumbnail(MultipartFile multipartFile, Integer width) {

        BufferedImage thumbImg = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            File file = convertMultiPartToFile(multipartFile);
            BufferedImage img = readImage(file.getPath());
            thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
            ImageIO.write(thumbImg, "jpg", baos);
            baos.flush();

            MultipartFile multipartFileThumb = new MockMultipartFile("file", file.getName(),
                    "jpg/plan", baos.toByteArray());

            return multipartFileThumb;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

//    public static String readImage(String filename) throws IOException {
//        File file = new File(filename);
//        ImageInputStream input = ImageIO.createImageInputStream(file);
//        Iterator readers = ImageIO.getImageReaders(input);
//        if(readers == null || !readers.hasNext()) {
//            throw new RuntimeException("1 No ImageReaders found");
//        }
//        ImageReader reader = (ImageReader) readers.next();
//        reader.setInput(input);
//        String format = reader.getFormatName() ;
//        BufferedImage image;
//
//        if ( "JPEG".equalsIgnoreCase(format) ||"JPG".equalsIgnoreCase(format) )   {
//            try {
//                image = reader.read(0); //RGB
//            } catch (IIOException e) {
//                Raster raster = reader.readRaster(0, null);//CMYK
//                image = createJPEG4(raster);
//            }
//            image.getGraphics().drawImage(image, 0, 0, null);
//            String dstfilename = filename.substring(0,filename.lastIndexOf("."))+"_rgb"+filename.substring(filename.lastIndexOf("."));
//            String newfilename = filename;
//            File newFile = new File(dstfilename);
//            FileOutputStream out = new FileOutputStream(newFile);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
//            out.flush();
//            out.close();
//            return dstfilename;
//        }
//        return null;
//    }

    public static BufferedImage readImage(String filename) throws IOException {
        File file = new File(filename);
        ImageInputStream input = ImageIO.createImageInputStream(file);
        Iterator readers = ImageIO.getImageReaders(input);
        if (readers == null || !readers.hasNext()) {
            throw new RuntimeException("1 No ImageReaders found");
        }
        ImageReader reader = (ImageReader) readers.next();
        reader.setInput(input);
        String format = reader.getFormatName();
        BufferedImage image = null;

        if ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) {
            try {
                image = reader.read(0); //RGB
            } catch (IIOException e) {
                Raster raster = reader.readRaster(0, null);//CMYK
                image = createJPEG4(raster);
            }
            image.getGraphics().drawImage(image, 0, 0, null);
        }
        return image;
    }

    private static BufferedImage createJPEG4(Raster raster) {
        int w = raster.getWidth();
        int h = raster.getHeight();
        byte[] rgb = new byte[w * h * 3];

        float[] Y = raster.getSamples(0, 0, w, h, 0, (float[]) null);
        float[] Cb = raster.getSamples(0, 0, w, h, 1, (float[]) null);
        float[] Cr = raster.getSamples(0, 0, w, h, 2, (float[]) null);
        float[] K = raster.getSamples(0, 0, w, h, 3, (float[]) null);
        for (int i = 0, imax = Y.length, base = 0; i < imax; i++, base += 3) {
            float k = 220 - K[i], y = 255 - Y[i], cb = 255 - Cb[i],
                    cr = 255 - Cr[i];

            double val = y + 1.402 * (cr - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
                    : (byte) (val + 0.5);

            val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base + 1] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
                    : (byte) (val + 0.5);

            val = y + 1.772 * (cb - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base + 2] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
                    : (byte) (val + 0.5);
        }
        raster = Raster.createInterleavedRaster(new DataBufferByte(rgb, rgb.length), w, h, w * 3, 3, new int[]{0, 1, 2}, null);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        ColorModel cm = new ComponentColorModel(cs, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        return new BufferedImage(cm, (WritableRaster) raster, true, null);
    }

}
