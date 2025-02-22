package com.blitzoffline.randomteleport.listeners

import com.blitzoffline.randomteleport.config.holder.Messages
import com.blitzoffline.randomteleport.config.settings
import com.blitzoffline.randomteleport.cooldown.tasks
import com.blitzoffline.randomteleport.cooldown.warmupsStarted
import com.blitzoffline.randomteleport.util.msg
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class MoveListener : Listener {

    @EventHandler(ignoreCancelled = true)
    fun PlayerMoveEvent.playerMove() {
        val distanceSquared = from.distanceSquared(to)
        if (distanceSquared < 0.05) return
        if (tasks[player.uniqueId] == null) return

        tasks[player.uniqueId]?.cancel()
        tasks.remove(player.uniqueId)
        warmupsStarted.remove(player.uniqueId)
        settings[Messages.TELEPORT_CANCELED].replace("%reason%", settings[Messages.MOVED]).msg(player)
    }
}