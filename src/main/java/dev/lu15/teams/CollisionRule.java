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
 * Represents a collision rule for a {@link Team}.
 */
public enum CollisionRule {

    /**
     * Always collide with other players.
     */
    ALWAYS(TeamsPacket.CollisionRule.ALWAYS),
    /**
     * Only collide with players on other teams.
     */
    PUSH_OTHER_TEAMS(TeamsPacket.CollisionRule.PUSH_OTHER_TEAMS),
    /**
     * Only collide with players on the same team.
     */
    PUSH_OWN_TEAM(TeamsPacket.CollisionRule.PUSH_OWN_TEAM),
    /**
     * Never collide with other players.
     */
    NEVER(TeamsPacket.CollisionRule.NEVER);

    private final @NotNull TeamsPacket.CollisionRule rule;

    CollisionRule(TeamsPacket.@NotNull CollisionRule rule) {
        this.rule = rule;
    }

    /**
     * Gets the {@link TeamsPacket.CollisionRule} equivalent of this rule.
     * @return The {@link TeamsPacket.CollisionRule} equivalent of this rule.
     */
    public @NotNull TeamsPacket.CollisionRule rule() {
        return this.rule;
    }

}
