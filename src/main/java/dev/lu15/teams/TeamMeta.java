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

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the immutable metadata of a {@link Team}.
 */
public sealed interface TeamMeta permits TeamMetaImpl {

    /**
     * Creates a new {@link TeamMeta.Builder}.
     * @return The new {@link TeamMeta.Builder}.
     */
    static @NotNull Builder builder() {
        return new TeamMetaImpl.BuilderImpl();
    }

    /**
     * Creates a new {@link TeamMeta.Builder} from the given {@link TeamMeta}.
     * @param meta The {@link TeamMeta} to create a {@link TeamMeta.Builder} from.
     * @return The new {@link TeamMeta.Builder}.
     */
    static @NotNull Builder builder(@NotNull TeamMeta meta) {
        return new TeamMetaImpl.BuilderImpl(meta);
    }

    /**
     * Gets the collision rule.
     * @return The collision rule.
     */
    @NotNull CollisionRule collisionRule();

    /**
     * Gets the tag visibility.
     * @return The tag visibility.
     */
    @NotNull TagVisibility tagVisibility();

    /**
     * Gets the prefix.
     * @return The prefix.
     */
    @NotNull Component prefix();

    /**
     * Gets the suffix.
     * @return The suffix.
     */
    @NotNull Component suffix();

    /**
     * Gets the display name.
     * @return The display name.
     */
    @NotNull Component displayName();

    /**
     * Gets the color.
     * @return The color.
     */
    @NotNull NamedTextColor color();

    /**
     * Gets whether friendly fire is allowed.
     * @return true if friendly fire is allowed, false otherwise.
     */
    boolean allowFriendlyFire();

    /**
     * Gets whether friendly invisibles are visible.
     * @return true if friendly invisibles are visible, false otherwise.
     */
    boolean canSeeFriendlyInvisibles();

    /**
     * Represents a builder for {@link TeamMeta}.
     */
    sealed interface Builder permits TeamMetaImpl.BuilderImpl {

        /**
         * Builds the {@link TeamMeta}.
         * @return The built {@link TeamMeta}.
         */
        @NotNull TeamMeta build();

        /**
         * Sets the collision rule.
         * @param rule The collision rule to set.
         * @return This builder.
         */
        @NotNull Builder collisionRule(@NotNull CollisionRule rule);

        /**
         * Sets the tag visibility.
         * @param visibility The tag visibility to set.
         * @return This builder.
         */
        @NotNull Builder tagVisibility(@NotNull TagVisibility visibility);

        /**
         * Sets the prefix.
         * @param prefix The prefix to set.
         * @return This builder.
         */
        @NotNull Builder prefix(@NotNull Component prefix);

        /**
         * Sets the suffix.
         * @param suffix The suffix to set.
         * @return This builder.
         */
        @NotNull Builder suffix(@NotNull Component suffix);

        /**
         * Sets the display name.
         * @param displayName The display name to set.
         * @return This builder.
         */
        @NotNull Builder displayName(@NotNull Component displayName);

        /**
         * Sets the color.
         * @param color The color to set.
         * @return This builder.
         */
        @NotNull Builder color(@NotNull NamedTextColor color);

        /**
         * Sets whether friendly fire is allowed.
         * @param allowFriendlyFire Whether friendly fire is allowed.
         * @return This builder.
         */
        @NotNull Builder allowFriendlyFire(boolean allowFriendlyFire);

        /**
         * Allows friendly fire.
         * @return This builder.
         */
        default @NotNull Builder allowFriendlyFire() {
            return allowFriendlyFire(true);
        }

        /**
         * Disallows friendly fire.
         * @return This builder.
         */
        default @NotNull Builder disallowFriendlyFire() {
            return allowFriendlyFire(false);
        }

        /**
         * Sets whether friendly invisibles are visible.
         * @param canSeeFriendlyInvisibles Whether friendly invisibles are visible.
         * @return This builder.
         */
        @NotNull Builder canSeeFriendlyInvisibles(boolean canSeeFriendlyInvisibles);

    }

}
