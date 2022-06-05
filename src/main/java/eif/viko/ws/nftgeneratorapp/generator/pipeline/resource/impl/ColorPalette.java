package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResource;

import java.awt.*;

public class ColorPalette extends ProcessorStepResource<Color> {

    private Color[] colors;
    private boolean repeating;
    private int paletteIndex;

    @Override
    public void validateResource() throws IllegalStateException {
        super.validateResource();

        if (colors == null || colors.length == 0) {
            throw new IllegalStateException("'colors' not assigned");
        }
    }

    @Override
    public Class<?> getResourceValueType() {
        return Color.class;
    }

    @Override
    protected boolean isUsable() {
        return true;
    }

    @Override
    protected Color provide() {
        int index = paletteIndex++;

        if (index == colors.length && paletteIndex == colors.length) {
            index = paletteIndex = 0;
        }

        return colors[index];
    }

    public Color[] getColors() {
        return colors;
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }

    public boolean isRepeating() {
        return repeating;
    }

    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public int getPaletteIndex() {
        return paletteIndex;
    }

    public void setPaletteIndex(int paletteIndex) {
        this.paletteIndex = paletteIndex;
    }

}
