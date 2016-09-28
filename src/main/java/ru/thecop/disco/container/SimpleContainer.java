package ru.thecop.disco.container;

import ru.thecop.disco.DisplayBlock;
import ru.thecop.disco.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * A container that uses the whole width of the frame (screen?)
 */
public class SimpleContainer implements DisplayBlock {

    private List<DisplayBlock> blocks = new ArrayList<>();
    private SimpleContainerSettings settings;

    public SimpleContainer(List<DisplayBlock> blocks, SimpleContainerSettings settings) {
        this.blocks = new ArrayList<>(blocks);
        this.settings = settings;
    }

    public SimpleContainer(DisplayBlock block, SimpleContainerSettings settings) {
        this.blocks.add(block);
        this.settings = settings;
    }

    @Override
    public List<String> buildLines(int width) {
        return Util.formatBlocks(blocks, width, settings.getSeparations(),
                settings.getPadding(), settings.getBorders());
    }

    public List<DisplayBlock> getBlocks() {
        return blocks;
    }

    public SimpleContainer setBlocks(List<DisplayBlock> blocks) {
        this.blocks = blocks;
        return this;
    }

    public SimpleContainerSettings getSettings() {
        return settings;
    }

    public SimpleContainer setSettings(SimpleContainerSettings settings) {
        this.settings = settings;
        return this;
    }
}
