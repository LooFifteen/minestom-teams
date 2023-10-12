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

import java.util.Objects;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a member of a {@link Team}.
 */
public sealed interface TeamMember extends ForwardingAudience.Single {

    /**
     * Creates a new {@link TeamMember} from the given {@link net.minestom.server.entity.Entity}.
     * @param entity The entity to create a {@link TeamMember} from.
     * @return The new {@link TeamMember}.
     */
    static @NotNull TeamMember of(@NotNull net.minestom.server.entity.Entity entity) {
        if (entity instanceof net.minestom.server.entity.Player player) return new Player(player);
        return new Entity(entity);
    }

    /**
     * Gets the ID of this member.
     * @return The ID of this member.
     */
    @NotNull String getId();

    record Player(@NotNull net.minestom.server.entity.Player player) implements TeamMember {

        @Override
        public @NotNull String getId() {
            return this.player.getUsername();
        }

        @Override
        public @NotNull Audience audience() {
            return this.player;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player player1 = (Player) o;
            return Objects.equals(player, player1.player);
        }

        @Override
        public int hashCode() {
            return Objects.hash(player);
        }

    }

    record Entity(@NotNull net.minestom.server.entity.Entity entity) implements TeamMember {

        @Override
        public @NotNull String getId() {
            return this.entity.getUuid().toString();
        }

        @Override
        public @NotNull Audience audience() {
            return Audience.empty(); // Cannot send messages to entities
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entity entity1 = (Entity) o;
            return Objects.equals(entity, entity1.entity);
        }

        @Override
        public int hashCode() {
            return Objects.hash(entity);
        }

    }

}
