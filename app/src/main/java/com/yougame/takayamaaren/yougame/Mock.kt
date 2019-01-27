package com.yougame.takayamaaren.yougame

import java.io.Serializable

data class Game(
        val name: String,
        val cover: String,
        val meta: String,
        val description: String
) : Serializable

object Mock {
    val gameList = mutableListOf(
            Game(
                    "Frostpunk",
                    "https://steamcdn-a.akamaihd.net/steam/apps/323190/header.jpg?t=1525678989",
                    "生存",
                    "Frostpunk is the first society survival game. As the ruler of the last city on Earth, it is your duty to manage both its citizens and its infrastructure. What decisions will you make to ensure your society's survival? What will you do when pushed to breaking point? Who will you become in the process?"
            ),
            Game(
                    "Raft",
                    "https://steamcdn-a.akamaihd.net/steam/apps/648800/header.jpg?t=1527104102",
                    "建造",
                    "Raft throws you and your friends into an epic oceanic adventure! Alone or together, players battle to survive a perilous voyage across a vast sea! Gather debris, scavenge reefs and build your own floating home, but be wary of the man-eating sharks!"
            )
    )
    val ubisoftGame = mutableListOf(
            Game(
                    "Tom Clancy's Rainbow Six® Siege",
                    "https://steamcdn-a.akamaihd.net/steam/apps/359550/header_alt_assets_2.jpg?t=1528471305",
                    "FPS",
                    "Tom Clancy's Rainbow Six Siege is the latest installment of the acclaimed first-person shooter franchise developed by the renowned Ubisoft Montreal studio."
            ),
            Game(
                    "Tom Clancy’s The Division™",
                    "https://steamcdn-a.akamaihd.net/steam/apps/365590/header.jpg?t=1513590468",
                    "开放世界",
                    "Black Friday – a devastating pandemic sweeps through New York City, and one by one, basic services fail. In only days, without food or water, society collapses into chaos. The Division, an autonomous unit of tactical agents, is activated."
            ),
            Game(
                    "Tom Clancy's Ghost Recon® Wildlands",
                    "https://steamcdn-a.akamaihd.net/steam/apps/460930/header.jpg?t=1527101477",
                    "开放世界",
                    "Create a team with up to 3 friends in Tom Clancy’s Ghost Recon® Wildlands and enjoy the ultimate military shooter experience set in a massive, dangerous, and responsive open world."
            ),
            Game(
                    "Far Cry® 5",
                    "https://steamcdn-a.akamaihd.net/steam/apps/552520/header.jpg?t=1527102722",
                    "开放世界",
                    "Welcome to Hope County, Montana, home to a fanatical doomsday cult known as Eden’s Gate. Stand up to cult leader Joseph Seed & his siblings, the Heralds, to spark the fires of resistance & liberate the besieged community."
            ),
            Game(
            "Assassin's Creed® Origins",
            "https://steamcdn-a.akamaihd.net/steam/apps/582160/header.jpg?t=1527101739",
            "开放世界",
            "ASSASSIN’S CREED® ORIGINS IS A NEW BEGINNING *The Discovery Tour by Assassin’s Creed®: Ancient Egypt is available now as a free update!* Ancient Egypt, a land of majesty and intrigue, is disappearing in a ruthless fight for power."
    )

    )

}