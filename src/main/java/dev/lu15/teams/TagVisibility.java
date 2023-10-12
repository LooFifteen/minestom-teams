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

import net.minestom.server.network.packet.server.play.TeamsPacket;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a tag visibility rule for a {@link Team}.
 */
public enum TagVisibility {

    /**
     * Always show other players' tags.
     */
    ALWAYS(TeamsPacket.NameTagVisibility.ALWAYS),
    /**
     * Only show other players' tags if they are on other teams.
     */
    HIDE_FOR_OTHER_TEAMS(TeamsPacket.NameTagVisibility.HIDE_FOR_OTHER_TEAMS),
    /**
     * Only show other players' tags if they are on the same team.
     */
    HIDE_FOR_OWN_TEAM(TeamsPacket.NameTagVisibility.HIDE_FOR_OWN_TEAM),
    /**
     * Never show other players' tags.
     */
    NEVER(TeamsPacket.NameTagVisibility.NEVER);

    private final @NotNull TeamsPacket.NameTagVisibility rule;

    TagVisibility(@NotNull TeamsPacket.NameTagVisibility visibility) {
        this.rule = visibility;
    }

    /**
     * Gets the {@link TeamsPacket.NameTagVisibility} equivalent of this rule.
     * @return The {@link TeamsPacket.NameTagVisibility} equivalent of this rule.
     */
    public @NotNull TeamsPacket.NameTagVisibility visibility() {
        return this.rule;
    }

}
