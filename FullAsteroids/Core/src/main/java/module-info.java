import dk.sdu.student.miser21.common.services.IEntityProcessingService;
import dk.sdu.student.miser21.common.services.IGamePluginService;
import dk.sdu.student.miser21.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires java.desktop;
    requires com.badlogic.gdx;
    requires spring.context;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;

    exports dk.sdu.student.miser21.components;
    exports dk.sdu.student.miser21.main;
}