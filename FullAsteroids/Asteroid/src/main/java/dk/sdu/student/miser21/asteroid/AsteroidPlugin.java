package dk.sdu.student.miser21.asteroid;

import java.lang.Math;
import dk.sdu.student.miser21.common.data.Color;
import dk.sdu.student.miser21.common.data.Entity;
import dk.sdu.student.miser21.common.data.GameData;
import dk.sdu.student.miser21.common.data.World;
import dk.sdu.student.miser21.common.data.entityparts.LifePart;
import dk.sdu.student.miser21.common.data.entityparts.MovingPart;
import dk.sdu.student.miser21.common.data.entityparts.PositionPart;
import dk.sdu.student.miser21.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;
    private int life;
    private float deacceleration;
    private float acceleration;
    private float maxSpeed;
    private float rotationSpeed;
    private int shapeCount;
    private Color color;

    public AsteroidPlugin() {
        this(3);
    }

    public AsteroidPlugin(int life) {
        this.life = life;
        this.deacceleration = 0;
        this.acceleration = 0;
        this.maxSpeed = 400;
        this.rotationSpeed = 0;
        this.color = new Color(11, 255, 0, 50);
        this.shapeCount = 8;
    }

    @Override
    public void start(GameData gameData, World world) {
        int asteroidCount = (int) (Math.floor(Math.random() * 15) + 5);
        for (int i = 0; i < asteroidCount; i++) {
            asteroid = createInitialAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    public Entity createInitialAsteroid(GameData gameData) {
        float x = (float) (Math.random() * gameData.getDisplayWidth());
        float y = (float) (Math.random() * gameData.getDisplayHeight());
        float radians = (float) (Math.random() * (2 * Math.PI));

        float startSpeed = (float) (Math.random() * 50f) + 25f;

        Entity asteroid = new Asteroid();
        setAsteroidRadius(asteroid);
        buildAsteroid(gameData, asteroid, x, y, radians, startSpeed);

        return asteroid;
    }

    protected void createSplittetAsteroid(GameData gameData, World world, Entity asteroid) {
        world.removeEntity(asteroid);

        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        MovingPart movingPart = asteroid.getPart(MovingPart.class);
        LifePart lifePart = asteroid.getPart(LifePart.class);

        this.life = lifePart.getLife() - 1;

        if (lifePart.getLife() <= 1) {
            return;
        }

        float[] splits = new float[]{(float) (Math.PI * 1 / 2), (float) (Math.PI * 1 / 2 * (-1))};

        for (float split : splits) {
            Entity splittetAsteroid = new Asteroid();

            setAsteroidRadius(splittetAsteroid);

            float radians = positionPart.getRadians() + split;

            float bx = (float) Math.cos(radians) * asteroid.getRadius();
            float x = bx + positionPart.getX();
            float by = (float) Math.sin(radians) * asteroid.getRadius();
            float y = by + positionPart.getY();

            float currentSpeed = movingPart.getSpeed();
            float startSpeed = (float) ((Math.random() * (75f - currentSpeed)) + currentSpeed);

            buildAsteroid(gameData, splittetAsteroid, x, y, radians, startSpeed);

            world.addEntity(splittetAsteroid);
        }
    }

    private void buildAsteroid(GameData gameData, Entity asteroid, float x, float y, float radians, float startSpeed) {
        float[] shapeX = new float[shapeCount];
        float[] shapeY = new float[shapeCount];
        Color asteroidColor = color;

        MovingPart movingPart = new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed, startSpeed);
        PositionPart positionPart = new PositionPart(x, y, radians);
        LifePart lifePart = new LifePart(life, 0);

        asteroid.setShapeX(shapeX);
        asteroid.setShapeY(shapeY);
        asteroid.setColor(asteroidColor);
        asteroid.add(movingPart);
        asteroid.add(positionPart);
        asteroid.add(lifePart);

        setAsteroidRadius(asteroid);
    }

    private void setAsteroidRadius(Entity asteroid) {
        float radius = 0;
        switch (life) {
            case 1:
                radius = 10;
                break;
            case 2:
                radius = 15;
                break;
            case 3:
                radius = 25;
                break;
            default:
                break;
        }
        asteroid.setRadius(radius);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(asteroid);
    }
}
