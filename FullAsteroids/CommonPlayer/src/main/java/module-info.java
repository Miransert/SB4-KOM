import dk.sdu.student.miser21.common.services.IBulletCreator;
import dk.sdu.student.miser21.common.services.IEntityProcessingService;
import dk.sdu.student.miser21.common.services.IGamePluginService;

module DefaultPlayer {
    requires Common;

    uses IBulletCreator;

    provides IGamePluginService with dk.sdu.student.miser21.player.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.student.miser21.player.PlayerControlSystem;
}