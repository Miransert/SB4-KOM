package dk.sdu.student.miser21.components;

import dk.sdu.student.miser21.common.data.GameData;
import dk.sdu.student.miser21.common.data.World;

public interface IProcessor {
    void process(GameData gameData, World world);
}
