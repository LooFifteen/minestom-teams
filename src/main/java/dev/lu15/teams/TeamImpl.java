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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.TeamsPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

final class TeamImpl implements Team {

    private final @NotNull Set<TeamMember> members = new HashSet<>();
    private final @NotNull Set<Player> viewers = new HashSet<>();

    private final @NotNull String name;

    private @NotNull TeamMeta meta = new TeamMetaImpl.BuilderImpl().build();

    TeamImpl(@NotNull String name) {
        this.name = name;
    }

    @Override
    public @NotNull Set<TeamMember> getMembers() {
        return Collections.unmodifiableSet(this.members);
    }

    @Override
    public boolean addMember(@NotNull TeamMember member) {
        boolean added = this.members.add(member);
        if (added) sendGroupedPacket(new TeamsPacket(this.name, new TeamsPacket.AddEntitiesToTeamAction(Collections.singleton(member.getId()))));
        return added;
    }

    @Override
    public boolean removeMember(@NotNull TeamMember member) {
        boolean removed = this.members.remove(member);
        if (removed) sendGroupedPacket(new TeamsPacket(this.name, new TeamsPacket.RemoveEntitiesToTeamAction(Collections.singleton(member.getId()))));
        return removed;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public @NotNull TeamMeta getMeta() {
        return this.meta;
    }

    @Override
    public void setMeta(@NotNull TeamMeta meta) {
        this.meta = meta;
        sendGroupedPacket(new TeamsPacket(this.name, createUpdateAction()));
    }

    @Override
    public void setMeta(@NotNull Function<TeamMeta.Builder, TeamMeta.Builder> meta) {
        setMeta(meta.apply(new TeamMetaImpl.BuilderImpl(this.meta)).build());
    }

    @Override
    public boolean addViewer(@NotNull Player player) {
        boolean added = this.viewers.add(player);
        if (added) player.sendPacket(new TeamsPacket(this.name, createCreateAction()));
        return added;
    }

    @Override
    public boolean removeViewer(@NotNull Player player) {
        boolean removed = this.viewers.remove(player);
        if (removed) player.sendPacket(new TeamsPacket(this.name, new TeamsPacket.RemoveTeamAction()));
        return removed;
    }

    @Override
    public @NotNull @Unmodifiable Set<@NotNull Player> getViewers() {
        return Collections.unmodifiableSet(this.viewers);
    }

    @Override
    public @NotNull Collection<@NotNull Player> getPlayers() {
        return getViewers();
    }

    private @NotNull TeamsPacket.CreateTeamAction createCreateAction() {
        return new TeamsPacket.CreateTeamAction(
                this.meta.displayName(),
                createFriendlyFlags(this.meta),
                this.meta.tagVisibility().visibility(),
                this.meta.collisionRule().rule(),
                this.meta.color(),
                this.meta.prefix(),
                this.meta.suffix(),
                this.members.stream()
                        .map(TeamMember::getId)
                        .collect(Collectors.toSet())
        );
    }

    private @NotNull TeamsPacket.UpdateTeamAction createUpdateAction() {
        return new TeamsPacket.UpdateTeamAction(
                this.meta.displayName(),
                createFriendlyFlags(this.meta),
                this.meta.tagVisibility().visibility(),
                this.meta.collisionRule().rule(),
                this.meta.color(),
                this.meta.prefix(),
                this.meta.suffix()
        );
    }

    private byte createFriendlyFlags(@NotNull TeamMeta meta) {
        byte flags = 0x00;
        if (meta.allowFriendlyFire()) flags |= 0x01;
        if (meta.canSeeFriendlyInvisibles()) flags |= 0x02;
        return flags;
    }

}
