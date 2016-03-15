/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.world.schematic;

import org.spongepowered.api.block.BlockState;

import java.util.Optional;

/**
 * Represents a palette of block states, mapping them to unique integer indices.
 * These indices are relevant only in the context of the palette they belong to
 * and anything storing the indices should also store the palette.
 * 
 * <p>Implementors of this interface should attempt to ensure that the id space
 * of the palette is continuous, that is to say that there are no indices less
 * than the highest in-use index which is not assigned to a blockstate.</p>
 */
public interface BlockPalette {

    /**
     * Gets the size of the palette which is the number of entries between zero
     * and the highest in-use index.
     * 
     * @return The palette size
     */
    int getPaletteSize();

    /**
     * Gets the blockstate matching the given index if it exists within the
     * palette.
     * 
     * @param index The index
     * @return The blockstate, if found
     */
    Optional<BlockState> getState(int index);

    /**
     * Gets the index matching the given blockstate if it exists within the
     * palette.
     * 
     * @param state The block state
     * @return The index, if found
     */
    Optional<Integer> getIndex(BlockState state);

    /**
     * Adds the given blockstate to the palette, assigning it a new index or
     * returning the existing index for the blockstate if it already existed
     * within the palette.
     * 
     * @param state The blockstate to add
     * @return The index of the blockstate
     */
    int addState(BlockState state);

    /**
     * Clears all entries from the palette.
     */
    void clear();

}
