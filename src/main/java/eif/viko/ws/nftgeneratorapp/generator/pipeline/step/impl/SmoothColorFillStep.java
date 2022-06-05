package eif.viko.ws.nftgeneratorapp.generator.pipeline.step.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.StepProperty;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SmoothColorFillStep extends ImageProcessorStep {

    private final StepProperty<Color> color = new StepProperty<>(Color.class);

    @Override
    public void validateStep() throws IllegalStateException {
        if (!color.isSet()) {
            throw new IllegalStateException("missing 'color' value");
        }
    }

    @Override
    public void onProcess(BufferedImage image) {
        int red = this.color.get().getRed();
        int green = this.color.get().getGreen();
        int blue = this.color.get().getBlue();
        Color transparentColor = new Color(red, green, blue, 128);
        Graphics2D graphics = image.createGraphics();
        graphics.setComposite(AlphaComposite.SrcAtop);
        graphics.setColor(transparentColor);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    public StepProperty<Color> getColorProperty() {
        return color;
    }
}
