package dk.sdu.student.miser21.common.data.entityparts;

import dk.sdu.student.miser21.common.data.Entity;
import dk.sdu.student.miser21.common.data.GameData;

public class ShootingPart implements EntityPart {
    private float cooldownTime;
    private float cooldown;
    private boolean shooting;

    public ShootingPart(float cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public void setShooting(boolean shooting) {
        if (shooting && cooldown <= 0) {
            this.shooting = true;
            this.cooldown = cooldownTime;
        } else {
            this.shooting = false;
        }
    }

    public boolean getShooting() {
        return shooting;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (cooldown > 0) {
            cooldown -= gameData.getDelta();
            shooting = false;
        } else {
            cooldown = 0;
        }
    }
}
