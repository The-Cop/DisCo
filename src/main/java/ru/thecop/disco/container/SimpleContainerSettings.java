package ru.thecop.disco.container;

import ru.thecop.disco.common.Borders;
import ru.thecop.disco.common.Padding;
import ru.thecop.disco.common.Separations;

/**
 * A container that uses the whole width of the frame (screen?)
 */
public class SimpleContainerSettings {

    private Padding padding;
    private Separations separations;
    private Borders borders;

    public SimpleContainerSettings(Padding padding, Separations separations, Borders borders) {
        this.padding = new Padding(padding);
        this.separations = new Separations(separations);
        this.borders = new Borders(borders);
    }

    public Padding getPadding() {
        return padding;
    }

    public SimpleContainerSettings setPadding(Padding padding) {
        this.padding = new Padding(padding);
        return this;
    }

    public Separations getSeparations() {
        return separations;
    }

    public SimpleContainerSettings setSeparations(Separations separations) {
        this.separations = separations;
        return this;
    }

    public Borders getBorders() {
        return borders;
    }

    public SimpleContainerSettings setBorders(Borders borders) {
        this.borders = borders;
        return this;
    }
}
