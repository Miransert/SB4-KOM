package dk.sdu.student.miser21.common.data.entityparts;

import dk.sdu.student.miser21.common.data.GameData;
import dk.sdu.student.miser21.common.data.Entity;

public interface EntityPart {
    void process(GameData gameData, Entity entity);
}
