package eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.impl;

import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResource;

import java.awt.*;

/**
 * Class representing a {@link Color} providing resource, which
 * may hold multiple colors that are provided in order of definition
 * and repeat when {@link ColorPalette#provide()} is invoked more times
 * than the size of the colors storage
 */
public class ColorPalette extends ProcessorStepResource<Color> {

    private Color[] colors;
    private int paletteIndex;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateResource() throws IllegalStateException {
        super.validateResource();

        if (colors == null || colors.length == 0) {
            throw new IllegalStateException("'colors' not assigned");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getResourceValueType() {
        return Color.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isUsable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Color provide() {
        int index = paletteIndex++;

        if (index == colors.length && paletteIndex == colors.length) {
            index = paletteIndex = 0;
        }

        return colors[index];
    }

    /**
     * @return The colors associated with this color palette
     */
    public Color[] getColors() {
        return colors;
    }

    /**
     * Updates the colors associated with this color palette
     *
     * @param colors The given colors
     */
    public void setColors(Color[] colors) {
        this.colors = colors;
    }

    /**
     * @return The index of the next color to be provided
     */
    public int getPaletteIndex() {
        return paletteIndex;
    }

    /**
     * Updates the index of the next color to be provided
     *
     * @param paletteIndex The given index
     */
    public void setPaletteIndex(int paletteIndex) {
        this.paletteIndex = paletteIndex;
    }

}
