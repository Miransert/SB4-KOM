import dk.sdu.student.miser21.common.services.IBulletCreator;
import dk.sdu.student.miser21.common.services.IEntityProcessingService;
import dk.sdu.student.miser21.common.services.IGamePluginService;

module DefaultBullet {
    requires Common;

    provides IBulletCreator with dk.sdu.student.miser21.bullet.BulletPlugin;
    provides IGamePluginService with dk.sdu.student.miser21.bullet.BulletPlugin;
    provides IEntityProcessingService with dk.sdu.student.miser21.bullet.BulletControlSystem;
}