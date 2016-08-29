package ru.thecop.disco;

import ru.thecop.disco.container.Frame;

//TODO may be get rid of this class, use only DisplaySettings - pass them to Frame and further
public class Display {

    private DisplaySettings settings;

    public Display(DisplaySettings settings) {
        this.settings = settings;
    }

    public Display() {
        settings = DisplaySettings.defaultSettings();
    }

    public void showFrame(Frame frame) {
        //TODO implement diffenent outputs - console, file, ...?
        //TODO render frame
    }
}
