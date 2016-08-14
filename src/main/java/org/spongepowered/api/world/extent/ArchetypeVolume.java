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
package org.spongepowered.api.world.extent;

import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.block.tileentity.TileEntityArchetype;
import org.spongepowered.api.entity.EntityArchetype;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.worker.MutableBlockVolumeWorker;

import java.util.Map;
import java.util.Optional;

/**
 * A copy of a region taken from another extent.
 */
public interface ArchetypeVolume extends MutableBlockVolume {

    /**
     * Applies this archetype at the given location. The archetype will be
     * mapped onto the given world such that the origin on the archetype lines
     * up with the given position.
     * 
     * @param location The location to apply at
     * @param cause The cause of the changes
     */
    void apply(Location<World> location, Cause cause);

    /**
     * Gets the {@link TileEntityArchetype} for the tile entity carrying block
     * at the given coordinates.
     * 
     * @param x The X position
     * @param y The Y position
     * @param z The Z position
     * @return The tile entity, if found
     */
    Optional<TileEntityArchetype> getBlockArchetype(int x, int y, int z);

    /**
     * Gets the {@link TileEntityArchetype} for the tile entity carrying block
     * at the given coordinates.
     * 
     * @param position The position
     * @return The tile entity, if found
     */
    default Optional<TileEntityArchetype> getBlockArchetype(Vector3i position) {
        return getBlockArchetype(position.getX(), position.getY(), position.getZ());
    }

    /**
     * Gets a map containing all tile entitiy archetypes within this volume,
     * keyed by their positions within the volume.
     * 
     * @return The tile entity map
     */
    Map<Vector3i, TileEntityArchetype> getBlockArchetypes();

    /**
     * Gets a map of all {@link EntityArchetype}s within this volume, keyed by
     * their positions.
     * 
     * @return The entity map
     */
    Map<Vector3f, EntityArchetype> getEntityArchetypes();

    @Override
    MutableBlockVolumeWorker<? extends ArchetypeVolume> getBlockWorker();

}
