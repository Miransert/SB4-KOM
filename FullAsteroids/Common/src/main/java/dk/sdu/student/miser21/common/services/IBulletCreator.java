package dk.sdu.student.miser21.common.services;

import dk.sdu.student.miser21.common.data.Entity;
import dk.sdu.student.miser21.common.data.GameData;

public interface IBulletCreator {
    Entity create(Entity shooter, GameData gameData);
}
