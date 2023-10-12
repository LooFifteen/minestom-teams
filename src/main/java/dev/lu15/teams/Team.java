/*
 * MIT License
 *
 * Copyright (c) 2023 LooFifteen <luis@lu15.dev>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.lu15.teams;

import java.util.Set;
import java.util.function.Function;
import net.minestom.server.Viewable;
import net.minestom.server.adventure.audience.PacketGroupingAudience;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * Represents a team.
 */
public sealed interface Team extends Viewable, PacketGroupingAudience permits TeamImpl {

    /**
     * Creates a new team with the given name.
     * @param name The name of the team.
     * @return The new team.
     */
    static @NotNull Team of(@NotNull String name) {
        return new TeamImpl(name);
    }

    /**
     * Gets the members of this team. NOT to be confused with viewers.
     * @return The members of this team.
     */
    @NotNull @Unmodifiable Set<TeamMember> getMembers();

    /**
     * Adds a member to this team.
     * @param member The member to add.
     * @return true if the member was added, false if the member was already in the team.
     */
    boolean addMember(@NotNull TeamMember member);

    /**
     * Removes a member from this team.
     * @param member The member to remove.
     * @return true if the member was removed, false if the member was not in the team.
     */
    boolean removeMember(@NotNull TeamMember member);

    /**
     * Gets the name of this team.
     * @return The name of this team.
     */
    @NotNull String getName();

    /**
     * Gets the metadata of this team.
     * @return The metadata of this team.
     */
    @NotNull TeamMeta getMeta();

    /**
     * Sets the metadata of this team.
     * @param meta The metadata to set.
     */
    void setMeta(@NotNull TeamMeta meta);

    /**
     * Sets the metadata of this team.
     * @param meta function to apply changes to a meta builder.
     */
    void setMeta(@NotNull Function<TeamMeta.@NotNull Builder, TeamMeta.@NotNull Builder> meta);

}
