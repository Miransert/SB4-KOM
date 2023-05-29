package dk.sdu.student.miser21.bullet;

import dk.sdu.student.miser21.common.data.Entity;
import dk.sdu.student.miser21.common.data.GameData;
import dk.sdu.student.miser21.common.data.World;
import dk.sdu.student.miser21.common.data.entityparts.LifePart;
import dk.sdu.student.miser21.common.data.entityparts.MovingPart;
import dk.sdu.student.miser21.common.data.entityparts.PositionPart;
import dk.sdu.student.miser21.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            lifePart.reduceExpiration(gameData.getDelta());
            lifePart.process(gameData, bullet);

            updateShape(bullet);

            if (lifePart.getExpiration() <= 0 || lifePart.isDead()) {
                world.removeEntity(bullet);
            }
        }
    }

    private void updateShape(Entity entity) {
        float[] shapeX = new float[4];
        float[] shapeY = new float[4];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float cosRadians = (float) Math.cos(radians);
        float sinRadians = (float) Math.sin(radians);
        float length = 2;

        shapeX[0] = x + cosRadians * length;
        shapeY[0] = y + sinRadians * length;

        shapeX[1] = x + cosRadians * 0;
        shapeY[1] = y + sinRadians * 0;

        shapeX[2] = x + cosRadians * (2 * length);
        shapeY[2] = y + sinRadians * (2 * length);

        shapeX[3] = x + cosRadians * (-2 * length);
        shapeY[3] = y + sinRadians * (-2 * length);

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}












