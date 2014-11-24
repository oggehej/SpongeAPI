/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered.org <http://www.spongepowered.org>
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

package org.spongepowered.api.service.command;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Optional;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import org.spongepowered.api.util.Owner;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandMapping;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.dispatcher.SimpleDispatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleCommandService implements CommandService {

    private final SimpleDispatcher dispatcher = new SimpleDispatcher();
    private final Multimap<Owner, CommandMapping> owners = HashMultimap.create();
    private final Object lock = new Object();

    @Override
    public Optional<CommandMapping> register(Owner owner, CommandCallable callable, String... alias) {
        return register(owner, callable, Arrays.asList(alias));
    }

    @Override
    public Optional<CommandMapping> register(Owner owner, CommandCallable callable, List<String> aliases) {
        return register(owner, callable, aliases, Functions.<List<String>>identity());
    }

    @Override
    public Optional<CommandMapping> register(Owner owner, CommandCallable callable, List<String> aliases, Function<List<String>, List<String>> callback) {
        checkNotNull(owner);

        synchronized (lock) {
            // <namespace>:<alias> for all commands
            List<String> aliasesWithPrefix = new ArrayList<String>(aliases.size() * 2);
            for (String alias : aliases) {
                aliasesWithPrefix.add(alias);
                aliasesWithPrefix.add(owner.getId() + ":" + alias);
            }

            Optional<CommandMapping> mapping = dispatcher.register(callable, aliasesWithPrefix, callback);

            if (!mapping.isPresent()) {
                owners.put(owner, mapping.get());
            }

            return mapping;
        }
    }

    @Override
    public Optional<CommandMapping> remove(String alias) {
        synchronized (lock) {
            Optional<CommandMapping> removed = dispatcher.remove(alias);

            if (removed.isPresent()) {
                forgetMapping(removed.get());
            }

            return removed;
        }
    }

    @Override
    public Optional<CommandMapping> removeMapping(CommandMapping mapping) {
        synchronized (lock) {
            Optional<CommandMapping> removed = dispatcher.removeMapping(mapping);

            if (removed.isPresent()) {
                forgetMapping(removed.get());
            }

            return removed;
        }
    }

    private void forgetMapping(CommandMapping mapping) {
        Iterator<CommandMapping> it = owners.values().iterator();
        while (it.hasNext()) {
            if (it.next().equals(mapping)) {
                it.remove();
                break;
            }
        }
    }

    @Override
    public Set<Owner> getOwners() {
        synchronized (lock) {
            return ImmutableSet.copyOf(owners.keySet());
        }
    }

    @Override
    public Set<CommandMapping> getCommands() {
        return dispatcher.getCommands();
    }

    @Override
    public Set<CommandMapping> getOwnedBy(Owner owner) {
        synchronized (lock) {
            return ImmutableSet.copyOf(owners.get(owner));
        }
    }

    @Override
    public Set<String> getPrimaryAliases() {
        return dispatcher.getPrimaryAliases();
    }

    @Override
    public Set<String> getAliases() {
        return dispatcher.getAliases();
    }

    @Override
    public Optional<CommandMapping> get(String alias) {
        return dispatcher.get(alias);
    }

    @Override
    public boolean containsAlias(String alias) {
        return dispatcher.containsAlias(alias);
    }

    @Override
    public boolean containsMapping(CommandMapping mapping) {
        return dispatcher.containsMapping(mapping);
    }

    @Override
    public boolean call(CommandSource source, String arguments, List<String> parents) throws CommandException {
        return dispatcher.call(source, arguments, parents);
    }

    @Override
    public boolean testPermission(CommandSource source) {
        return dispatcher.testPermission(source);
    }

    @Override
    public List<String> getSuggestions(CommandSource source, String arguments) throws CommandException {
        return dispatcher.getSuggestions(source, arguments);
    }

    @Override
    public Optional<String> getShortDescription() {
        return dispatcher.getShortDescription();
    }

    @Override
    public Optional<String> getHelp() {
        return dispatcher.getHelp();
    }

    @Override
    public String getUsage() {
        return dispatcher.getUsage();
    }

    @Override
    public int size() {
        return dispatcher.size();
    }

}
