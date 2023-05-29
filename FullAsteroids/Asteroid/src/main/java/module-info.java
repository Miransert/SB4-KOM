import dk.sdu.student.miser21.common.services.IEntityProcessingService;
import dk.sdu.student.miser21.common.services.IGamePluginService;

module Asteroid {
    requires Common;

    provides IGamePluginService with dk.sdu.student.miser21.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.student.miser21.asteroid.AsteroidControlSystem;
}