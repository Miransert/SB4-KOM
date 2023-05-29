package dk.sdu.student.miser21.bullet;

import java.lang.Math;
import dk.sdu.student.miser21.common.data.Color;
import dk.sdu.student.miser21.common.data.Entity;
import dk.sdu.student.miser21.common.data.GameData;
import dk.sdu.student.miser21.common.data.World;
import dk.sdu.student.miser21.common.data.entityparts.LifePart;
import dk.sdu.student.miser21.common.data.entityparts.MovingPart;
import dk.sdu.student.miser21.common.data.entityparts.PositionPart;
import dk.sdu.student.miser21.common.services.IBulletCreator;
import dk.sdu.student.miser21.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService, IBulletCreator {

    private Entity bullet;

    public BulletPlugin() {}

    @Override
    public void start(GameData gameData, World world) {

    }

    private Entity createBullet(GameData gameData, Entity shooter) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        MovingPart shooterMovement = shooter.getPart(MovingPart.class);

        float deacceleration = 0;
        float acceleration = 0;
        float maxSpeed = 1000;
        float rotationSpeed = 5;
        float radians = shooterPos.getRadians();

        Entity bullet = new Bullet();

        bullet.setRadius(2);

        float dx = (float) (Math.cos(radians) * shooter.getRadius());
        float dy = (float) (Math.sin(radians) * shooter.getRadius());

        float bx = (float) Math.cos(radians) * shooter.getRadius() * bullet.getRadius();
        float x = bx + shooterPos.getX() + dx;
        float by = (float) Math.sin(radians) * shooter.getRadius() * bullet.getRadius();
        float y = by + shooterPos.getY() + dy;
        float shootSpeed = 350 + (shooterMovement.getSpeed());

        bullet.setShapeX(new float[4]);
        bullet.setShapeY(new float[4]);
        bullet.setColor(new Color(0,255,191,1));
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed, shootSpeed));
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new LifePart(1, 1));

        return bullet;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(bullet);
    }

    @Override
    public Entity create(Entity shooter, GameData gameData) {
        return this.createBullet(gameData, shooter);
    }
}
