import dk.sdu.student.miser21.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;

    provides IPostEntityProcessingService with dk.sdu.student.miser21.collision.CollisionDetector;
}