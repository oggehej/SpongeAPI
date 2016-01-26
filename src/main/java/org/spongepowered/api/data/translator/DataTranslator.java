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

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.util.annotation.CatalogedBy;

/**
 * A translator that can translate {@link DataView}s into other types which do
 * not implement {@link DataSerializable}.
 *
 * @param <T> The type to translate to and from
 */
@CatalogedBy(DataTranslators.class)
public interface DataTranslator<T> extends CatalogType {

    /**
     * Translates the given {@link DataView} into the type.
     *
     * @param container The container to translate
     * @return A new instance of the type
     */
    T translateData(DataView container);

    /**
     * Translates the given {@link DataView} into the type. 
     *
     * @param object The node to store data
     * @param container The container of data to translate
     */
    void translateContainerToData(T object, DataView container);

    /**
     * Translates the given data structure into a {@link DataView}.
     *
     * @param node The data structure containing raw data
     * @return The newly created and translated {@link DataView}
     */
    DataContainer translateFrom(T node);

}
