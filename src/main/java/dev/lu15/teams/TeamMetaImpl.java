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

record TeamMetaImpl(
        @NotNull CollisionRule collisionRule,
        @NotNull TagVisibility tagVisibility,
        @NotNull Component prefix,
        @NotNull Component suffix,
        @NotNull Component displayName,
        @NotNull NamedTextColor color,
        boolean allowFriendlyFire,
        boolean canSeeFriendlyInvisibles
) implements TeamMeta {

    static final class BuilderImpl implements TeamMeta.Builder {

        private @NotNull CollisionRule collisionRule = CollisionRule.ALWAYS;
        private @NotNull TagVisibility tagVisibility = TagVisibility.ALWAYS;
        private @NotNull Component prefix = Component.empty();
        private @NotNull Component suffix = Component.empty();
        private @NotNull Component displayName = Component.empty();
        private @NotNull NamedTextColor color = NamedTextColor.WHITE;
        private boolean allowFriendlyFire = true;
        private boolean canSeeFriendlyInvisibles = false;

        BuilderImpl() {

        }

        BuilderImpl(@NotNull TeamMeta meta) {
            this.collisionRule = meta.collisionRule();
            this.tagVisibility = meta.tagVisibility();
            this.prefix = meta.prefix();
            this.suffix = meta.suffix();
            this.displayName = meta.displayName();
            this.color = meta.color();
            this.allowFriendlyFire = meta.allowFriendlyFire();
            this.canSeeFriendlyInvisibles = meta.canSeeFriendlyInvisibles();
        }

        @Override
        public @NotNull TeamMeta build() {
            return new TeamMetaImpl(
                    this.collisionRule,
                    this.tagVisibility,
                    this.prefix,
                    this.suffix,
                    this.displayName,
                    this.color,
                    this.allowFriendlyFire,
                    this.canSeeFriendlyInvisibles
            );
        }

        @Override
        public @NotNull Builder collisionRule(@NotNull CollisionRule rule) {
            this.collisionRule = rule;
            return this;
        }

        @Override
        public @NotNull Builder tagVisibility(@NotNull TagVisibility visibility) {
            this.tagVisibility = visibility;
            return this;
        }

        @Override
        public @NotNull Builder prefix(@NotNull Component prefix) {
            this.prefix = prefix;
            return this;
        }

        @Override
        public @NotNull Builder suffix(@NotNull Component suffix) {
            this.suffix = suffix;
            return this;
        }

        @Override
        public @NotNull Builder displayName(@NotNull Component displayName) {
            this.displayName = displayName;
            return this;
        }

        @Override
        public @NotNull Builder color(@NotNull NamedTextColor color) {
            this.color = color;
            return this;
        }

        @Override
        public @NotNull Builder allowFriendlyFire(boolean allowFriendlyFire) {
            this.allowFriendlyFire = allowFriendlyFire;
            return this;
        }

        @Override
        public @NotNull Builder canSeeFriendlyInvisibles(boolean canSeeFriendlyInvisibles) {
            this.canSeeFriendlyInvisibles = canSeeFriendlyInvisibles;
            return this;
        }

    }

}
