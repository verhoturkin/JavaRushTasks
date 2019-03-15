package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

public class ImageReaderFactory {

    public static ImageReader getImageReader(ImageTypes imageType) {
        if (imageType != null) {
            if (imageType.equals(ImageTypes.BMP)) return new BmpReader();
            else if (imageType.equals(ImageTypes.JPG)) return new JpgReader();
            else if (imageType.equals(ImageTypes.PNG)) return new PngReader();
        }
        throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
