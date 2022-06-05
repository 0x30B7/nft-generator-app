package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.StepProperty;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class RotateClockwiseStep extends ImageProcessorStep {

    private final StepProperty<Integer> times = new StepProperty<>(Integer.class);

    @Override
    public void validateStep() throws IllegalStateException {
        if (!times.isSet()) {
            throw new IllegalStateException("missing 'times' value");
        }
    }

    @Override
    public void onProcess(BufferedImage image) {
        times.set(times.get() % 4);
        double theta = Math.toRadians(90 * times.get());
        double cos = Math.abs(Math.cos(theta));
        double sin = Math.abs(Math.sin(theta));
        double width = image.getWidth();
        double height = image.getHeight();
        int w = (int) (width * cos + height * sin);
        int h = (int) (width * sin + height * cos);
        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        Graphics2D graphics = rotatedImage.createGraphics();
        double x = w / 2;
        double y = h / 2;
        AffineTransform affineTransform = AffineTransform.getRotateInstance(theta, x, y);
        x = (w - width) / 2;
        y = (h - height) / 2;
        affineTransform.translate(x, y);
        graphics.drawRenderedImage(image, affineTransform);
        graphics.dispose();
        image = rotatedImage;
    }

    public StepProperty<Integer> getTimesProperty() {
        return times;
    }
}
