package server;

import games.*;

public class Helper {
    public static String getHelp(String gameName) {
        switch (gameName) {
            case "CursedObject":    return CursedObject.gameDescription;
            case "OpenDoor2":   return OpenDoor2.gameDescription;
            case "TreasureHunt":    return TreasureHunt.gameDescription;
            case "WolfSheep":   return WolfSheep.gameDescription;
            default:    return "Invalid game name!";
        }
    }
}
