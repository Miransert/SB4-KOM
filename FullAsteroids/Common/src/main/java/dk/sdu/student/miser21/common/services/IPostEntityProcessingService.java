package dk.sdu.student.miser21.common.services;

import dk.sdu.student.miser21.common.data.GameData;
import dk.sdu.student.miser21.common.data.World;

public interface IPostEntityProcessingService  {
        void process(GameData gameData, World world);
}