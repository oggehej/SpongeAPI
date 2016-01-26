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
package org.spongepowered.api.data.translator;

import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.data.persistence.DataFormats;
import org.spongepowered.api.world.extent.TileEntityVolume;

/**
 * A pseudo-enum of available {@link DataTranslator}s for various common types.
 */
public final class DataTranslators {

    /**
     * A translator for converting between {@link ConfigurationNode}s and
     * DataViews.
     */
    public static final DataTranslator<ConfigurationNode> CONFIGURATE_NODE = null;

    /**
     * A translator for converting between {@link TileEntityVolume}s and
     * DataViews.
     * 
     * <p>When serialized to disk as NBT (See {@link DataFormats#NBT}) the data
     * in the DataView will be compatible with any application supporting the
     * MCEdit Schematic Format.</p>
     * 
     * <p>However, it should be noted that the data will not match the Schematic
     * Format exactly ass it has been updated for compatibility with 1.8 and
     * higher minecraft versions and for better use within an ecosystem
     * containing custom content.</p>
     * 
     * <p>A full description of the MCEdit Schematic Format including
     * modifications made for compatibility can be found <a
     * href="https://gist.github.com/Deamon5550/154ced44575c8016118e">here</a>
     * </p>
     */
    public static final DataTranslator<TileEntityVolume> MCEDIT_SCHEMATIC = null;

    private DataTranslators() {
    }
}
