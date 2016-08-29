package ru.thecop.disco.container;

import ru.thecop.disco.element.Block;

import java.util.List;

/**
 * A component of the frame that may be considered as a "row" - using the whole width of the display.<br/>
 * Consists of one or more @{@link Block}s
 */
public interface Container {

    List<Block> getBlocks();
}
