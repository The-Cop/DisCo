package ru.thecop.disco.container;

import ru.thecop.disco.common.Borders;
import ru.thecop.disco.common.Padding;
import ru.thecop.disco.common.Separations;

public class SimpleContainerSettings {

    private Padding padding;
    private Separations separations;
    private Borders borders;

    public static SimpleContainerSettings box() {
        return new SimpleContainerSettings(null, null, new Borders('|', '-', null));
    }

    public static SimpleContainerSettings invisible() {
        return new SimpleContainerSettings(new Padding(), null, null);
    }

    public static SimpleContainerSettings dos() {
        // TODO: 29.09.2016 implement
        return new SimpleContainerSettings(null, null, null);
    }

    public SimpleContainerSettings(Padding padding, Separations separations, Borders borders) {
        this.padding = padding;
        this.separations = separations;
        this.borders = borders;
    }

    public Padding getPadding() {
        return padding;
    }

    public SimpleContainerSettings setPadding(Padding padding) {
        this.padding = padding;
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
