package com.github.dr3amr2.vlc.utils;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Proprietary information subject to the terms of a Non-Disclosure Agreement
 */
public class ImageUtils
{
    private static final Logger logger = Logger.getLogger(ImageUtils.class);

    public static BufferedImage loadImage(String imageFilename)
    {
        try
        {
            return ImageIO.read(new File(imageFilename));

        } catch (IOException e)
        {
            logger.warn("Exception loading image", e);
        }
        return null;
    }

    public static void saveImage(BufferedImage image, String imageFilename)
    {
        try
        {
            ImageIO.write(
                    image,
                    "png",
                    new File(imageFilename));
        } catch (IOException e)
        {
            logger.warn("Exception writing image", e);
        }
    }

    public static BufferedImage loadImageBounded(String imageFilename, int maxSize)
    {
        BufferedImage image = loadImage(imageFilename);
        if (image == null)
        {
            return null;
        }

        int width = image.getWidth();
        int height = image.getHeight();
        if (width > maxSize || height > maxSize)
        {
            return convertToUShort(getScaledInstanceRespectAspectRatio(image, maxSize, maxSize));
        }
        return image;
    }

    public static BufferedImage convertToUShort(BufferedImage bufferedImage)
    {
        BufferedImage converted = new BufferedImage(
                bufferedImage.getWidth(),
                bufferedImage.getHeight(),
                BufferedImage.TYPE_USHORT_565_RGB);
        Graphics2D g = converted.createGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        return converted;
    }

    public static BufferedImage getImageFromResources(String imageFilename)
    {
        try
        {
            return ImageIO.read(ImageUtils.class.getResource(imageFilename));

        } catch (IOException e)
        {
            logger.warn("Exception loading image", e);
        }
        return null;
    }

    public static ImageIcon getSizedImageIcon(String imageName, int size)
    {
        BufferedImage image = getImageFromResources(imageName);
        if (image == null)
        {
            return null;
        }
        if (size > 0)
        {
            image = getScaledInstance(image, size);
        }
        return new ImageIcon(image);
    }

    public static ImageIcon getImageIcon(String iconFilename)
    {
        BufferedImage image = getImageFromResources(iconFilename);
        if (image != null)
        {
            return new ImageIcon(image);
        }

        logger.warn("Unable to load image: " + iconFilename);
        return null;
    }

    public static Color brighter(Color c, double factor)
    {
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        /* From 2D group:
         * 1. black.brighter() should return grey
         * 2. applying brighter to blue will always return blue, brighter
         * 3. non pure color (non zero rgb) will eventually return white
         */
        int i = (int) (1.0 / (1.0 - factor));
        if (r == 0 && g == 0 && b == 0)
        {
            return new Color(i, i, i);
        }
        if (r > 0 && r < i) r = i;
        if (g > 0 && g < i) g = i;
        if (b > 0 && b < i) b = i;

        return new Color(Math.min((int) (r / factor), 255),
                Math.min((int) (g / factor), 255),
                Math.min((int) (b / factor), 255));
    }

    public static Color darker(Color c, double factor)
    {
        return new Color(Math.max((int) (c.getRed() * factor), 0),
                Math.max((int) (c.getGreen() * factor), 0),
                Math.max((int) (c.getBlue() * factor), 0));
    }

    /**
     * Copied from: http://www.java2s.com/Code/Java/2D-Graphics-GUI/Commoncolorutilities.htm
     * <p/>
     * Blend two colors.
     *
     * @param c1    First color to blend.
     * @param c2    Second color to blend.
     * @param ratio Blend ratio. 0.5 will give even blend, 1.0 will return
     *              c1, 0.0 will return c2 and so on.
     * @return Blended color.
     */
    public static Color blend(Color c1, Color c2, double ratio)
    {
        float r = (float) ratio;
        float ir = 1.0f - r;

        float rgb1[] = new float[3];
        float rgb2[] = new float[3];

        c1.getColorComponents(rgb1);
        c2.getColorComponents(rgb2);

        return new Color(rgb1[0] * r + rgb2[0] * ir,
                rgb1[1] * r + rgb2[1] * ir,
                rgb1[2] * r + rgb2[2] * ir);
    }

    /**
     * Create a random color, with which a gradient will be created to fill this clip's background.
     *
     * @param lowThreshold low color channel threshold - all color channels created with this method will have a value
     *                     greater than or equal to this threshold.
     * @return a randomly-generated color.
     */
    public static Color generateRandomColor(int lowThreshold)
    {
        int range = 256 - lowThreshold;
        int r = (int) (Math.random() * range) + lowThreshold;
        int g = (int) (Math.random() * range) + lowThreshold;
        int b = (int) (Math.random() * range) + lowThreshold;
        return new Color(r, g, b);
    }

    public static Color generateRandomColor(int lowThreshold, int highThreshold)
    {
        int range = highThreshold - lowThreshold;
        int r = (int) (Math.random() * range) + lowThreshold;
        int g = (int) (Math.random() * range) + lowThreshold;
        int b = (int) (Math.random() * range) + lowThreshold;
        return new Color(r, g, b);
    }

    /**
     * Convenience method that returns a scaled instance of the provided {@code BufferedImage}.  Playing it loose and
     * fast - just a medium quality scaling technique.
     *
     * @param img          the original image to be scaled
     * @param targetWidth  the desired width of the scaled instance, in pixels
     * @param targetHeight the desired height of the scaled instance, in pixels
     *                     if the value is -1 then the height will be scaled proportionally to the width
     * @return a scaled version of the original {@code BufferedImage}
     */
    public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight)
    {
        if (img == null)
        {
            logger.warn("Unable to scale null image", new Throwable());
            return null;
        }

        // Use a proportional height if the parameter is a -1
        if (targetHeight == -1)
        {
            targetHeight = (int) (img.getHeight() * ((double) targetWidth) / (double) img.getWidth());
        } else if (targetWidth == -1)
        {
            targetWidth = (int) (img.getWidth() * ((double) targetHeight) / (double) img.getHeight());
        }

        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        if (targetWidth <= 0)
        {
            targetWidth = 10;
        }
        if (targetHeight <= 0)
        {
            targetHeight = 10;
        }
        BufferedImage scaledVersion = new BufferedImage(targetWidth, targetHeight, type);
        Graphics2D g2 = scaledVersion.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, targetWidth, targetHeight, null);
        g2.dispose();
        return scaledVersion;
    }

    public static BufferedImage getHighQualityScaledInstance(BufferedImage img, int targetWidth, int targetHeight)
    {
        // Use a proportional height if the parameter is a -1
        if (targetHeight == -1)
        {
            targetHeight = (int) (img.getHeight() * ((double) targetWidth) / (double) img.getWidth());
        } else if (targetWidth == -1)
        {
            targetWidth = (int) (img.getWidth() * ((double) targetHeight) / (double) img.getHeight());
        }

        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = img;
        int w, h;
        // Use multi-step technique: start with original size, then
        // scale down in multiple passes with drawImage()
        // until the target size is reached
        w = img.getWidth();
        h = img.getHeight();

        do
        {
            if (w > targetWidth)
            {
                w /= 2;
            }
            if (w < targetWidth)
            {
                w = targetWidth;
            }

            if (h > targetHeight)
            {
                h /= 2;
            }
            if (h < targetHeight)
            {
                h = targetHeight;
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        }
        while (w != targetWidth || h != targetHeight);

        return ret;
    }

    public static BufferedImage getScaledInstance(BufferedImage image, int size)
    {
        return getScaledInstance(image, size, size);
    }

    /**
     * This code was taken from:
     * http://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio
     * <p/>
     * Get a scaled dimension for a buffered image, respecting the image's aspect ratio.
     *
     * @param image       image to scale.
     * @param boundWidth  bounding width (largest possible scaled width).
     * @param boundHeight bounding height (largest possible scaled height).
     * @return a scaled dimension for the buffered image, respecting the image's aspect ratio.
     */
    public static Dimension getScaledDimension(BufferedImage image, double boundWidth, double boundHeight)
    {
        double originalWidth = image.getWidth();
        double originalHeight = image.getHeight();
        double newWidth = originalWidth;
        double newHeight = originalHeight;

        if (originalWidth <= boundWidth && originalHeight <= boundHeight)
        {
            return new Dimension((int) originalWidth, (int) originalHeight);
        }

        // first check if we need to scale width
        if (originalWidth > boundWidth)
        {
            //scale width to fit
            newWidth = boundWidth;
            //scale height to maintain aspect ratio
            newHeight = (newWidth * originalHeight) / originalWidth;
        }

        // then check if we need to scale even with the new height
        if (newHeight > boundHeight)
        {
            //scale height to fit instead
            newHeight = boundHeight;
            //scale width to maintain aspect ratio
            newWidth = (newHeight * originalWidth) / originalHeight;
        }

        return new Dimension((int) newWidth, (int) newHeight);
    }

    public static BufferedImage getScaledInstanceRespectAspectRatio(BufferedImage image, int boundWidth, int boundHeight)
    {
        if (image == null)
        {
            logger.warn("Unable to scale null image", new Throwable());
            return null;
        }

        int width = image.getWidth();
        int height = image.getHeight();
        if (width != boundWidth && height != boundHeight)
        {
            Dimension scaledDimension = getScaledDimension(image, boundWidth, boundHeight);
            return getScaledInstance(image, scaledDimension.width, scaledDimension.height);
        }
        return image;
    }
}
