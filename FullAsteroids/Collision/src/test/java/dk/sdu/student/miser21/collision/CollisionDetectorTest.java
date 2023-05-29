package dk.sdu.student.miser21.collision;

import dk.sdu.student.miser21.common.data.Entity;
import dk.sdu.student.miser21.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionDetectionTest {
    private CollisionDetector collisionDetector;

    @BeforeEach
    void setUp() {
        this.collisionDetector = new CollisionDetector();
    }

    @Test
    void collides() {
        Entity entity1 = new Entity();
        entity1.add(new PositionPart(0, 0, 0));
        entity1.setRadius(5);

        Entity entity2 = new Entity();
        entity2.add(new PositionPart(3, 0, 0));
        entity2.setRadius(5);

        Entity entity3 = new Entity();
        entity3.add(new PositionPart(14.99f, 0, 0));
        entity3.setRadius(7);

        boolean shouldCollide = collisionDetector.collides(entity1, entity2);
        boolean shouldCollide2 = collisionDetector.collides(entity2, entity3);
        boolean shouldNotCollide = collisionDetector.collides(entity1, entity3);

        assertTrue(shouldCollide);
        assertTrue(shouldCollide2);
        assertFalse(shouldNotCollide);
    }
}